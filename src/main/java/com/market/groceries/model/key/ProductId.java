package com.market.groceries.model.key;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class ProductId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String variety;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVariety() {
		return variety;
	}

	public void setVariety(String variety) {
		this.variety = variety;
	}

	@Override
	public String toString() {
		return "ProductId [name=" + name + ", variety=" + variety + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(name.hashCode(), variety.hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductId other = (ProductId) obj;
		
		return Objects.equals(other.getName(), getName()) &&
				Objects.equals(other.getVariety(), getVariety());
	}
}
