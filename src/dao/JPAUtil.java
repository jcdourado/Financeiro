package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	private static JPAUtil jpa;
	private static EntityManagerFactory factory;
	private EntityManager man;
	
	private JPAUtil() {
		factory = Persistence.createEntityManagerFactory("financeiro");
	}
	
	public static JPAUtil getJPAUtil(){
		if(jpa == null){
			jpa = new JPAUtil();
		}
		return jpa;
	}
	
	public EntityManager getEntityManager(){
		if(man == null || !man.isOpen()){
			man = factory.createEntityManager();
		}
		return man;
	}
}
