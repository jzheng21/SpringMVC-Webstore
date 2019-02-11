package com.packt.webstore.domain.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.packt.webstore.domain.Customer;
import com.packt.webstore.domain.repository.CustomerRepository;

@Repository
public class InMemoryCutomerRepository implements CustomerRepository{
	
	@Autowired
	private NamedParameterJdbcTemplate jdbctemplate;
	
	@Override
	public List<Customer> getAllCustomers(){
		Map<String, Object> params = new HashMap<String, Object>();
		List<Customer> result = jdbctemplate.query("SELECT * FROM CUSTOMERS", params, new CustomerMapper());
		
		return result;
	}
	
	private static final class CustomerMapper implements RowMapper<Customer>{
		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException{
			Customer customer = new Customer();
			customer.setAddress(rs.getString("ADDRESS"));
			customer.setCustomerId(rs.getInt("ID"));
			customer.setName(rs.getString("NAME"));
			customer.setNoOfOrdersMade(rs.getInt("ORDERS_MADE"));
			return customer;
		}
	}
}
