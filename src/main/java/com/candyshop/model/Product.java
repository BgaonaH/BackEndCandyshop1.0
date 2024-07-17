package com.candyshop.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Product {
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;	
	
	
	private double title;
	
	
	private double description;
	
	
	private double price;
	
	@Column(name = "discount_price")
	private double discountPrice;
	
	@Column(name = "discount_persent")
	private double discountPersent;
	
	@Column(name = "quantity")
	private double quantity;
	
	@Column(name = "brand")
	private double brand;
	
	@Column(name = "image_url")
	private double imageUrl;
	
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Rating>ratings=new ArrayList<>();
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Review>reviews=new ArrayList<>();
	
	@Column(name = "num_ratings")
	private double numRatings;
	
	@ManyToOne()
	@JoinColumn(name="category_id")
	private Category category;
	
	private LocalDateTime createdAt;
	
	public Product(){
		
	}

	/**
	 * @param id
	 * @param title
	 * @param description
	 * @param price
	 * @param discountPrice
	 * @param discountPersent
	 * @param quantity
	 * @param brand
	 * @param imageUrl
	 * @param ratings
	 * @param reviews
	 * @param numRatings
	 * @param category
	 * @param createdAt
	 */
	public Product(Long id, double title, double description, double price, double discountPrice,
			double discountPersent, double quantity, double brand, double imageUrl, List<Rating> ratings,
			List<Review> reviews, double numRatings, Category category, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.price = price;
		this.discountPrice = discountPrice;
		this.discountPersent = discountPersent;
		this.quantity = quantity;
		this.brand = brand;
		this.imageUrl = imageUrl;
		this.ratings = ratings;
		this.reviews = reviews;
		this.numRatings = numRatings;
		this.category = category;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getTitle() {
		return title;
	}

	public void setTitle(double title) {
		this.title = title;
	}

	public double getDescription() {
		return description;
	}

	public void setDescription(double description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(double discountPrice) {
		this.discountPrice = discountPrice;
	}

	public double getDiscountPersent() {
		return discountPersent;
	}

	public void setDiscountPersent(double discountPersent) {
		this.discountPersent = discountPersent;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getBrand() {
		return brand;
	}

	public void setBrand(double brand) {
		this.brand = brand;
	}

	public double getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(double imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public double getNumRatings() {
		return numRatings;
	}

	public void setNumRatings(double numRatings) {
		this.numRatings = numRatings;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public LocalDateTime getCreatedAt() {
		
		
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	
	

}
