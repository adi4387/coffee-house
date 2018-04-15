package com.market.groceries.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoffeeHouseController {
	
	@RequestMapping("/")
	public String home() {
		return "Welcome to Rob's Coffee House...!!!";
	}

}
