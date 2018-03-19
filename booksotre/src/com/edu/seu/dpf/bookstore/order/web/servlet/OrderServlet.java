package com.edu.seu.dpf.bookstore.order.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.seu.dpf.bookstore.cart.domain.Cart;
import com.edu.seu.dpf.bookstore.cart.domain.CartItem;
import com.edu.seu.dpf.bookstore.order.domain.Order;
import com.edu.seu.dpf.bookstore.order.domain.OrderItem;
import com.edu.seu.dpf.bookstore.order.service.OrderException;
import com.edu.seu.dpf.bookstore.order.service.OrderService;
import com.edu.seu.dpf.bookstore.user.domain.User;

import cn.itcast.servlet.BaseServlet;
import cn.itcast.utils.CommonUtils;

public class OrderServlet extends BaseServlet {
	
	private OrderService orderService = new OrderService();
	
	
	public String add(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		
		//创建order对象
		Order order = new Order();
		order.setOid(CommonUtils.uuid());
		order.setOrdertime(new Date());
		order.setState(1);
		User user = (User) request.getSession().getAttribute("session_user");
		order.setOwner(user);
		order.setTotal(cart.getTotal());
		
		//创建orderItem对象
		List<OrderItem> orderItemList = new ArrayList<>();
		
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setIid(CommonUtils.uuid());
			orderItem.setCount(cartItem.getCount());
			orderItem.setBook(cartItem.getBook());
			orderItem.setSubtotal(cartItem.getSubtoatl());
			orderItem.setOrder(order);
			
			orderItemList.add(orderItem);
		}
		
		//所有订单条目添加到order
		order.setOrderItemList(orderItemList);
		
		cart.clear();
		orderService.add(order);
		request.setAttribute("order", order);
		
		return "f:/jsps/order/desc.jsp";
	}
	
	public String myOrders(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("session_user");
		List<Order> orderList = orderService.myOrders(user.getUid());
		request.setAttribute("orderList", orderList);
		return "f:/jsps/order/list.jsp";
	}
	
	public String load(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setAttribute("order", orderService.load(request.getParameter("oid")));
		return "f:/jsps/order/desc.jsp";
	}
	
	public String confirm(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String oid = request.getParameter("oid");
		try {
			orderService.confirm(oid);
			request.setAttribute("msg", "交易成功!");
		} catch (OrderException e) {
			request.setAttribute("msg", e.getMessage());
		}
		return "f:/jsps/msg.jsp";
	}
}
