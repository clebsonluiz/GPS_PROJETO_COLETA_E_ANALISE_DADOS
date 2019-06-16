package entidade;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	
	@Column(name = "data_inicio")
	private LocalDate dataInicio;
	
	@Column(name = "data_fim")
	private LocalDate dataFim;
	
	@Transient
	private List<EstruturaPesquisa> estruturaPesquisas;
	
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
	
	/**
	 * @return the dataInicio
	 */
	public LocalDate getDataInicio() {
		return dataInicio;
	}
	/**
	 * @param dataInicio the dataInicio to set
	 */
	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}
	/**
	 * @return the dataFim
	 */
	public LocalDate getDataFim() {
		return dataFim;
	}
	/**
	 * @param dataFim the dataFim to set
	 */
	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}
	/**
	 * @return the estruturaPesquisas
	 */
	public List<EstruturaPesquisa> getEstruturaPesquisas() {
		return estruturaPesquisas;
	}
	/**
	 * @param estruturaPesquisas the estruturaPesquisas to set
	 */
	public void setEstruturaPesquisas(List<EstruturaPesquisa> estruturaPesquisas) {
		this.estruturaPesquisas = estruturaPesquisas;
	}
	
}
