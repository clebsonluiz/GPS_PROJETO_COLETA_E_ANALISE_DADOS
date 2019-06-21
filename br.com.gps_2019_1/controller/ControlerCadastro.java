package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;

public class ControlerCadastro implements Initializable {

	@FXML
    private JFXTextField tituloPesqField;

    @FXML
    private JFXTextField descricaoPesqField;

    @FXML
    private JFXDatePicker inicioPesqDate;

    @FXML
    private JFXDatePicker fimPesqDate;

    @FXML
    private JFXButton addEstruPesqBtn;

    @FXML
    private JFXTextField tituloEstruPesqField;

    @FXML
    private JFXTextField categEstruPesqField;

    @FXML
    private JFXTextField nomeFamiEstruPesqField;

    @FXML
    private JFXTextField nomeEstruPesqField;

    @FXML
    private JFXTextField valorEstruPesqField;

    @FXML
    private JFXButton addDadosBtn;

    @FXML
    private JFXTextField nomeFamiliaDadosField;

    @FXML
    private JFXTextField nomeDadosField;

    @FXML
    private JFXTextField valorDadosField;

    @FXML
    private JFXButton cancelarCadBtn;

    @FXML
    private JFXButton finalizarCadBtn;

    @FXML
    void action(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
