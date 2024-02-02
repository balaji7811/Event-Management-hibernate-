package dao;

import javax.persistence.EntityManager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.Client;
import dto.ClientEvent;

public class ClientEventDao {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("lion");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et = em.getTransaction();

	public ClientEvent saveClientEvent(ClientEvent clientevent) {

		if (clientevent != null) {
			et.begin();
			em.persist(clientevent);
			et.commit();
		} else {
			et.rollback();
		}
		return clientevent;
	}

	public ClientEvent findClientEvant(int id) {

		ClientEvent clientevent = em.find(ClientEvent.class, id);

		if (clientevent != null) {
			return clientevent;
		}
		return null;

	}
	
	public ClientEvent updateClientEvent(ClientEvent clientevent,int id) {
		
		ClientEvent exclientevent = em.find(ClientEvent.class,id);
		
		if(exclientevent != null) {
			clientevent.setClientEventId(id);
			et.begin();
			em.merge(clientevent);
			et.commit();
		}else {
			et.rollback();
		}

		return null;
}
	
public ClientEvent deleteClientEvent(int id) {
		
		ClientEvent clientevent = em.find(ClientEvent.class, id );
		if(clientevent != null) {
			et.begin();
			em.remove(clientevent);
			et.commit();
		}else {
			et.rollback();
		}
		return null;
		
	}


}