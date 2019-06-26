/**
 * 
 */
package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import entidade.Dado;
import entidade.EstruturaPesquisa;
import exceptions.BOException;
import exceptions.DAOException;
import facade.Facade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import util.EntidadeUtil;
import util.TipoGrafico;
import view.Message;

/**
 * @author ayrton
 *
 */
public class ControlerPesquisaUnica implements Initializable {

	public static ControlerPesquisaUnica controlerPesquisaUnica;

	private List<EstruturaPesquisa> estruturaPesquisas;

	@FXML
	private JFXTextField buscarEstruFIeld;

	@FXML
	private JFXButton buscarEstruBtn;

	@FXML
	private JFXButton voltarBtn;

	@FXML
	private JFXComboBox<TipoGrafico> graficoCmbBox;

	@FXML
	private TableView<EstruturaPesquisa> listEstruPesqTabela;

	@FXML
	private TableColumn<EstruturaPesquisa, String> tituloCol;

	@FXML
	private TableColumn<EstruturaPesquisa, String> familiaCol;

	@FXML
	private TableColumn<EstruturaPesquisa, String> nomeCol;

	@FXML
	private TableColumn<EstruturaPesquisa, String> valorCol;

	@FXML
	private TableColumn<EstruturaPesquisa, String> categoriaCol;

	@FXML
	private Tab areaTab;

	@FXML
	private AreaChart<String, Number> areaGrafico;

	@FXML
	private Tab barraTab;

	@FXML
	private BarChart<String, Number> barraGrafico;

	@FXML
	private Tab linhaTab;

	@FXML
	private LineChart<String, Number> linhaGrafico;

	@FXML
	private Tab pizzaTab;

	@FXML
	private PieChart pizzaGrafico;

	/**
	 * 
	 * @param event Evento de ação
	 */
	@FXML
	void action(ActionEvent event) {

		if (event.getSource() == buscarEstruBtn) {

			buscarEstrutura();
		}

		if (event.getSource() == voltarBtn) {

			ControlerInicio.controleInicio.updateFrame("pesquisas");

		}

		if (event.getSource() == graficoCmbBox) {

			if (graficoCmbBox.getValue().equals(TipoGrafico.AREA)) {
				areaTab.getTabPane().getSelectionModel().select(areaTab);
			}

			if (graficoCmbBox.getValue().equals(TipoGrafico.BARRA)) {

				barraTab.getTabPane().getSelectionModel().select(barraTab);

			}

			if (graficoCmbBox.getValue().equals(TipoGrafico.LINHA)) {

				linhaTab.getTabPane().getSelectionModel().select(linhaTab);

			}
			
			if(graficoCmbBox.getValue().equals(TipoGrafico.PIZZA)) {
				
				pizzaTab.getTabPane().getSelectionModel().select(pizzaTab);
				
			}

		}
	}

	/**
	 * 
	 * @param event Evento de mouse
	 */
	@FXML
	void mouseClick(MouseEvent event) {

		if (event.getSource() == listEstruPesqTabela
				&& listEstruPesqTabela.getSelectionModel().getSelectedItem() != null) {

			if (event.getClickCount() >= 2) {
				Atual.estrutura_pesquisa = listEstruPesqTabela.getSelectionModel().getSelectedItem();
				ControlerInicio.controleInicio.updateFrame("estrutura");

			}
			else
			{
				try {
					EstruturaPesquisa e = listEstruPesqTabela.getSelectionModel().getSelectedItem();
					e.setDados(
					Facade.getInstance().getBussinessDado().getEstruturasPesquisa(e.getId())
							);

					areaGrafico.getData().clear();
					linhaGrafico.getData().clear();
					barraGrafico.getData().clear();
					pizzaGrafico.getData().clear();
					
					areaGrafico.setTitle(e.getTitulo_estrutura());
					linhaGrafico.setTitle(e.getTitulo_estrutura());
					barraGrafico.setTitle(e.getTitulo_estrutura());
					pizzaGrafico.setTitle(e.getTitulo_estrutura());
					
					barraGrafico.getYAxis().setLabel(e.getCategoria_dados());
					areaGrafico.getYAxis().setLabel(e.getCategoria_dados());
					linhaGrafico.getYAxis().setLabel(e.getCategoria_dados());
					
					
					e.getDados().forEach(dado->{
						
						pizzaGrafico.getData().add( new PieChart.Data(dado.getCol_1_nome_familia() + "|" + dado.getCol_2_nome(), EntidadeUtil.parceValorToDouble(dado.getCol_3_valor()) ));
						
						preencherGraficoLinha(dado, linhaGrafico);
						preencherGraficoArea(dado, areaGrafico);
						
					});
					
					List<Dado> dados = EntidadeUtil.getOrdenado(e.getDados());
					dados.forEach(dado->{
						preencherGraficoBarra(dado, barraGrafico);
					});
				} catch (DAOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
				
			}
		}

	}

	/**
	 * Método que inicializa componentes ao iniciar o sistema, serve também pra
	 * deixar as configurações pré-carregadas, etc.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		controlerPesquisaUnica = this;

		/**
		 * Setando as strings no combo box
		 */
		graficoCmbBox.getItems().setAll(TipoGrafico.values());

		/**
		 * Setando os valores das colunas da tabela com base na Entidade
		 * EstruturaPesquisa
		 */
		tituloCol.setCellValueFactory(new PropertyValueFactory<>("titulo_estrutura"));
		familiaCol.setCellValueFactory(new PropertyValueFactory<>("col_1_nome_familia"));
		nomeCol.setCellValueFactory(new PropertyValueFactory<>("col_2_nome"));
		valorCol.setCellValueFactory(new PropertyValueFactory<>("col_3_valor"));
		categoriaCol.setCellValueFactory(new PropertyValueFactory<>("categoria_dados"));

	}

