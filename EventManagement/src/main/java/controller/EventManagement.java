package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dao.AdminDao;
import dao.ClientDao;
import dao.ClientEventDao;
import dao.ClientServiceDao;
import dao.ServiceDao;
import dto.Admin;
import dto.Client;
import dto.ClientEvent;
import dto.ClientService;
import dto.EventType;
import dto.Service;

public class EventManagement {

	EntityManagerFactory emf=Persistence.createEntityManagerFactory("lion");
	EntityManager em=emf.createEntityManager();
	EntityTransaction et=em.getTransaction();
	
	
		AdminDao adao=new AdminDao();
		ServiceDao sdao=new ServiceDao();
		ClientDao cdao=new ClientDao();
		ClientEventDao cedao=new ClientEventDao();
		ClientServiceDao csdao=new ClientServiceDao();
		Scanner sc=new Scanner(System.in);
		
		
		public static void main(String[] args) {
		
			EventManagement emt=new EventManagement();
		//System.out.println(emt.saveAdmin());
			//System.out.println(emt.loginAdmin());
		//System.out.println(emt.saveService());
			System.out.println(emt.updateService());
		//	System.out.println(emt.deleteService());
			//System.out.println(emt.signupClient());
			//System.out.println(emt.loginClient());
			//System.out.println(emt.createClientEvent());
			//System.out.println(emt.saveClientService());
			//System.out.println(emt.removeEventService());
			
	}
	
	public Admin saveAdmin()
	{
		Admin admin=new Admin();
		Scanner s=new Scanner(System.in);
		System.out.println("Enter admin name");
		admin.setAdminName(s.next());
		System.out.println("Enter admin mail");
		admin.setAdminMail(s.next());
		System.out.println("Enter admin password");
		admin.setAdminPassword(s.next());
		System.out.println("Enter admin contact number");
		admin.setAdminContact(s.nextLong());
		
		return adao.saveAdmin(admin);		
	}
	
	public Admin loginAdmin()
	{
		Scanner s=new Scanner(System.in);
		//System.out.println("to get a login details enter your's");
		System.out.println("enter email");
		String email=s.next();
		System.out.println("enter password");
		String pass=s.next();
		
		String jpql= "select a from Admin a";
		Query query=em.createQuery(jpql);
		
		List<Admin> admins=(List<Admin>) query.getResultList();
	    
		for(Admin a : admins)
		{
			if(a.getAdminMail().equals(email))
			{
				return a;
			}
		}
		return null;	
	}
	
	public Service saveService()
	{
		System.out.println("admin need to enter first");
		Admin admin= loginAdmin();
		if(admin!=null)
		{
			Service serv=new Service();
			Scanner s=new Scanner(System.in);
			System.out.println("enter service name");
			serv.setServicename(s.next());
			System.out.println("enter service cost per person");
			serv.setServiceCostPerPerson(s.nextDouble());
			System.out.println("enter service cost per day");
			serv.setServicecostPerDay(s.nextDouble());
			Service savedService=sdao.saveService(serv);
			admin.getServices().add(savedService);
			adao.updateAdmin(admin, admin.getAdminid());
			return serv;
		}
		return null;
	}
	
public List<Service> getAllServices()

{
	System.out.println("Enter Admin credentials to proceed....!");
	Admin exAdmin = loginAdmin();
	if(exAdmin != null)
{
		Query query = em.createQuery("select s from Service s");
	List<Service> services = (List<Service>) query.getResultList();
	return services;
}
return null;
}


public String updateService()
{
	List<Service> services = getAllServices();
	System.out.println("------------------------- Choose serviceId from below to update -------------------------");
	for(Service s : services)
{
		System.out.println("serviceId "+"serviceName "+"cost_per_person "+"cost_per_day");
		System.out.println(" "+s.getServiceid()+" "+s.getServicename()+" "+s.getServiceCostPerPerson()+" "+s.getServicecostPerDay());
}
		int id = sc.nextInt();
		Service toUpdated = sdao.findService(id);
		
		System.out.println("Enter updated cost per person");
		double costPerPerson = sc.nextDouble();
		
		System.out.println("Enter updated cost per day");
		double costPerDay = sc.nextDouble();
		
		toUpdated.setServiceCostPerPerson(costPerPerson);
		toUpdated.setServicecostPerDay(costPerDay);
		
		Service updated = sdao.updateService(toUpdated, id);

    if(updated != null)
	{
	return "Service update success....!";
	}
 // return "Service update failed....!"; 
	return null;
}

	
public String deleteService()
{
Admin exAdmin = loginAdmin();
if(exAdmin != null)
{
	List<Service> services = getAllServices();
	System.out.println("------------------------- Choose serviceId from below to delete -------------------------");
	for(Service s : services)
{
	System.out.println("serviceId "+"serviceName "+"cost_per_person "+"cost_per_day");
	System.out.println(" "+s.getServiceid()+" "+s.getServicename()+" "+s.getServiceCostPerPerson()+" "+s.getServicecostPerDay());
	}
  int id = sc.nextInt();
  List<Service> newList = new ArrayList<Service>();
  
for(Service s : services)
{
  if(s.getServiceid() != id)
{
     newList.add(s);
   }
   }
		exAdmin.setServices(newList);
		adao.updateAdmin(exAdmin, exAdmin.getAdminid());
		Service deleted = sdao.deleteService(id); 
if(deleted != null)
{
	 return "Service deleted success....";
}
}
  return "Service deleted failed....";
}

