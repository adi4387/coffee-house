package com.market.groceries.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.market.groceries.model.Customer;
import com.market.groceries.model.Product;
import com.market.groceries.model.ProductOrder;

public interface ProductOrderRepository extends
		JpaRepository<ProductOrder, Long> {

	@Query("Select po from ProductOrder po where po.orderDate <= :reportDate")
	public List<ProductOrder> findAllTheOrdersForADay(
			@Param("reportDate") Date date);

	@Query("Select po from ProductOrder po where po.customer = :customer")
	public List<ProductOrder> findAllTheOrdersOfACustomer(
			@Param("customer") Customer customer);

	@Query("Select po from ProductOrder po where po.product = :product")
	public List<ProductOrder> findAllTheOrdersOfAProduct(
			@Param("product") Product product);

	@Query("Select po from ProductOrder po where po.orderDate <= :reportDate and po.product = :product")
	public List<ProductOrder> findAllTheOrdersOfAProductInADay(
			@Param("reportDate") Date date, @Param("product") Product product);

}
