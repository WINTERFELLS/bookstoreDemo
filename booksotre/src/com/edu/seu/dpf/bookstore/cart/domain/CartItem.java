package com.edu.seu.dpf.bookstore.cart.domain;

import java.math.BigDecimal;

import com.edu.seu.dpf.bookstore.book.domain.Book;

public class CartItem {
	private Book book;
	private int count;
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	/*
	 * 解决二进制运算误差问题
	 */
	public double getSubtoatl() {
		BigDecimal b1 = new BigDecimal(book.getPrice() + "");
		BigDecimal b2 = new BigDecimal(count + "");
		return b1.multiply(b2).doubleValue();
	}
}
