package businesss;

import java.util.List;

import dao.DAODado;
import entidade.Dado;
import exceptions.BOException;
import exceptions.DAOException;

public class BODado extends BO<Dado>{

	public BODado() {
		super(new DAODado(), Dado.class);
	}

	public List<Dado> getEstruturasPesquisa(int id_estrutura) throws BOException, DAOException
	{
		if(id_estrutura <= 0) throw new BOException("Erro ao consultar dados");
		return ((DAODado)this.daoT).buscaListaHQLGenerica(Dado.class,
		"select d from dado as d where d.ativado = true and d.estruturaPesquisa.id = " + id_estrutura);
	}
	
	public List<Dado> getPesquisasUsuarioEspecifica(String dados, int id_estrutura) throws BOException, DAOException
	{
		if(id_estrutura <= 0) throw new BOException("Erro ao consultar dados");
		/**
		 * Para acessar a FK de um objeto em HQL o acesso � pelo atributo da FK na forma 
		 * de objeto, tipo, select p from Pessoa p where p.endereco_fk = 1,
		 * em HQL fica select p from Pessoa p where p.endereco.id = 1
		 * 
		 */
		return ((DAODado)this.daoT).buscaListaHQLGenerica(Dado.class,
		"select d from Dado as d where d.ativado = true and d.estruturaPesquisa.id = " + id_estrutura + " and "
			+ "lower(d.col_1_nome_familia) like lower('%" + dados + "%') "
			+ "OR "
			+ "lower(d.col_2_nome) like lower('%" + dados + "%') "
			+ "OR "
			+ "lower(d.col_3_valor) like lower('%" + dados + "%') ");
	}
}
