package com.market.groceries.dto;

public class ProductDTO {

	private String name;
	private String variety;
	private Double availableQuantity;
	private Double pricePerUnit;
	private String unit;

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

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		return "ProductDTO [name=" + name + ", variety=" + variety
				+ ", availableQuantity=" + availableQuantity
				+ ", pricePerUnit=" + pricePerUnit + ", unit=" + unit + "]";
	}
}
