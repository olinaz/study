package edu.npu.shop.dao.jdbc;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import edu.npu.shop.dao.OrderItemDAO;
import edu.npu.shop.domain.OrderItem;

@Repository("orderItemDaoJdbc")
public class OrderItemDaoJdbcImpl implements OrderItemDAO {
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	//private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate dbTemplate;
	private SimpleJdbcInsert jdbcInsert;
	private OrderItemRowMapper orderItemRowMapper;
	
	@PostConstruct
	public void setup() {
		//jdbcTemplate = new JdbcTemplate(dataSource);
		dbTemplate = new NamedParameterJdbcTemplate(dataSource);
		orderItemRowMapper = new OrderItemRowMapper();
		jdbcInsert = new SimpleJdbcInsert(dataSource)
		                 .withTableName("orderitem")
		                 .usingGeneratedKeyColumns("orderItemId")
		                 .usingColumns("orderId","prodId","numOfProdOrdered");
	}
		
	public void insertOrderItem(OrderItem orderItem) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(orderItem);
	    Number newId = jdbcInsert.executeAndReturnKey(params);
	    
	    orderItem.setOrderItemId(newId.intValue());
	}
	
	public List<OrderItem> findOrderItemsByOrderId(int orderId) {
		String sql = 
		    "SELECT * FROM orderitem WHERE orderId=:id";
			MapSqlParameterSource params = new MapSqlParameterSource("id", orderId);
			List<OrderItem> orderItemList = dbTemplate.query(sql, params,orderItemRowMapper);
		return orderItemList;
	}

}
