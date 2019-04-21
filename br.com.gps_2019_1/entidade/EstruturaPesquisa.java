package entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "estrutura_pesquisa")
public class EstruturaPesquisa extends Entidade{

	@Column
	private String col_1_nome_familia;
	@Column
	private String col_2_nome;
	@Column
	private String col_3_valor;
	@Column
	private String categoria_dados;
	@OneToOne
	@JoinColumn(name = "pesquisa_id")
	private Pesquisa pesquisa;
	
	public String getCol_1_nome_familia() {return col_1_nome_familia;}
	public String getCol_2_nome() {return col_2_nome;}
	public String getCol_3_valor() {return col_3_valor;}
	public String getCategoria_dados() {return categoria_dados;}

	public Pesquisa getPesquisa() {return pesquisa;}
	
	public void setPesquisa(Pesquisa pesquisa) {this.pesquisa = pesquisa;}
	public void setCol_1_nome_familia(String col_1_nome_familia) {this.col_1_nome_familia = col_1_nome_familia;}
	public void setCol_2_nome(String col_2_nome) {this.col_2_nome = col_2_nome;}
	public void setCol_3_valor(String col_3_valor) {this.col_3_valor = col_3_valor;}
	public void setCategoria_dados(String categoria_dados) {this.categoria_dados = categoria_dados;}
	
}
