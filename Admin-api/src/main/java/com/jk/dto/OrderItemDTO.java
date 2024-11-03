package com.jk.dto;

public class OrderItemDTO {

	private Long id;
	private String productName;
	private String imageUrl;
	private int quantity;
	private double price;

	public OrderItemDTO() {
	}

	public OrderItemDTO(Long id, String productName, String imageUrl, int quantity, double price) {
		this.id = id;
		this.productName = productName;
		this.imageUrl = imageUrl;
		this.quantity = quantity;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
