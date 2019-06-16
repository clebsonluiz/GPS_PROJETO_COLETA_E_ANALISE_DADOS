package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import entidade.Dado;
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

public class ControlerEstrutura implements Initializable {

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
		familiaCol.setCellValueFactory(new PropertyValueFactory<>("col_1_nome_familia"));
		nomeCol.setCellValueFactory(new PropertyValueFactory<>("col_2_nome"));
		valorCol.setCellValueFactory(new PropertyValueFactory<>("col_3_valor"));

	}

}
