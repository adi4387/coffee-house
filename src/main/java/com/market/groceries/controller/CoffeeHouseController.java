package com.market.groceries.controller;

import io.swagger.annotations.Api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("coffee-house/") @Api("Coffee House Store")
public class CoffeeHouseController {
	
	@RequestMapping("/")
	public String home() {
		return "Welcome to Rob's Coffee House...!!!";
	}

}
