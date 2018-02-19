package com.api.customer.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.api.customer.model.CustomerDAO;
import com.api.customer.model.CustomerDTO;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface CustomerMapper {
	
	CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
	
	@Mapping(target = "url", ignore = true)
	CustomerDTO customerDAOtoCustomerDTO(CustomerDAO customerDAO);
	
	CustomerDAO customerDTOtoCustomerDAO(CustomerDTO customerDTO);

			
}
