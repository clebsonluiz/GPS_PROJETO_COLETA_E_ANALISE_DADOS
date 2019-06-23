/**
 * 
 */
package util;

/**
 * @author ayrton
 *
 */
public class SqlUtil {

	public static final String BUSCA_LOGIN = "select u from Usuario u where u.login = :login and u.senha = :senha";
	
}
