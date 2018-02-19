package com.api.customer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.customer.model.CustomerDTO;
import com.api.customer.model.CustomerListDTO;
import com.api.customer.service.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="Customer APIs")
@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

	public static final String BASE_URL = "api/v1/customers/";
	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@ApiOperation(value="Get all customers", notes="Getting all customers from DB")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public CustomerListDTO getallCustomerDAOs(){
		
		return new CustomerListDTO(customerService.getAllCustomerDAOs());
	}
	
	@GetMapping("{firstname}")
	@ResponseStatus(HttpStatus.OK)
	
	public CustomerDTO getCustomerDAObyFirstname(@PathVariable String firstname){
		return customerService.getCustomerDAObyFirstname(firstname);
		
		
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CustomerDTO createNewCustomerDAO(@RequestBody CustomerDTO customerDTO){
		return customerService.createNewCustomer(customerDTO);
	}
	
	@PutMapping ("{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO){
		return customerService.updateExistingCustomer(id, customerDTO);	
	}
	
}
