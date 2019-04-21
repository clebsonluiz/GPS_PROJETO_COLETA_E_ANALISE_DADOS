package entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pesquisa")
public class Pesquisa extends Entidade{

	@Column
	private String titulo;
	@Column
	private String descricao;
	
	public String getTitulo() {return titulo;}
	public String getDescricao() {return descricao;}
	public void setTitulo(String titulo) {this.titulo = titulo;}
	public void setDescricao(String descricao) {this.descricao = descricao;}
	
}
