package daoJPA;

import javax.persistence.EntityManager;

import model.Recebimento;

public class RecebimentoDao {
	public boolean adicionar(Recebimento u){
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
	
	public boolean alterar(Recebimento u){
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
	
	public boolean remover(Recebimento u){
		EntityManager man = JPAUtil.getJPAUtil().getEntityManager();
		man.getTransaction().begin();
		try{
			u = man.find(Recebimento.class, u.getId());
			man.remove(u);
			man.getTransaction().commit();
			return true;
		}
		catch(Exception e){	}
		return false;		
	}
}
