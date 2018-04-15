package com.market.groceries.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.market.groceries.model.Customer;
import com.market.groceries.model.key.CustomerId;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, CustomerId>{

}
