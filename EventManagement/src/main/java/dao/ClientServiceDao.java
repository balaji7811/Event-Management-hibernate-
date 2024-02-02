package dao;

import javax.persistence.EntityManager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.ClientService;

public class ClientServiceDao {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("lion");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et = em.getTransaction();

	public ClientService saveClientService(ClientService clientService) {

		if (clientService != null) {
			et.begin();
			em.persist(clientService);
			et.commit();
		} else {
			et.rollback();
		}
		return clientService;
	}

	public ClientService findClientEvant(int id) {

		ClientService clientService = em.find(ClientService.class, id);

		if (clientService != null) {
			return clientService;
		}
		return null;

	}

	public ClientService updateClientService(ClientService ClientService, int id) {

		ClientService exClientService = em.find(ClientService.class, id);

		if (exClientService != null) {
			ClientService.setClientServiceId(id);
			et.begin();
			em.merge(ClientService);
			et.commit();
		} else {
			et.rollback();
		}

		return null;
	}

	public ClientService deleteClientService(int id) {

		ClientService clientService = em.find(ClientService.class, id);
		if (clientService != null) {
			et.begin();
			em.remove(clientService);
			et.commit();
		} else {
			et.rollback();
		}
		return null;

	}

}
