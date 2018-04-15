package com.market.groceries.controller;

import java.util.ArrayList;
import java.util.List;
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
		String unit = productDto.getUnit();
		if (name == null || variety == null || availableQuantity == null
				|| pricePerUnit == null || pricePerUnit == null || unit == null)
			return CoffeeHouseConstants.PRODUCT_MANDATORY_FIELDS_NOT_PRESENT
					+ " with name " + name;
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

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public String updateProduct(@RequestBody ProductDTO productDto) {
		String name = productDto.getName();
		String variety = productDto.getVariety();
		Double availableQuantity = productDto.getAvailableQuantity();
		Double pricePerUnit = productDto.getPricePerUnit();
		String unit = productDto.getUnit();
		if (name == null || variety == null || availableQuantity == null
				|| pricePerUnit == null || pricePerUnit == null || unit == null)
			return CoffeeHouseConstants.PRODUCT_MANDATORY_FIELDS_NOT_PRESENT
					+ " with name " + name;
		else {
			Product product = getProductFromProductDTO(productDto);
			try {
				product = productRepository.save(product);
				return product.getProductId().getName() + " "
						+ CoffeeHouseConstants.PRODUCT_UPDATE_SUCCESS;
			} catch (Exception e) {
				logger.error(CoffeeHouseConstants.PRODUCT_UPDATE_ERROR, e);
			}
		}
		return CoffeeHouseConstants.PRODUCT_UPDATE_ERROR;
	}

	@RequestMapping(value = "/{name}/{variety}", method = RequestMethod.GET)
	public ProductDTO getProduct(@PathVariable String name,
			@PathVariable String variety) {
		ProductDTO productDto = null;
		try {
			ProductId productId = new ProductId();
			productId.setName(name);
			productId.setVariety(variety);
			Optional<Product> opt = productRepository.findById(productId);
			if (opt.isPresent()) {
				productDto = getProductDTOFromProduct(opt.get());
			}
		} catch (Exception e) {
			logger.error(CoffeeHouseConstants.PRODUCT_FETCH_ERROR, e);
		}
		return productDto;
	}

	@RequestMapping(value = "/allAvailable", method = RequestMethod.GET)
	public List<ProductDTO> getAllAvailableProduct() {
		List<ProductDTO> productDTOs = new ArrayList<>();
		try {
			List<Product> products = productRepository
					.findAllAvailableProducts();
			if (null != products && !products.isEmpty()) {
				for (Product product : products) {
					productDTOs.add(getProductDTOFromProduct(product));
				}
			}
		} catch (Exception e) {
			logger.error(CoffeeHouseConstants.PRODUCT_FETCH_ERROR, e);
		}
		return productDTOs;
	}

	public Product getProductFromProductDTO(ProductDTO productDto) {
		Product product = new Product();
		ProductId productId = new ProductId();
		productId.setName(productDto.getName());
		productId.setVariety(productDto.getVariety());
		product.setProductId(productId);
		product.setAvailableQuantity(productDto.getAvailableQuantity());
		product.setPricePerUnit(productDto.getPricePerUnit());
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