   public Client signupClient()
{
		Client client = new Client();
		System.out.println("Enter the name:");
		client.setClientName(sc.next());
		System.out.println("Enter the contact:");
		client.setClientContact(sc.nextLong());
		System.out.println("Enter the email:");
		client.setClientMail(sc.next());
		System.out.println("Enter the password");
		client.setClientPassword(sc.next());

	return cdao.saveClient(client);

}

public Client loginClient()
{
	System.out.println("-------------------------- CLIENT LOGIN --------------------------");
	System.out.println("Enter the email:");
	String email = sc.next();
	System.out.println("Enter the password:");
	String password = sc.next();
	String jpql = "select c from Client c";

Query query = em.createQuery(jpql);

List<Client> clients = (List<Client>) query.getResultList();
for(Client c : clients)
{
  if(c.getClientMail().equals(email))
{
   return c;
}
}
 return null;
}


public Client createClientEvent()
{
	System.out.println("-------------------------- CREATE YOUR EVENTS HERE --------------------------");
	Client client = loginClient();
	
	if(client != null)
	{
		ClientEvent clientEvent = new ClientEvent();
		System.out.println("Choose the event:");
		
		EventType eventType[] = EventType.values();
		int i = 1;
		for(EventType et : eventType)
		{
			System.out.println(i++ +"."+ et);
		}
		
		int e = sc.nextInt();
		if(e == 1)
		{
			clientEvent.setEventType(EventType.Marrige);
		}
		if(e == 2)
		{
			clientEvent.setEventType(EventType.Engagement);
		}
		if(e == 3)
		{
			clientEvent.setEventType(EventType.BirthDay);
		}
		if(e == 4)
		{
			clientEvent.setEventType(EventType.Anniversary);
		}
		if(e == 5)
		{
			clientEvent.setEventType(EventType.babyShower);
		}
		if(e == 6)
		{
			clientEvent.setEventType(EventType.Reunion);
		}
		if(e == 7)
		{
			clientEvent.setEventType(EventType.NamingCeremony);
		}
		if(e == 8)
		{
			clientEvent.setEventType(EventType.BachelorParty);
		}
		
		System.out.println("Enter number of people:");
		clientEvent.setClientEventNoOfPeople(sc.nextInt());
		System.out.println("Enter number of days:");
		clientEvent.setClientEventNoODays(sc.nextInt());
		System.out.println("Enter the event location:");
		clientEvent.setClientEventLocation(sc.next());
		
		ClientEvent savedClientEvent = cedao.saveClientEvent(clientEvent);
		
		clientEvent.setClient(client);
		client.getEvents().add(savedClientEvent);
		Client c = cdao.updateClient(client, client.getClientid());
		if(c != null)
		{
			System.out.println("your event successfully created...!");
		}
		else
			System.out.println("Event created failed...!");
		
		return c;
	}
	return null;	
}

public Client saveClientService()
{
	Client client = loginClient();
	
	if(client != null)
	{
		List<Service> adminServices = getAllServices(); 
		System.out.println("------------------------- A to Z services -------------------------");
        System.out.println();
		
		System.out.println("serviceId     "+"serviceName     "+"cost_per_person     "+"cost_per_day");
		for(Service s : adminServices)
		{
			System.out.println("  "+s.getServiceid()+"          "+s.getServicename()+"          "+s.getServiceCostPerPerson()+"          "+s.getServicecostPerDay());
		}
		System.out.println("How many services are you want from above services?");
		int noOfServices = sc.nextInt();
		System.out.println("You're selected "+noOfServices+" services..!");
		
		List<Service> clientServices = new ArrayList<>();
		
		for(int j=1;j<=noOfServices;j++)
		{
			System.out.println("Confirm your service-"+j+" to enter the service number from above services");
			int id = sc.nextInt();
			
			Service service = sdao.findService(id);
			
			Query query = em.createQuery("select ce from ClientEvent ce");
			List<ClientEvent> clientEvents = (List<ClientEvent>) query.getResultList();
			
			ClientService clientService = new ClientService();
			for(ClientEvent clientEvent : clientEvents)
			{
				if(clientEvent.getClient().getClientid() == (client.getClientid()))
				{
					clientService.setClientServiceName(service.getServicename());
					clientService.setClientCostPerPerson(service.getServiceCostPerPerson());
					clientService.setClientServiceNoOfDays(clientEvent.getClientEventNoODays());
					
					double totalCostOfAllPeoples = clientEvent.getClientEventNoOfPeople()*service.getServiceCostPerPerson();
					double totalCostOfAllDays = clientEvent.getClientEventNoODays()*service.getServicecostPerDay();
					
					clientService.setClientServiceCost(totalCostOfAllPeoples+totalCostOfAllDays);
					clientServices.add(service);
					csdao.saveClientService(clientService);
					double clientEventCost = clientEvent.getClientEventCost()+clientService.getClientServiceCost();
					clientEvent.setClientEventCost(clientEventCost);						
					cedao.updateClientEvent(clientEvent, clientEvent.getClientEventId());
				}
			}
		}
		return client;
	}
	return null;
}


}
