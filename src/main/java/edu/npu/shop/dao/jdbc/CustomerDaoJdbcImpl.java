package edu.npu.shop.dao.jdbc;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import edu.npu.shop.dao.CustomerDAO;
import edu.npu.shop.domain.Customer;

@Repository("customerDaoJdbc")
public class CustomerDaoJdbcImpl implements CustomerDAO {
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	//private NamedParameterJdbcTemplate dbTemplate;
	private SimpleJdbcInsert jdbcInsert;
	
	private CustRowMapper custRowMapper;
	
	@PostConstruct
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		//dbTemplate = new NamedParameterJdbcTemplate(dataSource);
		
		custRowMapper = new CustRowMapper();
		
		jdbcInsert = new SimpleJdbcInsert(dataSource)
		                 .withTableName("Customer")
		                 .usingGeneratedKeyColumns("id")
		                 .usingColumns("name", "state", "addr","email","phone");
	}
	
	public int findCustCount() {
		String sql = "select count(*) from Customer";
		return jdbcTemplate.queryForInt(sql);
	}
	
	public Customer findCustByName(String name) {
		String sql = "select * from Customer where name=?";	
		try{
		Customer cust = jdbcTemplate.queryForObject(sql,custRowMapper,name);
		return cust;
		} catch (Exception ex) {
			Customer cust = null;
			return cust;
		}
	}

	public void insertCust(Customer cust) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(cust);
	    Number newId = jdbcInsert.executeAndReturnKey(params);
	    cust.setId(newId.intValue());
	}

	public int updateCustAddr(int id, String addr) {		
		String sql = "update Customer set addr= ? where id=?";		
		int rowsChanged = jdbcTemplate.update(sql,addr,id);
		return rowsChanged;
	}
	public int updateCustEmail(int id, String email) {		
		String sql = "update Customer set email= ? where id=?";		
		int rowsChanged = jdbcTemplate.update(sql,email,id);
		return rowsChanged;
	}
	public int updateCustPhone(int id, String phone) {		
		String sql = "update Customer set phone= ? where id=?";		
		int rowsChanged = jdbcTemplate.update(sql,phone,id);
		return rowsChanged;
	}

	public int deleteCust(int id) {
		String sql = "delete from Customer where id= ?";
		int rowsRemoved = jdbcTemplate.update(sql,id);
		return rowsRemoved;
	}

}
