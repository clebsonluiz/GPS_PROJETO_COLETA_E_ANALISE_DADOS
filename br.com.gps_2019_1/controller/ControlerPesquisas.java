package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import entidade.Pesquisa;
import exceptions.BOException;
import exceptions.DAOException;
import facade.Facade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ControlerPesquisas implements Initializable {

	private List<Pesquisa> pesquisas;
	
    @FXML
    private JFXTextField buscarPesquiFIeld;

    @FXML
    private JFXButton buscarPesqBtn;

    @FXML
    private PieChart periodoGraficoP;

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

    	
    	
    }

    /**
     * 
     * @param event
     * Evento de mouse
     */
    @FXML
    void mouseClick(MouseEvent event) {

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
		
		
		
		/**
		 * Adicionando as pesquisas na tabela
		 */
		try {
			pesquisas = Facade.getInstance().getBussinessPesquisa().buscarALL();
		
			listPesqTabela.getItems().setAll(pesquisas);
			
		} catch (BOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
