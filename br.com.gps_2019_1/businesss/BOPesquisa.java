package businesss;

import java.util.List;

import dao.DAOPesquisa;
import entidade.Pesquisa;
import exceptions.BOException;
import exceptions.DAOException;

public class BOPesquisa extends BO<Pesquisa>{

	public BOPesquisa() {
		super(new DAOPesquisa(), Pesquisa.class);
	}
	
	public List<Pesquisa> getPesquisasUsuario(int id_usuario) throws BOException, DAOException
	{
		if(id_usuario <= 0) throw new BOException("Erro ao consultar pesquisas");
		return ((DAOPesquisa)this.daoT).buscaListaHQLGenerica(Pesquisa.class,
		"select p from Pesquisa as p where p.ativado = true and p.usuario.id = " + id_usuario);
	}
	
	public List<Pesquisa> getPesquisasUsuarioEspecifica(String pesquisa, int id_usuario) throws BOException, DAOException
	{
		if(id_usuario <= 0) throw new BOException("Erro ao consultar pesquisas");
		/**
		 * Para acessar a FK de um objeto em HQL o acesso é pelo atributo da FK na forma 
		 * de objeto, tipo, select p from Pessoa p where p.endereco_fk = 1,
		 * em HQL fica select p from Pessoa p where p.endereco.id = 1
		 * 
		 */
		return ((DAOPesquisa)this.daoT).buscaListaHQLGenerica(Pesquisa.class,
		"select p from Pesquisa as p where p.ativado = true and p.usuario.id = " + id_usuario + " and "
				+ "lower(p.nome) like lower('%" + pesquisa + "%')");
	}

}
