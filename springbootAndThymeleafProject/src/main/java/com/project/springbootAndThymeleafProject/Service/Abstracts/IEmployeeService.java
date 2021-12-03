package com.project.springbootAndThymeleafProject.Service.Abstracts;

import java.util.List;

import org.springframework.data.domain.Page;

import com.project.springbootAndThymeleafProject.Entities.Employee;

public interface IEmployeeService {
	
	List<Employee> getAllEmployee();
	void add(Employee employee);
	void update(Employee employee);
	void delete(long id);
	List<Employee> getByEmail(String email);
	Employee getById(long id);
	void updateEmployee(Employee employee);

	

}
