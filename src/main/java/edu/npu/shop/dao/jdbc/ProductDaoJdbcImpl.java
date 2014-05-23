package edu.npu.shop.dao.jdbc;

import edu.npu.shop.dao.ProductDAO;
import edu.npu.shop.domain.Product;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;


@Repository("productDaoJdbc")
public class ProductDaoJdbcImpl implements ProductDAO {
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	private ProdRowMapper prodRowMapper;
	
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert jdbcInsert;
	
	@PostConstruct
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		prodRowMapper = new ProdRowMapper();
		jdbcInsert = new SimpleJdbcInsert(dataSource)
        .withTableName("Product")
        .usingGeneratedKeyColumns("id")
        .usingColumns("name", "brand", "price","invtquantity");
	}
	
	public int findProductCount() {
		String sql = "select count(*) from Product";
		return jdbcTemplate.queryForInt(sql);
	}

	public int findInvtQuantityByName(String prodName) {
		String sql = "select invtquantity from Product where name=?";
		return jdbcTemplate.queryForInt(sql,prodName);
	}
	public int findInvtQuantityById(int id) {
		String sql = "select invtquantity from Product where id=?";
		return jdbcTemplate.queryForInt(sql,id);
	}

	public String findProdNameById(int id) {
		String sql = "select name from Product where id=?";
		return jdbcTemplate.queryForObject(sql, String.class, id);
	}
	
	public void insertProduct(Product prod) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(prod);
	    Number newId = jdbcInsert.executeAndReturnKey(params);
	    prod.setId(newId.intValue());
	}
	
	public int updateProdInvt(int id, int invt) {		
		String sql = "update Product set invtquantity= ? where id=?";		
		int rowsChanged = jdbcTemplate.update(sql,invt,id);
		return rowsChanged;
	}
	public int updateProdPrice(int id, double price) {		
		String sql = "update Product set price= ? where id=?";		
		int rowsChanged = jdbcTemplate.update(sql,price,id);
		return rowsChanged;
	}

	public Product findProductByName(String name) {
		String sql = "select * from Product where name=?";		
		Product prod = jdbcTemplate.queryForObject(sql,prodRowMapper,name);
		return prod;
	}
	public Product findProductById(int id) {
		String sql = "select * from Product where id=?";		
		Product prod = jdbcTemplate.queryForObject(sql,prodRowMapper,id);
		return prod;
	}
	
	public List<Product> findProductsByKeyword(String name) {
		String sql = "select * from Product where name like '%" + name + "%'";
		List<Product> prodList = jdbcTemplate.query(sql, prodRowMapper);
		return prodList;
	}
	
	public List<Product> findAllProducts() {
		String sql = "select * from Product";
		List<Product> prodList = jdbcTemplate.query(sql, prodRowMapper);
		return prodList;
	}

	public List<Product> findAllProductsWithInvt() {
		String sql = "select * from Product where invtquantity > 0";
		List<Product> prodList = jdbcTemplate.query(sql, prodRowMapper);
		return prodList;
	}
}
