package com.candyshop.request;

public class CreateProductRequest {
	private String title;
	
	private String description;
	
	private int price;
	
	private int discountPrice;
	
	private int discountPercent;
	
	private int quantity;
	
	private String Brand ;
	
	private String Color;
	
	private String imageUrl; 
	
	private String topLabelCategory;

	private String secondLabelCategory;
	
	private String thirdLabelCategory;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getDiscountedPrice() {
		return discountPrice;
	}

	public void setDiscountedPrice(int discountedPrice) {
		this.discountPrice = discountedPrice;
	}

	public int getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(int discountPercent) {
		this.discountPercent = discountPercent;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getBrand() {
		return Brand;
	}

	public void setBrand(String brand) {
		Brand = brand;
	}

	public String getColor() {
		return Color;
	}

	public void setColor(String color) {
		Color = color;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getTopLavelCategory() {
		return topLabelCategory;
	}

	public void setTopLavelCategory(String topLavelCategory) {
		this.topLabelCategory = topLavelCategory;
	}

	public String getSecondLavelCategory() {
		return secondLabelCategory;
	}

	public void setSecondLavelCategory(String secondLavelCategory) {
		this.secondLabelCategory = secondLavelCategory;
	}

	public String getThirdLavelCategory() {
		return thirdLabelCategory;
	}

	public void setThirdLavelCategory(String thirdLavelCategory) {
		this.thirdLabelCategory = thirdLavelCategory;
	}
	
	
}