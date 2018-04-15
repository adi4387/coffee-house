package com.market.groceries.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Product_Order")
public class ProductOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_id")
	private Long orderId;
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumns({@JoinColumn(name = "name"), @JoinColumn(name = "variety")})
	private Product product;
	@NotNull
	@Column(name = "quantity")
	private Double quantity;
	@NotNull
	@Column(name = "amount")
	private Double amount;
	@NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "order_at")
    private Date orderDate = new Date();
	
	@ManyToOne
	@JoinColumns({@JoinColumn(name = "first_name"), @JoinColumn(name = "last_name"), @JoinColumn(name = "phone_number")})
	private Customer customer;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Customer getCustomer() {
		return customer;
	}

}
