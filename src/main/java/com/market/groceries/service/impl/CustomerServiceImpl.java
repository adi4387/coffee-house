package com.market.groceries.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.market.groceries.constant.CoffeeHouseConstants;
import com.market.groceries.dto.CustomerDTO;
import com.market.groceries.model.Customer;
import com.market.groceries.model.key.CustomerId;
import com.market.groceries.repository.CustomerRepository;
import com.market.groceries.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public String addCustomer(CustomerDTO customerDTO) {
		Customer customer = getCustomerFromCustomerDTO(customerDTO);
		try {
			customer = customerRepository.save(customer);
			return customer.getCustomerId().getFirstName() + " successfully added in the database"; 
		} catch(Exception e){
			logger.error("Exception while adding a new customer. ", e);
		}
		
		return CoffeeHouseConstants.CUSTOMER_ADD_ERROR + " with name " + customerDTO.getFirstName();
	}

	public Customer getCustomerFromCustomerDTO(CustomerDTO customerDto) {
		Customer customer = new  Customer();
		CustomerId customerId = new CustomerId();
		customerId.setFirstName(customerDto.getFirstName());
		customerId.setLastName(customerDto.getLastName());
		customerId.setPhoneNumber(customerDto.getPhoneNumber());
		customer.setCustomerId(customerId);
		customer.setEmailId(customerDto.getEmailId());
		
		return customer;
	}
}
