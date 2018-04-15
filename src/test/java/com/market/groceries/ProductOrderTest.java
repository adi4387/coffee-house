package com.market.groceries;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.market.groceries.repository.ProductOrderRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductOrderTest {

	@Autowired
	TestEntityManager entityManager;
	
	@Autowired
	ProductOrderRepository productOrderRepository;
	
	
}
