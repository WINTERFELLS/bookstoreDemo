package com.edu.seu.dpf.bookstore.order.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.edu.seu.dpf.bookstore.book.domain.Book;
import com.edu.seu.dpf.bookstore.order.domain.Order;
import com.edu.seu.dpf.bookstore.order.domain.OrderItem;

import cn.itcast.jdbc.TxQueryRunner;
import cn.itcast.utils.CommonUtils;

public class OrderDao {

	private QueryRunner qr = new TxQueryRunner();

	public void addOrder(Order order) {
		try {
			String sql = "insert into orders values(?,?,?,?,?,?)";

			Timestamp tiemstamp = new Timestamp(order.getOrdertime().getTime());
			Object[] params = { order.getOid(), order.getOrdertime(), order.getTotal(), tiemstamp,
					order.getOwner().getUid(), order.getAddress() };
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void addOrderItemList(List<OrderItem> orderItemList) {
		/*
		 * 批处理，二维params数组
		 */
		try {
			String sql = "insert into orderitem values(?,?,?,?,?)";
			Object[][] params = new Object[orderItemList.size()][];
			for (int i = 0; i < orderItemList.size(); i++) {
				OrderItem orderItem = orderItemList.get(i);
				params[i] = new Object[] { orderItem.getIid(), orderItem.getCount(), orderItem.getSubtotal(),
						orderItem.getOrder().getOid(), orderItem.getBook().getBid() };
			}
			qr.batch(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Order> findByUid(String uid) {
		try {
			String sql = "select * from orders where uid=?";
			List<Order> orderList = qr.query(sql, new BeanListHandler<Order>(Order.class), uid);

			for (Order order : orderList) {
				loadOrderItems(order);// 为order对象加载订单条目
			}
			return orderList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void loadOrderItems(Order order) {
		// 多表查询
		try {
			String sql = "select * from orderitem i, book b where i.bid=b.bid and oid=?";
			List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler(), order.getOid());
			// 循环便利每个Map，使用map生成两个对象，然后建立关系，最终结果一个OrderItem
			List<OrderItem> orderItemList = toOrderItemList(mapList);
			order.setOrderItemList(orderItemList);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	private List<OrderItem> toOrderItemList(List<Map<String, Object>> mapList) {
		List<OrderItem> orderItemList = new ArrayList<>();
		for (Map<String, Object> map : mapList) {
			OrderItem orderItem = toOrderItem(map);
			orderItemList.add(orderItem);
		}
		return orderItemList;
	}

	/*
	 * 把一个Map转换成两个对象并合并成一个OrderItem对象
	 */
	private OrderItem toOrderItem(Map<String, Object> map) {
		OrderItem orderItem = CommonUtils.toBean(map, OrderItem.class);
		Book book = CommonUtils.toBean(map, Book.class);
		orderItem.setBook(book);
		return orderItem;
	}

	public Order load(String oid) {
		try {
			String sql = "select * from orders where oid=?";
			Order order = qr.query(sql, new BeanHandler<Order>(Order.class), oid);
			loadOrderItems(order);
			return order;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int getStateByOid(String oid) {
		try {
			String sql = "select state from orders where oid=?";
			return (Integer) qr.query(sql, new ScalarHandler(), oid);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void updatestate(String oid, int state) {
		try {
			String sql = "update orders set state=? where oid=?";
			qr.update(sql, state, oid);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
