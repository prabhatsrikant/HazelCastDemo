package com.company.service;

import java.util.List;

import com.company.model.Employee;

public interface EmployeeService {
	
	public Employee insertEmployee(Employee employee);
	public void insertEmployees(List<Employee> employeeList);
	public List<Employee> getAllEmployees();
	public Employee getEmployeeById(String empId);

}
