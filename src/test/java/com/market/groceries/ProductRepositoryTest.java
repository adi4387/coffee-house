package com.market.groceries;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.market.groceries.repository.ProductRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private ProductRepository productRepository;

	@Test
	public void testWhenNewVarietyOfCoffeeComesItHasToBeAddedInTheInventory() {
		Product product = new Product();
		ProductId productId = new ProductId();
		productId.setName("Arabica");
		productId.setVariety("Indian");
		product.setProductId(productId);
		product.setAvailableQuantity(10000D);
		product.setPricePerUnit(100D);
		product.setUnit(Unit.GRAM);
		Product persistedProduct = productRepository.save(product);
		assertThat("Testing persisting of item", persistedProduct
				.getProductId().getName(), equalTo(productId.getName()));
	}

	@Test
	public void testfindProductInTheInventory() {
		Product product = new Product();
		ProductId productId = new ProductId();
		productId.setName("Arabica");
		productId.setVariety("Indian");
		product.setProductId(productId);
		product.setAvailableQuantity(10000D);
		product.setPricePerUnit(100D);
		product.setUnit(Unit.GRAM);
		entityManager.persist(product);
		entityManager.flush();

		Optional<Product> opt = productRepository.findById(productId);
		Product persistedProduct = opt.get();
		assertThat("Testing persisting of item", persistedProduct
				.getProductId().getName(), equalTo(productId.getName()));
	}

	public void testfindAllTheProductsInTheInventory() {
		Product product = new Product();
		ProductId productId = new ProductId();
		productId.setName("Arabica");
		productId.setVariety("Indian");
		product.setProductId(productId);
		product.setAvailableQuantity(10000D);
		product.setPricePerUnit(100D);
		product.setUnit(Unit.GRAM);
		entityManager.persist(product);

		product = new Product();
		productId = new ProductId();
		productId.setName("Arabica");
		productId.setVariety("Arabian");
		product.setProductId(productId);
		product.setAvailableQuantity(10000D);
		product.setPricePerUnit(150D);
		product.setUnit(Unit.GRAM);
		entityManager.persist(product);

		product = new Product();
		productId = new ProductId();
		productId.setName("Kents");
		productId.setVariety("Indian");
		product.setProductId(productId);
		product.setAvailableQuantity(10000D);
		product.setPricePerUnit(500D);
		product.setUnit(Unit.GRAM);
		entityManager.persist(product);
		entityManager.flush();

		List<Product> products = productRepository.findAllProducts();
		assertThat("Finding all products", products.size() > 0);
	}
	
	@Test
	public void testfindAllTheAvailableProductsInTheInventory() {
		Product product = new Product();
		ProductId productId = new ProductId();
		productId.setName("Arabica");
		productId.setVariety("Indian");
		product.setProductId(productId);
		product.setAvailableQuantity(10000D);
		product.setPricePerUnit(100D);
		product.setUnit(Unit.GRAM);
		entityManager.persist(product);

		product = new Product();
		productId = new ProductId();
		productId.setName("Arabica");
		productId.setVariety("Arabian");
		product.setProductId(productId);
		product.setAvailableQuantity(10000D);
		product.setPricePerUnit(150D);
		product.setUnit(Unit.GRAM);
		entityManager.persist(product);

		product = new Product();
		productId = new ProductId();
		productId.setName("Kents");
		productId.setVariety("Indian");
		product.setProductId(productId);
		product.setAvailableQuantity(10000D);
		product.setPricePerUnit(500D);
		product.setUnit(Unit.GRAM);
		entityManager.persist(product);
		entityManager.flush();

		List<Product> products = productRepository.findAllAvailableProducts();
		assertThat("Finding all products", products.size() > 0);
	}
	
	@Test
	public void testFindAlltheProductsOrderedByACustomer(){
		Customer customer = new Customer();
		CustomerId customerId = new CustomerId();
		customerId.setFirstName("Varun");
		customerId.setLastName("Dhawan");
		customerId.setPhoneNumber("919999911111");
		customer.setCustomerId(customerId);
		customer.setEmailId("varun.dhawan@abc.com");
		entityManager.persist(customer);
		Product product = new Product();
		ProductId productId = new ProductId();
		productId.setName("Arabica");
		productId.setVariety("Indian");
		product.setProductId(productId);
		product.setAvailableQuantity(10000D);
		double quantity = 100D;
		product.setPricePerUnit(100D);
		product.setUnit(Unit.GRAM);
		entityManager.persist(product);
		ProductOrder productOrder = new ProductOrder();
		product.setAvailableQuantity(10000D - quantity);
		productOrder.setProduct(product);
		productOrder.setQuantity(quantity);
		productOrder.setAmount(product.getPricePerUnit() * quantity);
		productOrder.setCustomer(customer);
		List<ProductOrder> productOrders = new ArrayList<>();
		productOrders.add(productOrder);
		entityManager.persist(productOrder);
		entityManager.flush();
		Optional<Product> opt = productRepository.findById(productId);
		Product persistedProduct = opt.get();
		assertThat("Testing persisting of item", persistedProduct.getAvailableQuantity()
				, equalTo(9900D));
	}
}
