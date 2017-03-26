package com.model;

import java.util.List;

public class CartRequest {
	int userId;
	//Cart cart;
	List<LibraryBooks> cart;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/*public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}*/
	public List<LibraryBooks> getCart() {
		return cart;
	}
	public void setCart(List<LibraryBooks> cart) {
		this.cart = cart;
	}
	
}
