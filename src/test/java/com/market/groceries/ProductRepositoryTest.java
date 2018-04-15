package com.market.groceries;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.market.groceries.constant.Unit;
import com.market.groceries.model.Product;
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
}
