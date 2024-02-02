
package dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Service {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int serviceid;
	
	private String servicename;
	
	private double servicecostPerDay;
	
	private double serviceCostPerPerson;

	public int getServiceid() {
		return serviceid;
	}

	public void setServiceid(int serviceid) {
		this.serviceid = serviceid;
	}

	public String getServicename() {
		return servicename;
	}

	public void setServicename(String servicename) {
		this.servicename = servicename;
	}

	public double getServicecostPerDay() {
		return servicecostPerDay;
	}

	public void setServicecostPerDay(double servicecostPerDay) {
		this.servicecostPerDay = servicecostPerDay;
	}

	public double getServiceCostPerPerson() {
		return serviceCostPerPerson;
	}

	public void setServiceCostPerPerson(double serviceCostPerPerson) {
		this.serviceCostPerPerson = serviceCostPerPerson;
	}

	@Override
	public String toString() {
		return "Service [serviceid=" + serviceid + ", servicename=" + servicename + ", servicecostPerDay="
				+ servicecostPerDay + ", serviceCostPerPerson=" + serviceCostPerPerson + "]";
	}

}
