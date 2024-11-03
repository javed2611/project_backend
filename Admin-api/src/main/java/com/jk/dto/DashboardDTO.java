package com.jk.dto;

public class DashboardDTO {
	private Long customersCount;
	private Long ordersCount;
	private Double amountCollected;
	private Long productCount;

	public Long getCustomersCount() {
		return customersCount;
	}

	public void setCustomersCount(Long customersCount) {
		this.customersCount = customersCount;
	}

	public Long getOrdersCount() {
		return ordersCount;
	}

	public void setOrdersCount(Long ordersCount) {
		this.ordersCount = ordersCount;
	}

	public Double getAmountCollected() {
		return amountCollected;
	}

	public void setAmountCollected(Double amountCollected) {
		this.amountCollected = amountCollected;
	}

	public Long getProductCount() {
		return productCount;
	}

	public void setProductCount(Long productCount) {
		this.productCount = productCount;
	}

}
