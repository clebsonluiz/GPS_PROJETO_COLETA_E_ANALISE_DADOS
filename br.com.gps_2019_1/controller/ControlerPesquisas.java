package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import entidade.EstruturaPesquisa;
import entidade.Pesquisa;
import exceptions.BOException;
import exceptions.DAOException;
import exceptions.ValidacaoException;
import facade.Facade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import relatorio.Relatorio;
import view.Message;

public class ControlerPesquisas implements Initializable {

	public static ControlerPesquisas controlerPesquisas;
	
	private List<Pesquisa> pesquisas;
	
    @FXML
    private JFXTextField buscarPesquiFIeld;

    @FXML
    private JFXButton buscarPesqBtn;

    @FXML
    private PieChart periodoGraficoP;
    
    @FXML
    private JFXButton relatorioBtn;

    @FXML
    private TableView<Pesquisa> listPesqTabela;

    /**
     * Definição do tipo de entidade e do tipo da coluna da tabela.
     */
    @FXML
    private TableColumn<Pesquisa, String> nomeCol;

    /**
     * Definição do tipo de entidade e do tipo da coluna da tabela.
     */
    @FXML
    private TableColumn<Pesquisa, String> descricaoCol;

    
    /**
     * 
     * @param event
     * Evento de ação
     */
    @FXML
    void action(ActionEvent event) {

    	if(event.getSource() == buscarPesqBtn)
    	{
    		buscarPesquisas();
    	}
    	
    	
    	if(event.getSource() == relatorioBtn) {
    		if(listPesqTabela.getSelectionModel().getSelectedItem() != null) {
    			
    			Pesquisa p = listPesqTabela.getSelectionModel().getSelectedItem();    			
    			try 
    			{
					p.setEstruturaPesquisas(Facade.getInstance().getBussinessEstrutura().getEstruturasPesquisa(p.getId()));
					
					p.getEstruturaPesquisas().forEach(e->{
						try {
							e.setDados(Facade.getInstance().getBussinessDado().getEstruturasPesquisa(e.getId()));
						} catch (BOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (DAOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					});
					
					
				} catch (DAOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    			
    			
    			FileChooser chooser = new FileChooser();
    			chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Arquivos pdf", "*.pdf"));
    			File file = chooser.showSaveDialog(null);
    			
    			if(file != null) {
    			try {
					Relatorio.gerarRelatorio(p, file.getAbsolutePath());
					
					Message.getInstance().viewMessage(
							AlertType.INFORMATION, 
							"Gera��o de Relatorio", 
							"Relatorio gerado",
							file.getAbsolutePath() + ".pdf");
				} catch (FileNotFoundException | DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			}
    		}
    		
    	}
    	
    }

    /**
     * 
     * @param event
     * Evento de mouse
     */
    @FXML
    void mouseClick(MouseEvent event) 
    {
    	if(event.getSource() == listPesqTabela && listPesqTabela.getSelectionModel().getSelectedItem() != null)
    	{
    		if(event.getClickCount() >= 2 )
    		{
    			//TODO - Abrir tela de Estruturas Aqui
    			//Vê se ta certo Clébson
    			Atual.pesquisa = listPesqTabela.getSelectionModel().getSelectedItem();
    			ControlerInicio.controleInicio.updateFrame("pesquisaUnica");
    			
    		}
    		else
    		{
    			Pesquisa pesquisa = listPesqTabela.getSelectionModel().getSelectedItem();
    			
    			try 
    			{
    				atualizarGrafico(pesquisa);
    			}
    			catch (ValidacaoException e) 
    			{
    				e.printStackTrace();
    			}
    		}
    	}
    }

    /**
     * Método que inicializa componentes ao iniciar o sistema, serve também pra deixar as configurações
     * pré-carregadas, etc.
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		/**
		 * @author ayrton
		 * Setando os valores das colunas nomeCol e descricaoCol da tabela com base na Entidade Pesquisas,
		 * os nomes de cada coluna tem que ser os mesmos dos atributos da entidade.
		 */
		nomeCol.setCellValueFactory(new PropertyValueFactory<>("titulo"));
		descricaoCol.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		
		controlerPesquisas = this;
		
				
	}
	
	
	private void buscarPesquisas()
	{
		try 
		{
			Atual.usuario.setPesquisas(
					Facade.getInstance().getBussinessPesquisa().getPesquisasUsuarioEspecifica(buscarPesquiFIeld.getText(), Atual.usuario.getId())
			);
			
			if(Atual.usuario.getPesquisas() == null)
				Atual.usuario.setPesquisas(new ArrayList<>());
			
			listPesqTabela.getItems().setAll(Atual.usuario.getPesquisas());
		
		} 
		catch (ValidacaoException e) 
		{
			Message.getInstance().viewMessage(
					AlertType.ERROR, 
					"Erro", 
					"Erro ao buscar pesquisas",
					e.getMessage());
		}
	}
	
	private void atualizarGrafico(Pesquisa pesquisa) throws BOException, DAOException
	{
		int porc_andamento = (Integer) ((Double)Facade.getInstance().getBussinessPesquisa()
				.buscaSQLGenerica(getSQLPorcentagem(pesquisa.getDataInicio(), pesquisa.getDataFim()))).intValue();
		int porc_restante = 100 - porc_andamento;
		
		periodoGraficoP.getData().clear();
		periodoGraficoP.getData().add(new PieChart.Data("Atual", porc_andamento));
		periodoGraficoP.getData().add(new PieChart.Data("Restante", porc_restante));
		
	}
	
	private String getSQLPorcentagem(LocalDate dataInicial, LocalDate dataFinal)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		String data1 = dataInicial.format(formatter);
		String data2 = dataFinal.format(formatter);
		
		String sql = "SELECT porcentagem from ( " + 
				" " + 
				"	SELECT ( " + 
				" " + 
				"	CASE WHEN (ABS(CAST('"+ data1 + "' AS DATE) - CAST('" + data2 + "' AS DATE)) <= 0) " + 
				"	THEN " + 
				"		100 " + 
				"	ELSE " + 
				"		(CAST(ABS(CAST('"+ data1 + "' AS DATE) - CURRENT_DATE) AS FLOAT)/CAST(ABS(CAST('"+ data1 + "' AS DATE) - CAST('"+ data2 + "' AS DATE)) as FLOAT))*100 " + 
				"	END) as porcentagem " + 
				" " + 
				") as alias_table";
				
		return sql;
	}
	
}
