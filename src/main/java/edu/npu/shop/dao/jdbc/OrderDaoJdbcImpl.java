package edu.npu.shop.dao.jdbc;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import edu.npu.shop.dao.OrderDAO;
import edu.npu.shop.domain.Customer;
import edu.npu.shop.domain.Order;


import org.springframework.beans.factory.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import edu.npu.shop.dao.jdbc.OrderRowMapper;

@Repository("orderDaoJdbc")
public class OrderDaoJdbcImpl implements OrderDAO {
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate dbTemplate;
	private SimpleJdbcInsert jdbcInsert;
	private OrderRowMapper orderRowMapper;
	
	@PostConstruct
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		dbTemplate = new NamedParameterJdbcTemplate(dataSource);
		orderRowMapper = new OrderRowMapper();
		jdbcInsert = new SimpleJdbcInsert(dataSource)
		                 .withTableName("CusOrder")
		                 .usingGeneratedKeyColumns("id")
		                 .usingColumns("cusname", "date","subtotal","tax","total");
	}
	

	public int findOrderCount() {
		String sql = "select count(*) FROM CusOrder";
		return jdbcTemplate.queryForInt(sql);
	}

	public List<Order> findAllOrders() {
		String sql = "SELECT * FROM CusOrder";
		List<Order> orderList = jdbcTemplate.query(sql, orderRowMapper);
		return orderList;
	}

	public Order findOrderById(int id) {
		String sql = "SELECT * FROM CusOrder WHERE id = :id";
		MapSqlParameterSource params = new MapSqlParameterSource("id", id);
		Order order = dbTemplate.queryForObject(sql, params, orderRowMapper);
		return order;
	}

	public List<Order> findOrderByCust(Customer cust) {
		String sql = 
		    "SELECT * FROM CusOrder WHERE cusname=:name";
			BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(cust);
			List<Order> orderList = dbTemplate.query(sql, params, orderRowMapper);
		return orderList;
	}

	public void insertOrder(Order order) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(order);
	    Number newId = jdbcInsert.executeAndReturnKey(params);
	    
	    order.setId(newId.intValue());
	}

	public int deleteOrder(int id) {
		String sql = "delete from cusorder where id= ?";
		int rowsRemoved = jdbcTemplate.update(sql,id);
		return rowsRemoved;
	}

}
