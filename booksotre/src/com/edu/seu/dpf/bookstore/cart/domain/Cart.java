package com.edu.seu.dpf.bookstore.cart.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
	private Map<String, CartItem> map = new LinkedHashMap<>();
	
	public double getTotal() {
		BigDecimal total = new BigDecimal("0");
		for (CartItem cartItem : map.values()) {
			BigDecimal subTotal = new BigDecimal("" + cartItem.getSubtoatl());
			total = total.add(subTotal);
		}
		return total.doubleValue();
	}
	
	public void add(CartItem cartItem) {
		if(map.containsKey(cartItem.getBook().getBid())) {
			CartItem _cartItem = map.get(cartItem.getBook().getBid());
			_cartItem.setCount(_cartItem.getCount()+cartItem.getCount());
			map.put(_cartItem.getBook().getBid(), _cartItem);
		}else {
			map.put(cartItem.getBook().getBid(), cartItem);
		}
	}
	
	public void clear() {
		map.clear();
	}
	
	public void delete(String bid) {
		map.remove(bid);
	}
	
	/*
	 * 获取所有条目
	 */
	public Collection<CartItem> getCartItems() {
		return map.values();
	}
}
