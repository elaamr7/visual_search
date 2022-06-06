package com.example.faloka_mobile.model;

import com.google.gson.annotations.SerializedName;

public class Product {

	@SerializedName("size")
	private String size;

	@SerializedName("price")
	private int price;

	@SerializedName("imagePath")
	private String imagePath;

	@SerializedName("name")
	private String name;

	public void setSize(String size) {
		this.size = size;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(String id) {
		this.id = id;
	}

	@SerializedName("description")
	private String description;

	@SerializedName("_id")
	private String id;

	public String getSize(){
		return size;
	}

	public int getPrice(){
		return price;
	}

	public String getImagePath(){
		return imagePath;
	}

	public String getName(){
		return name;
	}

	public String getDescription(){
		return description;
	}

	public String getId(){
		return id;
	}
}