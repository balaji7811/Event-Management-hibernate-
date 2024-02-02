package dto;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ClientEvent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int clientEventId;
	
	private int clientEventNoOfPeople;
	
	private LocalDate startDate;
	
	private int  clientEventNoODays;
	
	private String clientEventLocation;
	
	private double clientEventCost;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Client client;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<ClientService> clientservices;
	
	private EventType eventType;

	public int getClientEventId() {
		return clientEventId;
	}

	public void setClientEventId(int clientEventId) {
		this.clientEventId = clientEventId;
	}

	public int getClientEventNoOfPeople() {
		return clientEventNoOfPeople;
	}

	public void setClientEventNoOfPeople(int clientEventNoOfPeople) {
		this.clientEventNoOfPeople = clientEventNoOfPeople;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public int getClientEventNoODays() {
		return clientEventNoODays;
	}

	public void setClientEventNoODays(int clientEventNoODays) {
		this.clientEventNoODays = clientEventNoODays;
	}

	public String getClientEventLocation() {
		return clientEventLocation;
	}

	public void setClientEventLocation(String clientEventLocation) {
		this.clientEventLocation = clientEventLocation;
	}

	public double getClientEventCost() {
		return clientEventCost;
	}

	public void setClientEventCost(double clientEventCost) {
		this.clientEventCost = clientEventCost;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<ClientService> getClientservices() {
		return clientservices;
	}

	public void setClientservices(List<ClientService> clientservices) {
		this.clientservices = clientservices;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	@Override
	public String toString() {
		return "ClientEvent [clientEventId=" + clientEventId + ", clientEventNoOfPeople=" + clientEventNoOfPeople
				+ ", startDate=" + startDate + ", clientEventNoODays=" + clientEventNoODays + ", clientEventLocation="
				+ clientEventLocation + ", clientEventCost=" + clientEventCost + ", client=" + client
				+ ", clientservices=" + clientservices + ", eventType=" + eventType + "]";
	}

     
	
    
}
