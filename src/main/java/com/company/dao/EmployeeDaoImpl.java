package com.company.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.company.model.Employee;

@Repository
public class EmployeeDaoImpl extends JdbcDaoSupport implements EmployeeDao{
	
	@Autowired
	DataSource dataSource;
	
	@PostConstruct
	private void initalize() {
		setDataSource(dataSource);
	}

	@Override
	public void insertEmployee(Employee employee) {
		String sql = "INSERT INTO employee "+
						"(empId , empName) VALUES (?, ?)";
		getJdbcTemplate().update(sql, new Object[] {
				employee.getEmpId(), employee.getEmpName()
		});
	}

	@Override
	public void insertEmployees(List<Employee> employees) {
		String  sql = "INSERT INTO employee " + 
						"(empId, empName) VALUES (?, ?)";
		getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Employee employee = employees.get(i);
				ps.setString(1, employee.getEmpId());
				ps.setString(2, employee.getEmpName());
			}
			
			@Override
			public int getBatchSize() {
				return employees.size();
			}
		});
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employeeList = new ArrayList<Employee>();
		String sql = "SELECT * FROM employee";
		List<Map<String, Object>> result = getJdbcTemplate().queryForList(sql);
		for (Map<String, Object> row : result) {
			Employee emp = new Employee();
			emp.setEmpId((String) row.get("empId"));
			emp.setEmpName((String) row.get("empName"));
			employeeList.add(emp);
		}
		
		return employeeList;
	}

	@Override
	public Employee getEmployeeById(String empId) {
		String sql = "SELECT * FROM employee where empId = ?";
		return getJdbcTemplate().queryForObject(sql, new Object[]{empId}, new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				Employee employee = new Employee();

				employee.setEmpId(rs.getString("empId"));
				employee.setEmpName(rs.getString("empName"));
				return employee;
			}
		});
	}
	
	

}
