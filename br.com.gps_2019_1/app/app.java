/**
 * 
 */
package app;

import dao.DAODado;
import dao.DAOEstruturaPesquisa;
import dao.DAOPesquisa;
import dao.DAOUsuario;

/**
 * @author ayrtons
 *
 */
public class app {

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

}
