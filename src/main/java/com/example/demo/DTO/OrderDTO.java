package com.example.demo.DTO;

import com.example.demo.Entity.Shoe;

public class OrderDTO {

	private Long shoeId;
	private int quantity;
	private double totalPrice;
	public Long getShoeId() {
		return shoeId;
	}
	public void setShoeId(Long shoeId) {
		this.shoeId = shoeId;
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
	@Override
	public String toString() {
		return "OrderDTO [shoeId=" + shoeId + ", quantity=" + quantity + ", totalPrice=" + totalPrice + "]";
	}
	
	
	
	
}
