package com.edu.seu.dpf.bookstore.order.service;

import java.sql.SQLException;
import java.util.List;

import com.edu.seu.dpf.bookstore.order.dao.OrderDao;
import com.edu.seu.dpf.bookstore.order.domain.Order;

import cn.itcast.jdbc.JdbcUtils;

public class OrderService {

	private OrderDao orderDao = new OrderDao();
	
	public void add(Order order) {
		try {
			//开启事务
			JdbcUtils.beginTransaction();
			orderDao.addOrder(order);
			orderDao.addOrderItemList(order.getOrderItemList());
			//提交事务
			JdbcUtils.commitTransaction();
		}catch (Exception e) {
			//回滚事务
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
			}
			throw new RuntimeException(e);
		}
	}
	
	public List<Order> myOrders(String uid){
		return orderDao.findByUid(uid);
	}

	public Order load(String oid) {
		return orderDao.load(oid);
	}
	
	/*
	 * 确认收货
	 */
	public void confirm(String oid) throws OrderException{
		int state = orderDao.getStateByOid(oid);
		if(state != 3) {
			throw new OrderException("你过分了！");
		}else {
			orderDao.updatestate(oid, 4);
		}
	}

	public void pay(String oid) {
		int state = orderDao.getStateByOid(oid);
		if(state == 1) {
			orderDao.updatestate(oid, 2);
		}
	}
}
