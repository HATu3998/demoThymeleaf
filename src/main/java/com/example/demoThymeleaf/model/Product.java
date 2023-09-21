package com.example.demoThymeleaf.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//validator
	@Column(nullable=false,unique=true,length=300)
	private String productName;
	private int pyear;
	private double price;
	private String url;
	public Product( String productName, int year, double price, String url) {
		super();
		
		this.productName = productName;
		this.pyear = year;
		this.price = price;
		this.url = url;
	}
	public Product() {
		super();
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", year=" + pyear + ", price=" + price + ", url="
				+ url + "]";
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
	public int getYear() {
		return pyear;
	}
	public void setYear(int year) {
		this.pyear = year;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
