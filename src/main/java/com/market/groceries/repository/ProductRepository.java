package com.market.groceries.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.market.groceries.model.Product;
import com.market.groceries.model.key.ProductId;

@Repository
public interface ProductRepository extends CrudRepository<Product, ProductId> {

	@Query("SELECT p FROM Product p")
	public List<Product> findAllProducts();

	@Query("SELECT p FROM Product p where availableQuantity > 0")
	public List<Product> findAllAvailableProducts();

}
