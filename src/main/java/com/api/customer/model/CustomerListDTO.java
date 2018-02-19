package com.api.customer.model;

import java.util.List;

import lombok.Data;

@Data
public class CustomerListDTO {
	
	List<CustomerDTO> customers;
	
	public List<CustomerDTO> getCustomers() {
		return customers;
	}


	public void setCustomers(List<CustomerDTO> customers) {
		this.customers = customers;
	}




	public CustomerListDTO(List<CustomerDTO> customers) {
		this.customers = customers;
	}


	public CustomerListDTO() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customers == null) ? 0 : customers.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerListDTO other = (CustomerListDTO) obj;
		if (customers == null) {
			if (other.customers != null)
				return false;
		} else if (!customers.equals(other.customers))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "CustomerListDTO [customers=" + customers + "]";
	}


	

}
