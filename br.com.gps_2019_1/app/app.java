/**
 * 
 */
package app;

import dao.DAODado;
import dao.DAOEstruturaPesquisa;
import dao.DAOPesquisa;
import dao.DAOUsuario;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author ayrtons
 *
 */
public class app extends Application{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Pimba");
		
		new DAOUsuario();
		new DAOPesquisa();
		new DAOEstruturaPesquisa();
		new DAODado();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		super.stop();
		System.exit(0);
	}

}
