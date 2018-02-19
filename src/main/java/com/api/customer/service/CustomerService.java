package com.api.customer.service;

import java.util.List;

import com.api.customer.model.CustomerDTO;

public interface CustomerService {
	
	List<CustomerDTO> getAllCustomerDAOs();
	
	CustomerDTO getCustomerDAObyFirstname(String firstname);
	
	CustomerDTO createNewCustomer(CustomerDTO customerDTO);
	
	CustomerDTO updateExistingCustomer(Long id, CustomerDTO customerDTO);

}
