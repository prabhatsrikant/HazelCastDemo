package com.company.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.company.model.Employee;
import com.company.service.EmployeeService;

@EnableCaching
@Controller
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping("/save")
	public void insertEmployee() {
		Employee emp = new Employee();
		emp.setEmpId("emp");
		emp.setEmpName("emp");

		employeeService.insertEmployee(emp);

	}

	@GetMapping("/saveAll")
	public ResponseEntity insertEmployees() {
		Employee emp1 = new Employee();
		emp1.setEmpId("emp1");
		emp1.setEmpName("emp1");

		Employee emp2 = new Employee();
		emp2.setEmpId("emp2");
		emp2.setEmpName("emp2");

		List<Employee> employees = new ArrayList<>();
		employees.add(emp1);
		employees.add(emp2);
		employeeService.insertEmployees(employees);
		
		return new ResponseEntity(HttpStatus.OK);

	}

	@GetMapping("/getAll")
	public ResponseEntity getEmployees() {
		System.out.println("Inside the main class making call to service first time");
		List<Employee> employeeList1 = employeeService.getAllEmployees();
		for (Employee employee : employeeList1) {
			System.out.println(employee.toString());
		}
		
		return new ResponseEntity(employeeList1, HttpStatus.OK);
				

	}

}
