package com.market.groceries;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.market.groceries.constant.Unit;
import com.market.groceries.model.Customer;
import com.market.groceries.model.Product;
import com.market.groceries.model.ProductOrder;
import com.market.groceries.model.key.CustomerId;
import com.market.groceries.model.key.ProductId;
import com.market.groceries.repository.ProductOrderRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductOrderRepositoryTest {

	private static final double AVAILABLE_QTY = 10000D;
	private static final String VARIETY = "Indian";
	private static final String PRODUCT_NAME = "Arabica";
	private static final String EMAIL_ID = "varun.dhawan@abc.com";
	private static final String PHONE_NUMBER = "919999911111";
	private static final String LAST_NAME = "Dhawan";
	private static final String FIRST_NAME = "Varun";

	@Autowired
	TestEntityManager entityManager;

	@Autowired
	ProductOrderRepository productOrderRepository;

	private Customer customer;
	private Product product;
	private double quantity = 100D;

	@Before
	public void init() {
		customer = new Customer();
		CustomerId customerId = new CustomerId();
		customerId.setFirstName(FIRST_NAME);
		customerId.setLastName(LAST_NAME);
		customerId.setPhoneNumber(PHONE_NUMBER);
		customer.setCustomerId(customerId);
		customer.setEmailId(EMAIL_ID);
		entityManager.persist(customer);
		product = new Product();
		ProductId productId = new ProductId();
		productId.setName(PRODUCT_NAME);
		productId.setVariety(VARIETY);
		product.setProductId(productId);
		product.setAvailableQuantity(AVAILABLE_QTY);
		product.setPricePerUnit(quantity);
		product.setUnit(Unit.GRAM);
		entityManager.persist(product);
		entityManager.flush();
	}

	@Test
	public void testFindAlltheProductsOrderedByACustomer() {
		ProductOrder productOrder = new ProductOrder();
		product.setAvailableQuantity(10000D - quantity);
		productOrder.setProduct(product);
		productOrder.setQuantity(quantity);
		productOrder.setAmount(product.getPricePerUnit() * quantity);
		productOrder.setCustomer(customer);
		productOrderRepository.save(productOrder);
		List<ProductOrder> productOrders = productOrderRepository
				.findAllTheOrdersOfACustomer(customer);
		assertThat("Find All List of Orders Placed By Customer",
				productOrders.size(), equalTo(1));
	}

	@Test
	public void testFindNumberOfProductsOrdered() {
		ProductOrder productOrder = new ProductOrder();
		product.setAvailableQuantity(10000D - quantity);
		productOrder.setProduct(product);
		productOrder.setQuantity(quantity);
		productOrder.setAmount(product.getPricePerUnit() * quantity);
		productOrder.setCustomer(customer);
		productOrderRepository.save(productOrder);
		List<ProductOrder> productOrders = productOrderRepository
				.findAllTheOrdersOfAProduct(product);
		assertThat("Find All List of Orders Placed By Customer",
				productOrders.size(), equalTo(1));
	}

	@Test
	public void testWhenCustomerPlacesAnOrderItShouldBeSavedOnlyIfProductIsAvailableInEnoughQuantity() {
		ProductOrder productOrder = new ProductOrder();
		productOrder.setProduct(product);
		productOrder.setQuantity(quantity);
		productOrder.setAmount(product.getPricePerUnit() * quantity);
		productOrder.setCustomer(customer);
		ProductOrder persistedProductOrder = productOrderRepository
				.save(productOrder);
		assertThat("Order is place", persistedProductOrder.getOrderId(),
				greaterThan(0L));
	}

	@Test
	public void testFindOrdersInADay() {
		ProductOrder productOrder = new ProductOrder();
		product.setAvailableQuantity(10000D - quantity);
		productOrder.setProduct(product);
		productOrder.setQuantity(quantity);
		productOrder.setAmount(product.getPricePerUnit() * quantity);
		productOrder.setCustomer(customer);
		productOrderRepository.save(productOrder);
		List<ProductOrder> productOrders = productOrderRepository
				.findAllTheOrdersForADay(new Date());
		assertThat("Find All List of Orders Placed By Customer",
				productOrders.size(), equalTo(1));
	}
	
	@Test
	public void testFindOrdersInADayForAProduct() {
		ProductOrder productOrder = new ProductOrder();
		product.setAvailableQuantity(10000D - quantity);
		productOrder.setProduct(product);
		productOrder.setQuantity(quantity);
		productOrder.setAmount(product.getPricePerUnit() * quantity);
		productOrder.setCustomer(customer);
		productOrderRepository.save(productOrder);
		List<ProductOrder> productOrders = productOrderRepository
				.findAllTheOrdersOfAProductInADay(new Date(), product);
		assertThat("Find All List of Orders Placed By Customer in a day for a product",
				productOrders.size(), equalTo(1));
	}

}
