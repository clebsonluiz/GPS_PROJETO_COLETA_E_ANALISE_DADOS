package businesss;

import java.util.List;

import dao.DAO;
import entidade.Entidade;
import exceptions.BOException;
import exceptions.DAOException;

public abstract class BO<T extends Entidade>{

	protected DAO<T> daoT;
	protected Class<T> classe;
	
	public BO(DAO<T> daoT, Class<T> classe) {
		this.daoT = daoT;
		this.classe = classe;
	}
	
	public T inserir(T t) throws BOException, DAOException {
		validacaoInsercao(t);
		return this.daoT.inserir(t);
	};
	
	public T atualizar(T t) throws BOException, DAOException {
		if(t == null || t.getId() <= 0)
			throw new BOException("Não é possivel atualizar "+ t.getClass().getSimpleName());
		validacaoAtualizacao(t);
		return this.daoT.atualizar(t);
	};
	
	public T buscar(int id) throws BOException, DAOException {
		if(id <= 0)
			throw new BOException("Erro ao consultar por Id");
		return this.daoT.buscar(classe, id);
	};
	
	public void remover(T t) throws BOException, DAOException {
		if(t == null || t.getId() <= 0)
			throw new BOException("Não foi possivel deletar "+ t.getClass().getSimpleName());
		this.daoT.remover(t);
	};
	
	public void deletar(T t) throws BOException, DAOException {
		if(t == null || t.getId() <= 0)
			throw new BOException("Não foi possivel deletar "+ t.getClass().getSimpleName());
		this.daoT.deletar(t);
	};
	
	public T buscaHQL(String s) throws BOException, DAOException {
		validacaoBuscaHQL(s);
		return this.daoT.buscaHQLGenerica(classe, s);
	};
	
	public List<T> buscaHQLList(String s) throws BOException, DAOException {
		validacaoBuscaHQLLista(s);
		return this.daoT.buscaListaHQLGenerica(classe, s);
	};
	
	public List<T> buscarALL() throws BOException, DAOException {
		return this.daoT.buscarAll(classe);
	};
	
	public List<Object[]> buscaListaSQLGenerica(String sql)throws BOException, DAOException {
		return this.daoT.buscaSQLGenerica(sql);
	}
	
	
	protected void validacaoInsercao(T t) throws BOException{
		if(t == null) throw new BOException("Erro ao inserir " + classe.getSimpleName());
		//TODO - Serão sobrescritos na classe concreta
	}
	
	protected void validacaoAtualizacao(T t) throws BOException{
		if(t == null || t.getId() <= 0) throw new BOException("Erro ao atualizar " + classe.getSimpleName());
		//TODO - Serão sobrescritos na classe concreta
	}
	
	
	protected void validacaoBuscaHQL(String s) throws BOException{
		//TODO - Serão sobrescritos na classe concreta
	}
	
	protected void validacaoBuscaHQLLista(String s) throws BOException{
		//TODO - Serão sobrescritos na classe concreta
	}
	
}
