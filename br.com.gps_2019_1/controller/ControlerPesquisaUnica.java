/**
 * 
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import entidade.EstruturaPesquisa;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import util.TipoGrafico;

/**
 * @author ayrton
 *
 */
public class ControlerPesquisaUnica implements Initializable {

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
	private AreaChart<?, ?> areaGrafico;

	@FXML
	private Tab barraTab;

	@FXML
	private BarChart<?, ?> barraGrafico;

	@FXML
	private Tab linhaTab;

	@FXML
	private LineChart<?, ?> linhaGrafico;

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

}
