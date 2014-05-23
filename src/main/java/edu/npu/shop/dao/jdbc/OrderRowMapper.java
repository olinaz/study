package edu.npu.shop.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.npu.shop.domain.Order;

public class OrderRowMapper implements RowMapper<Order> {
	
	public Order mapRow(ResultSet resultSet, int row) throws SQLException {
		int id = resultSet.getInt("id");		
		Order order = new Order(id);
		//ArrayList<OrderItem> itemsOrdered = new ArrayList<OrderItem>();

		order.setDate(resultSet.getDate("date"));
		order.setCusname(resultSet.getString("cusname"));
		order.setSubtotal(resultSet.getDouble("subtotal"));
		order.setTax(resultSet.getDouble("tax"));
		order.setTotal(resultSet.getDouble("total"));
		//order.setItemsOrdered(itemsOrdered);
		
		return order;
	}
}
