package facade;

import businesss.BODado;
import businesss.BOEstrutura;
import businesss.BOPesquisa;
import businesss.BOUsuario;
import entidade.Dado;
import entidade.Entidade;
import entidade.EstruturaPesquisa;
import entidade.Pesquisa;
import entidade.Usuario;
import exceptions.ValidacaoException;

public class Facade {

	private BOUsuario bussinessUsuario;
	private BOPesquisa bussinessPesquisa;
	private BOEstrutura bussinessEstrutura;
	private BODado bussinessDado;
	
	
	private static Facade instance;
	
	public static Facade getInstance()
	{
		if(instance == null)
			instance = new Facade();
		return instance;
	}

	private Facade() {
		this.bussinessUsuario = new BOUsuario();
		this.bussinessPesquisa = new BOPesquisa();
		this.bussinessEstrutura = new BOEstrutura();
		this.bussinessDado = new BODado();
	}
	
	public <T extends Entidade> Entidade inserir(T t) throws ValidacaoException
	{
		if(t instanceof Usuario)
			return bussinessUsuario.inserir((Usuario)t);
		else if(t instanceof Pesquisa)
			return bussinessPesquisa.inserir((Pesquisa)t);
		else if(t instanceof EstruturaPesquisa)
			return bussinessEstrutura.inserir((EstruturaPesquisa)t);
		else if(t instanceof Dado)
			return bussinessDado.inserir((Dado)t);
		return null;
	}
	
	public <T extends Entidade> Entidade atualizar(T t) throws ValidacaoException
	{
		if(t instanceof Usuario)
			return bussinessUsuario.atualizar((Usuario)t);
		else if(t instanceof Pesquisa)
			return bussinessPesquisa.atualizar((Pesquisa)t);
		else if(t instanceof EstruturaPesquisa)
			return bussinessEstrutura.atualizar((EstruturaPesquisa)t);
		else if(t instanceof Dado)
			return bussinessDado.atualizar((Dado)t);
		return null;
	}
	
	public <T extends Entidade> void deletar(T t) throws ValidacaoException
	{
		if(t instanceof Usuario)
			bussinessUsuario.deletar((Usuario)t);
		else if(t instanceof Pesquisa)
			bussinessPesquisa.deletar((Pesquisa)t);
		else if(t instanceof EstruturaPesquisa)
			bussinessEstrutura.deletar((EstruturaPesquisa)t);
		else if(t instanceof Dado)
			bussinessDado.deletar((Dado)t);
	}
	
	public <T extends Entidade> Entidade buscar(int id, Class<T> classe) throws ValidacaoException
	{
		if(classe.getSimpleName().equals(Usuario.class.getSimpleName()))
			return bussinessUsuario.buscar(id);
		else if(classe.getSimpleName().equals(Pesquisa.class.getSimpleName()))
			return bussinessPesquisa.buscar(id);
		else if(classe.getSimpleName().equals(EstruturaPesquisa.class.getSimpleName()))
			return bussinessEstrutura.buscar(id);
		else if(classe.getSimpleName().equals(Dado.class.getSimpleName()))
			return bussinessDado.buscar(id);
		return null;
	}

	public BOUsuario getBussinessUsuario() {
		return bussinessUsuario;
	}

	public BOPesquisa getBussinessPesquisa() {
		return bussinessPesquisa;
	}

	public BOEstrutura getBussinessEstrutura() {
		return bussinessEstrutura;
	}

	public BODado getBussinessDado() {
		return bussinessDado;
	}
	
}
