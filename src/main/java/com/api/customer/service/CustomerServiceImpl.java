package com.api.customer.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.stereotype.Service;

import com.api.customer.controller.CustomerController;
import com.api.customer.mapper.CustomerMapper;
import com.api.customer.model.CustomerDAO;
import com.api.customer.model.CustomerDTO;
import com.api.customer.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	
	private final CustomerMapper customerMapper;
	
	private final CustomerRepository customerRepository;
	
	
	 /*private static EntityManagerFactory factory = null;
	    private static EntityManager entityManager = null;
	
	    @BeforeClass
	    public static void init() {
	        factory = Persistence.createEntityManagerFactory("jpa-db");
	       entityManager = factory.createEntityManager();
	    }*/
	    
	/*public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
		this.customerMapper = customerMapper;
		this.customerRepository = customerRepository;
	}*/
	
	public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
		this.customerMapper = customerMapper;
		this.customerRepository = customerRepository;
	}

	@Override
	public List<CustomerDTO> getAllCustomerDAOs() {
		
		return customerRepository
				.findAll()
				.stream()
				.map(customerMapper::customerDAOtoCustomerDTO)
				.collect(Collectors.toList());
		
	}

	@Override
	public CustomerDTO getCustomerDAObyFirstname(String firstname) {
		
		CustomerDTO returnDTO = customerMapper.customerDAOtoCustomerDTO(customerRepository.findByFirstname(firstname));
		returnDTO.setUrl(CustomerController.BASE_URL + returnDTO.getId());
		
		//CustomerDAO daoobject = customerRepository.findByFName("Vivek");
		//CustomerDTO returnDTO = customerMapper.customerDAOtoCustomerDTO(daoobject);
		
		/*StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("FIND_CUST_BY_FNAME", CustomerDAO.class)
												.registerStoredProcedureParameter("FNAME", String.class, ParameterMode.IN).setParameter("FNAME", firstname);
		
	
		CustomerDAO customerDAO = (CustomerDAO) storedProcedure.getSingleResult();
		CustomerDTO returnDTO = customerMapper.customerDAOtoCustomerDTO(customerDAO);*/
		
		
		returnDTO.setUrl(CustomerController.BASE_URL + returnDTO.getId());
		return returnDTO;
	}
	
	
	

	@Override
	public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
		return saveAndReturnCustomer(customerMapper.customerDTOtoCustomerDAO(customerDTO));
	}

	private CustomerDTO saveAndReturnCustomer(CustomerDAO customerDAO) {
		
		/*CustomerDAO toSaveCustomer = customerRepository.save(customerDAO);
		CustomerDTO returnDTO = customerMapper.customerDAOtoCustomerDTO(toSaveCustomer);
		returnDTO.setUrl(CustomerController.BASE_URL + toSaveCustomer.getId());
		return returnDTO;*/
		
		String lastName = customerDAO.getLastname();
		String firstName = customerDAO.getFirstname();
		Long custID = customerRepository.addCustomer(firstName, lastName);
		
		CustomerDTO returnDTO = new CustomerDTO();
		returnDTO.setId(custID);
		returnDTO.setLastname(lastName);
		returnDTO.setFirstname(firstName);
		returnDTO.setUrl(CustomerController.BASE_URL+custID);
		
		return returnDTO;
		
		
	}
	
	@Override
	public CustomerDTO updateExistingCustomer(Long id, CustomerDTO customerDTO) {
		CustomerDAO toSaveCustomer = customerMapper.customerDTOtoCustomerDAO(customerDTO);
		toSaveCustomer.setId(id);
		String lastName = toSaveCustomer.getLastname();
		String firstName = toSaveCustomer.getFirstname();
		Long inID = toSaveCustomer.getId();
		Long custID = customerRepository.updateCustomer(inID, firstName, lastName);
		CustomerDTO returnDTO = new CustomerDTO();
		returnDTO.setId(custID);
		returnDTO.setLastname(lastName);
		returnDTO.setFirstname(firstName);
		returnDTO.setUrl(CustomerController.BASE_URL+custID);
		return returnDTO;
	}
	
/*	@AfterClass
    public static void destroy() {

        if (entityManager != null) {
            entityManager.close();
        }
        if (factory != null) {
            factory.close();
        }
    }*/

}
