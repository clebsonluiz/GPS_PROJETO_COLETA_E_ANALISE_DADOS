/**
 * 
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * @author ayrton
 *
 */
public class ControlerInicio implements Initializable {

	public static String TELA = "pesquisas";
	
	private  Pane pesquisaUnicaPane;
	private  Pane pesquisasPane;
	private  Pane estruturaPane;
	private  Pane cadastroPane;
	
	public static ControlerInicio controleInicio;
	
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
	private  AnchorPane pane;

	@FXML
	void action(ActionEvent event) {

		if(event.getSource() == novaPesqMenuItem) {
			updateFrame("nova pesquisa");
		}
		
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		controleInicio = this;
		
		try {
			pesquisasPane = FXMLLoader.load(getClass().getClassLoader().
					getResource("view/Pesquisas.fxml"));
			
			pesquisaUnicaPane = FXMLLoader.load(getClass().getClassLoader().
					getResource("view/PesquisaUnica.fxml"));
			
			estruturaPane = FXMLLoader.load(getClass().getClassLoader().
					getResource("view/Estrutura.fxml"));
			
			cadastroPane = FXMLLoader.load(getClass().getClassLoader().
					getResource("view/Cadastro.fxml"));
//			System.out.println(TELA.toString()); 
			updateFrame(TELA);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	/**
	 * Método para atualizar os Pane
	 * @param nameFrame
	 */
	public void updateFrame(String nameFrame) {

		if (nameFrame.equalsIgnoreCase("pesquisas")) {

			AnchorPane.setBottomAnchor(pesquisasPane, 0.0);
			AnchorPane.setLeftAnchor(pesquisasPane, 0.0);
			AnchorPane.setRightAnchor(pesquisasPane, 0.0);
			AnchorPane.setTopAnchor(pesquisasPane, 0.0);
			pane.getChildren().setAll(pesquisasPane);
		}

		if (nameFrame.equalsIgnoreCase("pesquisaUnica")) {

			AnchorPane.setBottomAnchor(pesquisaUnicaPane, 0.0);
			AnchorPane.setLeftAnchor(pesquisaUnicaPane, 0.0);
			AnchorPane.setRightAnchor(pesquisaUnicaPane, 0.0);
			AnchorPane.setTopAnchor(pesquisaUnicaPane, 0.0);
			pane.getChildren().setAll(pesquisaUnicaPane);
		}

		if (nameFrame.equalsIgnoreCase("estrutura")) {

			AnchorPane.setBottomAnchor(estruturaPane, 0.0);
			AnchorPane.setLeftAnchor(estruturaPane, 0.0);
			AnchorPane.setRightAnchor(estruturaPane, 0.0);
			AnchorPane.setTopAnchor(estruturaPane, 0.0);
			pane.getChildren().setAll(estruturaPane);
		}

		if (nameFrame.equalsIgnoreCase("nova pesquisa")) {

			AnchorPane.setBottomAnchor(cadastroPane, 0.0);
			AnchorPane.setLeftAnchor(cadastroPane, 0.0);
			AnchorPane.setRightAnchor(cadastroPane, 0.0);
			AnchorPane.setTopAnchor(cadastroPane, 0.0);
			pane.getChildren().setAll(cadastroPane);
		}
		
	}

	public static void atualizaFrame(String name) {

		
		TELA = name;
		System.out.println("atualizou na tela cadastro " + TELA.toString());
		
	}

	
}
