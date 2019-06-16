/**
 * 
 */
package entidade;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author ayrton
 *
 */
@Entity
@Table(name = "usuario")
public class Usuario extends Entidade{

	@Column(nullable = false)
	private String nome;
	@Column(unique = true, nullable = false)
	private String login;
	@Column(nullable = false)
	private String senha;
	
	@Transient
	private List<Pesquisa> pesquisas;
	
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}
	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	/**
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}
	/**
	 * @param senha the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}
	/**
	 * @return the pesquisas
	 */
	public List<Pesquisa> getPesquisas() {
		return pesquisas;
	}
	/**
	 * @param pesquisas the pesquisas to set
	 */
	public void setPesquisas(List<Pesquisa> pesquisas) {
		this.pesquisas = pesquisas;
	}
	
	
}
