package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.Service;

public class ServiceDao {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("lion");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et = em.getTransaction();

	public Service saveService(Service service) {

		if (service != null) {
			et.begin();
			em.persist(service);
			et.commit();
		} else {
			et.rollback();
		}
		return service;
	}

	public Service findService(int id) {

		Service service = em.find(Service.class, id);

		if (service != null) {
			return service;
		}
		return null;

	}
	
	public Service updateService(Service service,int id) {
		
		Service exService = em.find(Service.class,id);
		
		if(exService != null) {
			service.setServiceid(id);
			et.begin();
			em.merge(service);
			et.commit();
		}else {
			et.rollback();
		}

		return null;
}
	
public Service deleteService(int id) {
		
		Service service = em.find(Service.class, id );
		if(service != null) {
			et.begin();
			em.remove(service);
			et.commit();
		}else {
			et.rollback();
		}
		return null;
		
	}


}