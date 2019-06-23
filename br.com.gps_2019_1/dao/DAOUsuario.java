package dao;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import entidade.Usuario;
import util.SqlUtil;

public class DAOUsuario extends DAO<Usuario>{

	
//	public Usuario searchLoginUsuario(String login, String senha)  {
//		// TODO Auto-generated method stub
//		
//		try {
//			TypedQuery<Usuario> typedQuery = createEntityManager().createQuery(SqlUtil.BUSCA_LOGIN);
//			typedQuery.setParameter("login", login);
//			typedQuery.setParameter("senha", senha);			
//			return typedQuery.getSingleResult();
//			
//			
//		}catch (NoResultException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			System.err.println(e.getMessage());
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			System.err.println(e.getMessage());
//		}
//	}
	
}
