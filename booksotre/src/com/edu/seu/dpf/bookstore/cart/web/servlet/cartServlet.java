package com.edu.seu.dpf.bookstore.cart.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.seu.dpf.bookstore.cart.domain.Cart;

import cn.itcast.servlet.BaseServlet;

public class cartServlet extends BaseServlet{
	
	/*
	 * 添加购物条目
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		/*
		 * 得到车；
		 * 得到图书和数量；
		 */
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		return null;
	}
	
	public String clear(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
				return null;
		
	}
	
	public String delete(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
				return null;
		
	}
}