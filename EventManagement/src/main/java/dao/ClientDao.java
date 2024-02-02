package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.Client;

public class ClientDao {
	
	public static void main(String[] args) {
		
	}
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("lion");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et = em.getTransaction();
	
	public Client saveClient(Client client) {
		if(client != null) {
			
			et.begin();
			em.persist(client);
			et.commit();
		}
		else {
		 et.rollback();
		}
		return client;
	
	}
	
	public Client findClient(int clientid) {
		
		Client client = em.find(Client.class, clientid);
		
		if(client!=null) {
			return client;
			
		}
		return null;
	}
	
	public Client updateClient(Client client , int clientid) {
		
		Client exclient = em.find(Client.class, clientid);
		if(exclient !=null) {
			et.begin();
			em.merge(exclient);
			et.commit();
			return exclient;
		}
		return null;
	}
	public Client deleteClient(int clientid) {
		
		Client client = em.find(Client.class, clientid);
		if(client != null) {
			et.begin();
			em.remove(client);
			et.commit();
		}else {
			et.rollback();
		}
		return null;
		
	}

}
