package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.company.dao.EmployeeDao;
import com.company.model.Employee;


@Service
@CacheConfig(cacheNames = "employees")
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	EmployeeDao employeeDao;

	@Override
	public Employee insertEmployee(Employee employee) {
		employeeDao.insertEmployee(employee);
		return employee;
	}

	@Override
	public void insertEmployees(List<Employee> employeeList) {
		employeeDao.insertEmployees(employeeList);
	}

	@Override
	@Cacheable
	public List<Employee> getAllEmployees() {
		System.out.println("Inside the service layer");
		return employeeDao.getAllEmployees();
	}

	@Override
	public Employee getEmployeeById(String empId) {
		return employeeDao.getEmployeeById(empId);
	}
	

}
