package com.market.groceries.dto;

public class ProductOrderReportDTO {

	String variety;
	Double totalServingsOfTheDay;
	Double totalServingsSoldForTheDay;

	public String getVariety() {
		return variety;
	}

	public void setVariety(String variety) {
		this.variety = variety;
	}

	public Double getTotalServingsOfTheDay() {
		return totalServingsOfTheDay;
	}

	public void setTotalServingsOfTheDay(Double totalServingsOfTheDay) {
		this.totalServingsOfTheDay = totalServingsOfTheDay;
	}

	public Double getTotalServingsSoldForTheDay() {
		return totalServingsSoldForTheDay;
	}

	public void setTotalServingsSoldForTheDay(Double totalServingsSoldForTheDay) {
		this.totalServingsSoldForTheDay = totalServingsSoldForTheDay;
	}
}
