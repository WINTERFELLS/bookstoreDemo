package com.edu.seu.dpf.bookstore.cart.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.seu.dpf.bookstore.book.domain.Book;
import com.edu.seu.dpf.bookstore.book.service.BookService;
import com.edu.seu.dpf.bookstore.cart.domain.Cart;
import com.edu.seu.dpf.bookstore.cart.domain.CartItem;

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
		
		String bid = request.getParameter("bid");
		Book book = new BookService().load(bid);
		int count = Integer.parseInt(request.getParameter("count"));
		CartItem cartItem = new CartItem();
		cartItem.setBook(book);
		cartItem.setCount(count);
		cart.add(cartItem);
		return "f:/jsps/cart/list.jsp";
	}
	
	public String clear(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		cart.clear();
		return "f:/jsps/cart/list.jsp";
		
	}
	
	public String delete(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
				
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		String bid = request.getParameter("bid");
		cart.delete(bid);
		return "f:/jsps/cart/list.jsp";
		
	}
}
