package daoJPA;

import javax.persistence.EntityManager;

import model.Usuario;

public class UsuarioDao {
	
	public boolean adicionar(Usuario u){
		EntityManager man = JPAUtil.getJPAUtil().getEntityManager();
		man.getTransaction().begin();
		try{
			man.persist(u);
			man.getTransaction().commit();
			return true;
		}
		catch(Exception e){
		}
		return false;
	}
	
	public boolean alterar(Usuario u){
		EntityManager man = JPAUtil.getJPAUtil().getEntityManager();
		man.getTransaction().begin();
		
		try{
			man.merge(u);
			man.getTransaction().commit();
			return true;
		}
		catch(Exception e){	}
		return false;
	}
	
	public boolean remover(Usuario u){
		EntityManager man = JPAUtil.getJPAUtil().getEntityManager();
		man.getTransaction().begin();
		try{
			u = man.find(Usuario.class, u.getUsuario());
			man.remove(u);
			man.getTransaction().commit();
			return true;
		}
		catch(Exception e){	}
		return false;		
	}
	
	public Usuario consultar(Usuario u){
		EntityManager man = JPAUtil.getJPAUtil().getEntityManager();
		man.getTransaction().begin();
		try{
			u = man.find(Usuario.class, u.getUsuario());
			return u;
		}
		catch(Exception e){	}
		return null;
	}
}
