package com.example.demo.DTO;



public class ResponseOrderDTO {

	private Long shoeId;
	private String shoeImage;
	private String size;
	private int quantity;
	private double totalPrice;
	private String shoeName;
	
	
	
	
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getShoeName() {
		return shoeName;
	}
	public void setShoeName(String shoeName) {
		this.shoeName = shoeName;
	}
	public Long getShoeId() {
		return shoeId;
	}
	public void setShoeId(Long shoeId) {
		this.shoeId = shoeId;
	}
	public String getShoeImage() {
		return shoeImage;
	}
	public void setShoeImage(String shoeImage) {
		this.shoeImage = shoeImage;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
}
