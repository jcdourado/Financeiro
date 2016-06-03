package daoJPA;

import javax.persistence.EntityManager;

import model.Conta;

public class ContaDao {
	public boolean adicionar(Conta u){
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
	
	public boolean alterar(Conta u){
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
	
	public boolean remover(Conta u){
		EntityManager man = JPAUtil.getJPAUtil().getEntityManager();
		man.getTransaction().begin();
		try{
			u = man.find(Conta.class, u.getId());
			man.remove(u);
			man.getTransaction().commit();
			return true;
		}
		catch(Exception e){	}
		return false;		
	}
}
