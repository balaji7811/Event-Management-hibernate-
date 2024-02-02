package dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ClientService {
	
	
	@Id
	@GeneratedValue
	private int clientServiceId;
	
	private String clientServiceName;
	
	private double clientServiceCost;
	
	private int clientServiceNoOfDays;
	
	private double clientCostPerPerson;

	public int getClientServiceId() {
		return clientServiceId;
	}

	public void setClientServiceId(int clientServiceId) {
		this.clientServiceId = clientServiceId;
	}

	public String getClientServiceName() {
		return clientServiceName;
	}

	public void setClientServiceName(String clientServiceName) {
		this.clientServiceName = clientServiceName;
	}

	public double getClientServiceCost() {
		return clientServiceCost;
	}

	public void setClientServiceCost(double clientServiceCost) {
		this.clientServiceCost = clientServiceCost;
	}

	public int getClientServiceNoOfDays() {
		return clientServiceNoOfDays;
	}

	public void setClientServiceNoOfDays(int clientServiceNoOfDays) {
		this.clientServiceNoOfDays = clientServiceNoOfDays;
	}

	public double getClientCostPerPerson() {
		return clientCostPerPerson;
	}

	public void setClientCostPerPerson(double clientCostPerPerson) {
		this.clientCostPerPerson = clientCostPerPerson;
	}

	@Override
	public String toString() {
		return "ClientService [clientServiceId=" + clientServiceId + ", clientServiceName=" + clientServiceName
				+ ", clientServiceCost=" + clientServiceCost + ", clientServiceNoOfDays=" + clientServiceNoOfDays
				+ ", clientCostPerPerson=" + clientCostPerPerson + "]";
	}
	
}


