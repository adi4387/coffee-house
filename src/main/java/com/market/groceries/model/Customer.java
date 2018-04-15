package com.market.groceries.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.market.groceries.model.key.CustomerId;

@Entity
@Table(name = "Customer")
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	@Column(name = "customer_id")
	private CustomerId customerId;
	@Column(name = "email_id")
	private String emailId;

	/*
	 * @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy =
	 * "customer") private List<Order> orders;
	 */

	public CustomerId getCustomerId() {
		return customerId;
	}

	public void setCustomerId(CustomerId customerId) {
		this.customerId = customerId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/*
	 * public List<Order> getOrders() { return orders; }
	 * 
	 * public void setOrders(List<Order> orders) { this.orders = orders; }
	 */

	@Override
	public String toString() {
		return "Customer [customerKey=" + customerId + ", emailId=" + emailId
				+ "]";
	}

	@Override
	public int hashCode() {
		return getCustomerId().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return other.getCustomerId().equals(getCustomerId());
	}
}
