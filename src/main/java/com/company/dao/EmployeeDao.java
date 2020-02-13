package com.company.dao;

import java.util.List;

import com.company.model.Employee;

public interface EmployeeDao {
	
	void insertEmployee(Employee employee);
	void insertEmployees(List<Employee> employees);
	List<Employee> getAllEmployees();
	Employee getEmployeeById(String empId);

}