	public void buscarEstrutura() {

		try {
			Atual.pesquisa.setEstruturaPesquisas(Facade.getInstance().getBussinessEstrutura()
					.getPesquisasUsuarioEspecifica(buscarEstruFIeld.getText(), Atual.pesquisa.getId()));
			if (Atual.pesquisa.getEstruturaPesquisas() == null) {
				Atual.pesquisa.setEstruturaPesquisas(new ArrayList<>());
			}

			listEstruPesqTabela.getItems().setAll(Atual.pesquisa.getEstruturaPesquisas());
		} catch (BOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Message.getInstance().viewMessage(AlertType.ERROR, "Erro", "Erro ao buscar estruturas", e.getMessage());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	public static void preencherGraficoLinha(Dado dado, LineChart<String, Number> linhaGrafico)
	{
		XYChart.Series series1 = new XYChart.Series();
		
        XYChart.Data<String, Number> data = new XYChart.Data<String, Number>(dado.getCol_1_nome_familia(), EntidadeUtil.parceValorToDouble(dado.getCol_3_valor()));
        
        series1.setName(dado.getCol_2_nome());       
        
        XYChart.Series<String, Number> seriesTemp = null;
        
        for(XYChart.Series serie: linhaGrafico.getData())
        {
        	if(serie.getName().equals(series1.getName()))
        	{
        		seriesTemp = serie;
        		break;
        	}
        }
        
        if(seriesTemp != null)
        	seriesTemp.getData().add(data);
        else
        {
        	series1.getData().add(data);
        	linhaGrafico.getData().add(series1);
        }
	}
	
	public static void preencherGraficoArea(Dado dado, AreaChart<String, Number> areaGrafico)
	{
		XYChart.Series series1 = new XYChart.Series();
		
        XYChart.Data<String, Number> data = new XYChart.Data<String, Number>(dado.getCol_1_nome_familia(), EntidadeUtil.parceValorToDouble(dado.getCol_3_valor()));
        
        series1.setName(dado.getCol_2_nome());       
        
        XYChart.Series<String, Number> seriesTemp = null;
        
        for(XYChart.Series serie: areaGrafico.getData())
        {
        	if(serie.getName().equals(series1.getName()))
        	{
        		seriesTemp = serie;
        		break;
        	}
        }
        
        if(seriesTemp != null)
        	seriesTemp.getData().add(data);
        else
        {
        	series1.getData().add(data);
        	areaGrafico.getData().add(series1);
        }
	}
	
	public static void preencherGraficoBarra(Dado dado, BarChart<String, Number> barraGrafico)
	{
		XYChart.Series series1 = new XYChart.Series();
		
        XYChart.Data data = new XYChart.Data(dado.getCol_1_nome_familia(), EntidadeUtil.parceValorToDouble(dado.getCol_3_valor()));
        
        series1.setName(dado.getCol_2_nome());       
        
        XYChart.Series seriesTemp = null;
        
        for(XYChart.Series serie: barraGrafico.getData())
        {
        	if(serie.getName().equals(series1.getName()))
        	{
        		seriesTemp = serie;
        		break;
        	}
        }
        
        if(seriesTemp != null)
        {
        	seriesTemp.getData().add(0, data);
        }
        else
        {
        	series1.getData().add(data);
        	barraGrafico.getData().add(series1);
        }
	}
	
}
