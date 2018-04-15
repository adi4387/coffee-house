package com.market.groceries.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.market.groceries.model.ProductOrder;

public interface ProductOrderRepository extends
		JpaRepository<ProductOrder, Long> {
	
	@Query("Select po from ProductOrder where orderDate = trunc(:reportDate)")
	public List<ProductOrder> findAllTheOrdersForADay(@Param("reportDate") Date date);

}
