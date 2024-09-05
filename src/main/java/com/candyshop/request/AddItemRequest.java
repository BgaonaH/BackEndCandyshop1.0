package com.candyshop.request;

public class AddItemRequest {

	private Long productId;
	private String weight;
	private int quantity;
	private Integer price;
	
	public AddItemRequest() {
		
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getWeight() {
		return weight;
	}
	public String setWeight() {
		return weight;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	
}
