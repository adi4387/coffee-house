package com.market.groceries.service;

import org.springframework.stereotype.Service;

import com.market.groceries.dto.CustomerDTO;

@Service("customerService")
public interface CustomerService {
	public String addCustomer(CustomerDTO customerDTO);
}
