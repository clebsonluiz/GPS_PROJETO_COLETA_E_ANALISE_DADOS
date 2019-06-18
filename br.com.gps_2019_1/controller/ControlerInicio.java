/**
 * 
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

/**
 * @author ayrton
 *
 */
public class ControlerInicio implements Initializable {

	@FXML
	private MenuItem alterSenhaMenuItem;

	@FXML
	private MenuItem deletContaMenuItem;

	@FXML
	private MenuItem novaPesqMenuItem;

	@FXML
	private MenuItem infoSistemaMenuItem;

	@FXML
	private MenuItem infoDevsMenuItem;

	@FXML
	private MenuItem sairMenuItem;

	@FXML
	private AnchorPane pane;

	@FXML
	void action(ActionEvent event) {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
