package com.api.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;


import com.api.customer.model.CustomerDAO;

public interface CustomerRepository extends JpaRepository<CustomerDAO, Long>{
	
	
	
	CustomerDAO findByFirstname (String firstname);
	
	@Procedure
	CustomerDAO findByFName (String firstname);
	
	@Procedure(procedureName="addCustomer")
	Long addCustomer (String firstname, String lastname);
	
	@Procedure(procedureName="updateCustomer")
	Long updateCustomer(Long id, String firstname, String lastname);

}
