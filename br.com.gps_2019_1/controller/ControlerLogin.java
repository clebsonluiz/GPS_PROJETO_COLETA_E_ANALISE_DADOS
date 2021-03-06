/**
 * 
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javax.persistence.NoResultException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import app.app;
import entidade.Usuario;
import exceptions.BOException;
import exceptions.DAOException;
import exceptions.ValidacaoException;
import facade.Facade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import view.Message;

/**
 * @author ayrton
 *
 */
public class ControlerLogin implements Initializable {

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

    @FXML
    private Label senhaErroLabel;

    @FXML
    private JFXTextField nomeField;

//	private static Usuario usuario;

	@FXML
	void action(ActionEvent event) {

		if (event.getSource() == entrarBtn) {
    		if(efetuarLogin()) {
//			app.changeStage("Inicio");
    		}
		}

		if (cadUserChBox.isSelected()) {

			nomeField.setDisable(false);
			nomeField.setVisible(true);
			
			confimSenhaField.setDisable(false);
			confimSenhaField.setVisible(true);

			cadUserBtn.setDisable(false);
			cadUserBtn.setVisible(true);

		}
		
		if (!cadUserChBox.isSelected()) {

			desabilitarCampos();

		}		

		if (event.getSource() == cadUserBtn) {

			
			
			if(senhaField.getText().equals(confimSenhaField.getText())) {			
			try {
				Usuario usuario = new Usuario();
				
				usuario.setNome(nomeField.getText());
				usuario.setLogin(loginField.getText());
				usuario.setSenha(senhaField.getText());
				
				Facade.getInstance().inserir(usuario);
				
				limparCampos();
				
				senhaErroLabel.setVisible(false);

				desabilitarCampos();
				
				
				
			} catch (ValidacaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			} else {
			
				senhaErroLabel.setVisible(true);
			}
		}

		if (event.getSource() == sairBtn) {
			System.exit(0);
		}

	}

	@FXML
	void mouseClick(MouseEvent event) {
		
//		if (event.getSource() == cadUserChBox && cadUserChBox.isSelected()) {
//			confimSenhaField.setDisable(true);
//			confimSenhaField.setVisible(false);
//
//			cadUserBtn.setDisable(true);
//			cadUserBtn.setVisible(false);
//
//		}
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public boolean efetuarLogin() {
		
		try {
			Atual.usuario = Facade.getInstance().getBussinessUsuario().
					buscarUsuario(loginField.getText(), senhaField.getText());
//			Atual.usuario.setId(usuario);
			if (Atual.usuario == null) {
				Message.getInstance().viewMessage(AlertType.ERROR, "Erro ao Logar", "O usuário não EXISTE!", "Usuário inexiste");	
				return false;
			}
			
			app.changeStage("Inicio");
			Message.getInstance().viewMessage(AlertType.INFORMATION, "Logado", "Login efetuado", "Logado com sucesso!");
			limparCampos();
			
		} catch (BOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Message.getInstance().
			viewMessage(AlertType.ERROR, "Erro!", "Erro ao Logar!", "Usuário e senha incorretos ou inxexistentes!");
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return false;

	}

	public void limparCampos() {
		
		nomeField.clear();
		loginField.clear();
		senhaField.clear();
		confimSenhaField.clear();
		
		cadUserChBox.setSelected(false);
		
	}
	
	public void desabilitarCampos() {
		nomeField.setDisable(true);
		nomeField.setVisible(false);
		
		confimSenhaField.setDisable(true);
		confimSenhaField.setVisible(false);

		cadUserBtn.setDisable(true);
		cadUserBtn.setVisible(false);
	}
	
//	public static Usuario getUsuario() {
//		return usuario;
//	}
	
}
