package entidade;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Entidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private boolean ativado = true;
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public boolean isAtivado() {return ativado;}
	public void setAtivado(boolean ativado) {this.ativado = ativado;}
	
}
