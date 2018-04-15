package com.market.groceries.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.market.groceries.constant.CoffeeHouseConstants;
import com.market.groceries.dto.CustomerDTO;
import com.market.groceries.model.Customer;
import com.market.groceries.model.key.CustomerId;
import com.market.groceries.repository.CustomerRepository;

@RestController
@RequestMapping("coffee-house/customer/")
public class CustomerController {

	private static final Logger logger = LoggerFactory
			.getLogger(CustomerController.class);

	@Autowired
	private CustomerRepository customerRepository;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String addCustomer(@RequestBody CustomerDTO customerDTO) {
		String firstName = customerDTO.getFirstName();
		String lastName = customerDTO.getLastName();
		String phoneNumber = customerDTO.getPhoneNumber();
		if(firstName == null || lastName == null || phoneNumber == null) {
			return CoffeeHouseConstants.CUSTOMER_MANDATORY_FIELDS_NOT_PRESENT;
		}
		Customer customer = getCustomerFromCustomerDTO(customerDTO);
		try {
			customer = customerRepository.save(customer);
			return customer.getCustomerId().getFirstName()
					+ " successfully added in the database";
		} catch (Exception e) {
			logger.error("Exception while adding a new customer. ", e);
		}

		return CoffeeHouseConstants.CUSTOMER_ADD_ERROR + " with name "
				+ firstName;
	}
	
	@RequestMapping(value = "/{firstName}/{lastName}/{phoneNumber}", method = RequestMethod.GET)
	public CustomerDTO getCustomer(@PathVariable String firstName, @PathVariable String lastName, @PathVariable String phoneNumber) {
		CustomerDTO customerDTO = null;
		try {
			CustomerId customerId = new CustomerId();
			customerId.setFirstName(firstName);
			customerId.setLastName(lastName);
			customerId.setPhoneNumber(phoneNumber);
			Optional<Customer> customer = customerRepository.findById(customerId);
			if(customer.isPresent()) {
				customerDTO = getCustomerDTOFromCustomer(customer.get());
			}
		} catch (Exception e) {
			logger.error("Exception while fetching customer. ", e);
		}
		return customerDTO;
	}

	public Customer getCustomerFromCustomerDTO(CustomerDTO customerDto) {
		Customer customer = new Customer();
		CustomerId customerId = new CustomerId();
		customerId.setFirstName(customerDto.getFirstName());
		customerId.setLastName(customerDto.getLastName());
		customerId.setPhoneNumber(customerDto.getPhoneNumber());
		customer.setCustomerId(customerId);
		customer.setEmailId(customerDto.getEmailId());
		return customer;
	}
	
	public CustomerDTO getCustomerDTOFromCustomer(Customer customer) {
		CustomerId customerId = customer.getCustomerId();
		CustomerDTO customerDto = new CustomerDTO();
		customerDto.setFirstName(customerId.getFirstName());
		customerDto.setLastName(customerId.getLastName());
		customerDto.setPhoneNumber(customerId.getPhoneNumber());
		customerDto.setEmailId(customer.getEmailId());
		return customerDto;
	}
}
