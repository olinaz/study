package edu.npu.shop.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.npu.shop.domain.Product;

public class ProdRowMapper implements RowMapper<Product> {
	public Product mapRow(ResultSet resultSet, int row) throws SQLException {
		Product prod;

		String name = resultSet.getString("name");
		float price = resultSet.getFloat("price");		
		prod = new Product(name, price);
		
		prod.setId(resultSet.getInt("id"));
		prod.setBrand(resultSet.getString("brand"));
		prod.setInvtQuantity(resultSet.getInt("invtquantity"));
		
		return prod;
		
	}
}
