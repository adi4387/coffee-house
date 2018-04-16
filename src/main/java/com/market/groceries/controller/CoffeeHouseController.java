package com.market.groceries.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import io.swagger.annotations.Api;

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
import com.market.groceries.dto.CustomerDTO;
import com.market.groceries.dto.ProductDTO;
import com.market.groceries.dto.ProductOrderDTO;
import com.market.groceries.dto.ProductOrderReportDTO;
import com.market.groceries.model.Customer;
import com.market.groceries.model.Product;
import com.market.groceries.model.ProductOrder;
import com.market.groceries.model.key.CustomerId;
import com.market.groceries.model.key.ProductId;
import com.market.groceries.repository.CustomerRepository;
import com.market.groceries.repository.ProductOrderRepository;
import com.market.groceries.repository.ProductRepository;

@RestController
@RequestMapping("coffee-house/")
@Api("Coffee House Store")
public class CoffeeHouseController {

	private static final Logger logger = LoggerFactory
			.getLogger(CoffeeHouseController.class);
	
	@Autowired
	private ProductOrderRepository productOrderRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@RequestMapping("/")
	public String home() {
		return "Welcome to Rob's Coffee House...!!!";
	}

	@RequestMapping(value = "/order/", method = RequestMethod.POST)
	public String placeOrder(@RequestBody ProductOrderDTO productOrderDTO) {
		ProductDTO productDto = productOrderDTO.getProductDTO();
		ProductOrder productOrder = null;
		Product product = getProduct(productDto);
		Customer customer = getCustomer(productOrderDTO.getCustomerDTO());
		try {
			if(null != product) {
				productOrder = getProductOrderFromProductOrderDTO(productOrderDTO);
				Double quantity = productOrder.getQuantity();
				Double availableQuantity = product.getAvailableQuantity();
				if(quantity > availableQuantity) {
					return CoffeeHouseConstants.PRODUCT_UNAVAILABLE;
				} else {
					product.setAvailableQuantity(availableQuantity - quantity);
					productOrder.setProduct(product);
					productOrder.setCustomer(customer);
					productRepository.save(product);
					productOrderRepository.save(productOrder);
					return CoffeeHouseConstants.ORDER_PLACED_SUCCESSFULLY;
				}
			} else {
				return CoffeeHouseConstants.PRODUCT_UNAVAILABLE;
			}
		} catch (Exception e) {
			logger.error(CoffeeHouseConstants.ORDER_FAILED, e);
		}
		return CoffeeHouseConstants.ORDER_FAILED;
	}

	@RequestMapping(value = "/order/{name}/{variety}", method = RequestMethod.GET)
	public List<ProductOrderDTO> getOrder(@PathVariable("name") String name, @PathVariable("variety") String variety) {
		ProductId productId = new ProductId();
		ProductOrderDTO productOrderDTO = null;
		List<ProductOrderDTO> productOrderDTOs = new ArrayList<>();
		productId.setName(name);
		productId.setVariety(variety);
		Product product = new Product();
		product.setProductId(productId);
		List<ProductOrder> productOrders = productOrderRepository.findAllTheOrdersOfAProduct(product);
		for(ProductOrder productOrder : productOrders) {
			productOrderDTO = getProductOrderDTOFromProductOrder(productOrder);
			productOrderDTOs.add(productOrderDTO);
		}
		return productOrderDTOs;
	}
	
	@RequestMapping(value = "/report", method = RequestMethod.GET)
	public List<ProductOrderReportDTO> getOrder() {
		List<ProductOrderReportDTO> productOrderReportDTOs = new ArrayList<>(); 
		List<ProductOrder> productOrders = null;
		ProductOrderReportDTO productOrderReportDTO = null;
		List<Product> products = productRepository.findAllProducts();
		Double totalServingsSoldForTheDay = 0.0D;
		for(Product product : products) {
			productOrders = productOrderRepository.findAllTheOrdersOfAProductInADay(new Date(), product);
			for(ProductOrder productOrder : productOrders) {
				totalServingsSoldForTheDay += productOrder.getQuantity();
			}
			productOrderReportDTO = new ProductOrderReportDTO();
			productOrderReportDTO.setVariety(product.getProductId().getVariety());
			productOrderReportDTO.setTotalServingsOfTheDay(totalServingsSoldForTheDay + product.getAvailableQuantity());
			productOrderReportDTO.setTotalServingsSoldForTheDay(totalServingsSoldForTheDay);
			productOrderReportDTOs.add(productOrderReportDTO);
		}
		return productOrderReportDTOs;
	}

