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
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import util.EntidadeUtil;
import util.TipoGrafico;
import view.Message;

public class ControlerEstrutura implements Initializable {

	public static ControlerEstrutura controlerEstrutura;

	@FXML
	private JFXTextField buscarDadosFIeld;

	@FXML
	private JFXButton buscarDadosBtn;

	@FXML
	private JFXButton voltarBtn;

	@FXML
	private JFXComboBox<TipoGrafico> graficoCmbBox;

	@FXML
	private TableView<Dado> listDadosTabela;

	@FXML
	private TableColumn<Dado, String> familiaCol;

	@FXML
	private TableColumn<Dado, String> nomeCol;

	@FXML
	private TableColumn<Dado, String> valorCol;

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

		if (event.getSource() == buscarDadosBtn) {
			buscarDados();

			EstruturaPesquisa e = Atual.estrutura_pesquisa;
			
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
				
				ControlerPesquisaUnica.preencherGraficoLinha(dado, linhaGrafico);
				ControlerPesquisaUnica.preencherGraficoArea(dado, areaGrafico);
				
			});
			
			List<Dado> dados = EntidadeUtil.getOrdenado(e.getDados());
			dados.forEach(dado->{
				ControlerPesquisaUnica.preencherGraficoBarra(dado, barraGrafico);
			});
			
			
			
		}

		if (event.getSource() == voltarBtn) {

			ControlerInicio.controleInicio.updateFrame("pesquisaUnica");

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

			if (graficoCmbBox.getValue().equals(TipoGrafico.PIZZA)) {

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

	}

	/**
	 * Método que inicializa componentes ao iniciar o sistema, serve também pra
	 * deixar as configurações pré-carregadas, etc.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		controlerEstrutura = this;

		/**
		 * Setando as strings no combo box
		 */
		graficoCmbBox.getItems().setAll(TipoGrafico.values());

		/**
		 * Setando os valores das colunas da tabela com base na Entidade
		 * EstruturaPesquisa
		 */
		familiaCol.setCellValueFactory(new PropertyValueFactory<>("col_1_nome_familia"));
		nomeCol.setCellValueFactory(new PropertyValueFactory<>("col_2_nome"));
		valorCol.setCellValueFactory(new PropertyValueFactory<>("col_3_valor"));

	}

	public void buscarDados() {

		try {
			Atual.estrutura_pesquisa.setDados(Facade.getInstance().getBussinessDado()
					.getPesquisasUsuarioEspecifica(buscarDadosFIeld.getText(), Atual.estrutura_pesquisa.getId()));
			if (Atual.estrutura_pesquisa.getDados() == null) {
				Atual.estrutura_pesquisa.setDados(new ArrayList<>());
			}

			listDadosTabela.getItems().setAll(Atual.estrutura_pesquisa.getDados());

		} catch (BOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Message.getInstance().viewMessage(AlertType.ERROR, "Erro", "Erro ao buscar dados", e.getMessage());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
