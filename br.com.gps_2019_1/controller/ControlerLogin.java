/**
 * 
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import entidade.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;



/**
 * @author ayrton
 *
 */
public class ControlerLogin implements Initializable{

	private String pass;
	
	@FXML
    private JFXTextField loginField;

    @FXML
    private JFXPasswordField senhaField;

    @FXML
    private JFXButton entrarBtn;

    @FXML
    private JFXButton sairBtn;

    @FXML
    private JFXPasswordField confimSenhaField;

    @FXML
    private JFXButton cadUserBtn;

    @FXML
    private JFXCheckBox cadUserChBox;

    private Usuario usuario;
    
   
    @FXML
    void action(ActionEvent event) {

    	if(event.getSource() == entrarBtn) {
    		if(efetuarLogin()) {
//    			App.changeStage("Home");
    		}
    	}
    	
    	if(event.getSource() == sairBtn) {
    		System.exit(0);
    	}
    	
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	public boolean efetuarLogin() {
		return false;

	
	}
	
}
