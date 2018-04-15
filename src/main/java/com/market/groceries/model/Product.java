package com.market.groceries.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.market.groceries.constant.Unit;
import com.market.groceries.model.key.ProductId;

@Entity
@Table(name = "Product")
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@EmbeddedId
	@Column(name = "product_id")
	private ProductId productId;
	@Column(name = "avail_qty")
	private Double availableQuantity;
	@Column(name = "price_per_unit")
	private Double pricePerUnit;
	@Enumerated(EnumType.STRING)
	@Column(name = "unit")
	private Unit unit;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "product")
	private ProductOrder productOrder;

	public ProductId getProductId() {
		return productId;
	}

	public void setProductId(ProductId productId) {
		this.productId = productId;
	}

	public Double getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(Double availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public Double getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(Double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public ProductOrder getProductOrder() {
		return productOrder;
	}

	public void setProductOrder(ProductOrder productOrder) {
		this.productOrder = productOrder;
	}

	@Override
	public int hashCode() {
		return getProductId().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return getProductId().equals(other.getProductId());
	}

}