	public ProductOrderDTO getProductOrderDTOFromProductOrder(
			ProductOrder productOrder) {
		ProductOrderDTO productOrderDTO = new ProductOrderDTO();
		productOrderDTO.setCustomerDTO(getCustomerDTOFromCustomer(productOrder
				.getCustomer()));
		productOrderDTO.setProductDTO(getProductDTOFromProduct(productOrder
				.getProduct()));
		productOrderDTO.setAmount(productOrder.getAmount());
		productOrderDTO.setOrderId(productOrder.getOrderId());
		productOrderDTO.setOrderDate(productOrder.getOrderDate());
		productOrderDTO.setQuantity(productOrder.getQuantity());
		return productOrderDTO;

	}

	public ProductOrder getProductOrderFromProductOrderDTO(
			ProductOrderDTO productOrderDTO) {
		ProductOrder productOrder = new ProductOrder();
		productOrder.setCustomer(getCustomerFromCustomerDTO(productOrderDTO
				.getCustomerDTO()));
		productOrder.setProduct(getProductFromProductDTO(productOrderDTO
				.getProductDTO()));
		productOrder.setAmount(productOrderDTO.getAmount());
		productOrder.setOrderId(productOrderDTO.getOrderId());
		productOrder.setOrderDate(productOrderDTO.getOrderDate());
		productOrder.setQuantity(productOrderDTO.getQuantity());
		return productOrder;

	}

	public Customer getCustomerFromCustomerDTO(CustomerDTO customerDto) {
		Customer customer = new Customer();
		CustomerId customerId = new CustomerId();
		customerId.setFirstName(customerDto.getFirstName());
		customerId.setLastName(customerDto.getLastName());
		customerId.setPhoneNumber(customerDto.getPhoneNumber());
		customer.setCustomerId(customerId);
		customer.setEmailId(customerDto.getEmailId());
		return customer;
	}

	public CustomerDTO getCustomerDTOFromCustomer(Customer customer) {
		CustomerId customerId = customer.getCustomerId();
		customer = getCustomer(customer);
		CustomerDTO customerDto = new CustomerDTO();
		customerDto.setFirstName(customerId.getFirstName());
		customerDto.setLastName(customerId.getLastName());
		customerDto.setPhoneNumber(customerId.getPhoneNumber());
		customerDto.setEmailId(customer.getEmailId());
		return customerDto;
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
		product = getProduct(product);
		productDto.setName(productId.getName());
		productDto.setVariety(productId.getVariety());
		productDto.setAvailableQuantity(product.getAvailableQuantity());
		productDto.setUnit(product.getUnit().toString());
		productDto.setPricePerUnit(product.getPricePerUnit());
		return productDto;
	}
	
	public Product getProduct(ProductDTO productDto) {
		Product product = getProductFromProductDTO(productDto);
		return getProduct(product);
	}

	private Product getProduct(Product product) {
		Optional<Product> opt = productRepository.findById(product.getProductId());
		if(opt.isPresent()) {
			return opt.get();
		}
		return null;
	}
	
	public Customer getCustomer(CustomerDTO customerDto) {
		Customer customer = getCustomerFromCustomerDTO(customerDto);
		return getCustomer(customer);
	}

	private Customer getCustomer(Customer customer) {
		Optional<Customer> opt = customerRepository.findById(customer.getCustomerId());
		if(opt.isPresent()) {
			return opt.get();
		}
		return null;
	}
}
