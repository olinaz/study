package edu.npu.shop.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.npu.shop.domain.Customer;


public class CustRowMapper implements RowMapper<Customer>{
	public Customer mapRow(ResultSet resultSet, int row) throws SQLException {
		Customer cust;
		
		cust = new Customer();
		cust.setId(resultSet.getInt("id"));
		cust.setName(resultSet.getString("name"));
		cust.setAddr(resultSet.getString("addr"));
		cust.setEmail(resultSet.getString("email"));
		cust.setPhone(resultSet.getString("phone"));
		cust.setState(resultSet.getString("state"));
		
		return cust;		
	}
}
