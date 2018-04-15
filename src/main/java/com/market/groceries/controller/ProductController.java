package com.market.groceries.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.market.groceries.constant.CoffeeHouseConstants;
import com.market.groceries.constant.Unit;
import com.market.groceries.dto.ProductDTO;
import com.market.groceries.model.Product;
import com.market.groceries.model.key.ProductId;
import com.market.groceries.repository.ProductRepository;

@RestController
@RequestMapping("coffee-house/product/")
public class ProductController {

	private static final Logger logger = LoggerFactory
			.getLogger(ProductController.class);

	@Autowired
	private ProductRepository productRepository;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String addProduct(@RequestBody ProductDTO productDto) {
		String name = productDto.getName();
		String variety = productDto.getVariety();
		Double availableQuantity = productDto.getAvailableQuantity();
		Double pricePerUnit = productDto.getPricePerUnit();

		if (name == null || variety == null || availableQuantity == null
				|| pricePerUnit == null || pricePerUnit == null)
			return CoffeeHouseConstants.PRODUCT_MANDATORY_FIELDS_NOT_PRESENT
					+ "with name " + name;
		else {
			Product product = getProductFromProductDTO(productDto);
			try {
				product = productRepository.save(product);
				return product.getProductId().getName()
						+ " added successfully in the inventory";
			} catch (Exception e) {
				logger.error(CoffeeHouseConstants.PRODUCT_ADD_ERROR, e);
			}
		}
		return CoffeeHouseConstants.PRODUCT_ADD_ERROR;
	}

	@RequestMapping(value = "/{name}/{variety}/{quantity}", method = RequestMethod.PUT)
	public String updateProduct(@PathVariable String name,
			@PathVariable String variety, @PathVariable Double quantity) {
		try {
			ProductId productId = new ProductId();
			productId.setName(name);
			productId.setVariety(variety);
			Optional<Product> opt = productRepository.findById(productId);
			if (opt.isPresent()) {
				Product product = opt.get();
				Double totalAvailableQuantity = product.getAvailableQuantity()
						+ (quantity != null ? quantity : 0.0D);
				product.setAvailableQuantity(totalAvailableQuantity);
				product = productRepository.save(product);
				return CoffeeHouseConstants.PRODUCT_UPDATE_SUCCESS
						+ " with name " + product.getProductId().getName();
			} else {
				return CoffeeHouseConstants.PRODUCT_UNAVAILABLE;
			}
		} catch (Exception e) {
			logger.error(CoffeeHouseConstants.PRODUCT_UPDATE_ERROR, e);
		}
		return CoffeeHouseConstants.PRODUCT_UPDATE_ERROR;
	}
	
	@RequestMapping(value = "/{name}/{variety}", method = RequestMethod.GET)
	public Product getProduct(@PathVariable String name, @PathVariable String variety) {
		Product product = null;
		try {
			ProductId productId = new ProductId();
			productId.setName(name);
			productId.setVariety(variety);
			Optional<Product> opt = productRepository.findById(productId);
			if (opt.isPresent()) {
				product = opt.get();
			}
		} catch (Exception e) {
			logger.error(CoffeeHouseConstants.PRODUCT_FETCH_ERROR, e);
		}
		return product;
	}
	
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public Product getProduct(@PathVariable String name) {
		Product product = null;
		try {
			product = new Product();
			ProductId productId = new ProductId();
			productId.setName("Arabica");
			productId.setVariety("Indian");
			product.setProductId(productId);
			product.setAvailableQuantity(10000D);
			product.setPricePerUnit(100D);
			product.setUnit(Unit.GRAM);
			productRepository.save(product);
			Optional<Product> opt = productRepository.findById(productId);
			if (opt.isPresent()) {
				product = opt.get();
			}
		} catch (Exception e) {
			logger.error(CoffeeHouseConstants.PRODUCT_FETCH_ERROR, e);
		}
		return product;
	}

	public Product getProductFromProductDTO(ProductDTO productDto) {
		Product product = new Product();
		ProductId productId = new ProductId();
		productId.setName(productDto.getName());
		productId.setVariety(productDto.getVariety());
		product.setProductId(productId);
		product.setAvailableQuantity(productDto.getPricePerUnit());
		product.setUnit(Unit.valueOf(productDto.getUnit()));
		return product;
	}

	public ProductDTO getProductDTOFromProduct(Product product) {
		ProductDTO productDto = new ProductDTO();
		ProductId productId = product.getProductId();
		productDto.setName(productId.getName());
		productDto.setVariety(productId.getVariety());
		productDto.setAvailableQuantity(product.getAvailableQuantity());
		productDto.setUnit(product.getUnit().toString());
		productDto.setPricePerUnit(product.getPricePerUnit());
		return productDto;
	}
}
