package entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pesquisa")
public class Pesquisa extends Entidade{

	@Column
	private String titulo;
	@Column
	private String descricao;
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	public String getTitulo() {return titulo;}
	public String getDescricao() {return descricao;}
	public void setTitulo(String titulo) {this.titulo = titulo;}
	public void setDescricao(String descricao) {this.descricao = descricao;}
	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}	
	
}
