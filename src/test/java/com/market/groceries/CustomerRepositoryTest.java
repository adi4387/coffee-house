package com.market.groceries;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.market.groceries.model.Customer;
import com.market.groceries.model.key.CustomerId;
import com.market.groceries.repository.CustomerRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Test
	public void testWhenANewCustomerIsAdded() {
		Customer customer = new Customer();
		CustomerId customerId = new CustomerId();
		customerId.setFirstName("Varun");
		customerId.setLastName("Dhawan");
		customerId.setPhoneNumber("+919999911111");
		customer.setCustomerId(customerId);
		customer.setEmailId("varun.dhawan@abc.com");
		Customer customerPersisted = customerRepository.save(customer);

		assertTrue(customerPersisted.equals(customer));
	}
	
	@Test
	public void testWhenCustomerIsRetrieved() {
		Customer customer = new Customer();
		CustomerId customerId = new CustomerId();
		customerId.setFirstName("Varun");
		customerId.setLastName("Dhawan");
		customerId.setPhoneNumber("+919999911111");
		customer.setCustomerId(customerId);
		customer.setEmailId("varun.dhawan@abc.com");
		entityManager.persist(customer);
		entityManager.flush();
		Optional<Customer> opt = customerRepository.findById(customerId);
		assertTrue(customer.equals(opt.get()));
	}
	
	@Test
	public void testWhenCustomerExistIShouldNotBeAbleToAddAgainTheSameCustomer() {
		Customer customer = new Customer();
		CustomerId customerId = new CustomerId();
		customerId.setFirstName("Varun");
		customerId.setLastName("Dhawan");
		customerId.setPhoneNumber("+919999911111");
		customer.setCustomerId(customerId);
		customer.setEmailId("varun.dhawan@abc.com");
		entityManager.persist(customer);
		entityManager.flush();
		Optional<Customer> opt = customerRepository.findById(customerId);
		assertTrue(customer.equals(opt.get()));
	}
}
