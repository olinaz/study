package edu.npu.shop.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.npu.shop.domain.OrderItem;

public class OrderItemRowMapper implements RowMapper<OrderItem> {
	public OrderItem mapRow(ResultSet resultSet, int row) throws SQLException {
		OrderItem orderItem = new OrderItem();

		orderItem.setOrderItemId(resultSet.getInt("orderItemId"));
		orderItem.setOrderId(resultSet.getInt("orderId"));
		orderItem.setNumOfProdOrdered(resultSet.getInt("numOfProdOrdered"));
		orderItem.setProdId(resultSet.getInt("prodId"));

		return orderItem;
	}
}
