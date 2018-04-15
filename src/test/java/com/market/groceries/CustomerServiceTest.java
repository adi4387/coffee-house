package com.market.groceries;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.market.groceries.dto.CustomerDTO;
import com.market.groceries.model.Customer;
import com.market.groceries.model.key.CustomerId;
import com.market.groceries.repository.CustomerRepository;
import com.market.groceries.service.CustomerService;
import com.market.groceries.service.impl.CustomerServiceImpl;

public class CustomerServiceTest {

	@Autowired
	private CustomerService customerService;

	private CustomerServiceImpl customerServiceImpl;

	@MockBean
	private CustomerRepository customerRepository;

	private Customer customer;
	private CustomerDTO customerDto;

	@Before
	public void init() {
		customerRepository = mock(CustomerRepository.class);
		customerServiceImpl = new CustomerServiceImpl();
		customerDto = new CustomerDTO();
		customerDto.setFirstName("Varun");
		customerDto.setLastName("Dhawan");
		customerDto.setPhoneNumber("+919999911111");
		customerDto.setEmailId("varun.dhawan@abc.com");
		
		customer = new Customer();
		CustomerId customerId = new CustomerId();
		customerId.setFirstName("Varun");
		customerId.setLastName("Dhawan");
		customerId.setPhoneNumber("+919999911111");
		customer.setCustomerId(customerId);
		customer.setEmailId("varun.dhawan@abc.com");
		given(customerRepository.save(customer)).willReturn(customer);
	}

	@Test
	public void testWhenIAddNewCustomerTheSameIsPassedToRepository() {
		Customer customer = customerServiceImpl
				.getCustomerFromCustomerDTO(customerDto);
		assertThat(
				customer.getCustomerId().getFirstName()
						.equals(customerDto.getFirstName())
						&& customer.getCustomerId().getLastName()
								.equals(customerDto.getLastName())
						&& customer.getCustomerId().getPhoneNumber()
								.equals(customerDto.getPhoneNumber())
						&& customer.getEmailId().equals(
								customerDto.getEmailId()), is(true));
	}

	@Test
	public void testWhenIAddNewCustomerItIsPersisted() {
		customerService.addCustomer(customerDto);
		then(customerRepository).should(times(1)).save(customer);
	}

}
