package businesss;

import java.util.List;

import dao.DAOEstruturaPesquisa;
import entidade.EstruturaPesquisa;
import exceptions.BOException;
import exceptions.DAOException;

public class BOEstrutura extends BO<EstruturaPesquisa>{

	public BOEstrutura() {
		super(new DAOEstruturaPesquisa(), EstruturaPesquisa.class);
	}

	public List<EstruturaPesquisa> getEstruturasPesquisa(int id_pesquisa) throws BOException, DAOException
	{
		if(id_pesquisa <= 0) throw new BOException("Erro ao consultar estruturas");
		return ((DAOEstruturaPesquisa)this.daoT).buscaListaHQLGenerica(EstruturaPesquisa.class,
		"select e from EstruturaPesquisa as e where e.ativado = true and e.pesquisa.id = " + id_pesquisa);
	}
	
	public List<EstruturaPesquisa> getPesquisasUsuarioEspecifica(String estrutura, int id_pesquisa) throws BOException, DAOException
	{
		if(id_pesquisa <= 0) throw new BOException("Erro ao consultar estruturas");
		/**
		 * Para acessar a FK de um objeto em HQL o acesso é pelo atributo da FK na forma 
		 * de objeto, tipo, select p from Pessoa p where p.endereco_fk = 1,
		 * em HQL fica select p from Pessoa p where p.endereco.id = 1
		 * 
		 */
		return ((DAOEstruturaPesquisa)this.daoT).buscaListaHQLGenerica(EstruturaPesquisa.class,
		"select e from EstruturaPesquisa as e where e.ativado = true and e.pesquisa.id = " + id_pesquisa + " and "
			+ "lower(e.titulo_estrutura) like lower('%" + estrutura + "%') "
			+ "OR "
			+ "lower(e.col_1_nome_familia) like lower('%" + estrutura + "%') "
			+ "OR "
			+ "lower(e.col_2_nome) like lower('%" + estrutura + "%') "
			+ "OR "
			+ "lower(e.col_3_valor) like lower('%" + estrutura + "%') "
			+ "OR "
			+ "lower(e.categoria_dados) like lower('%" + estrutura + "%') ");
	}
}
