package com.project.springbootAndThymeleafProject.Service.Concretes;

import java.util.List;
import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.springbootAndThymeleafProject.DataAccess.IEmployeeDao;
import com.project.springbootAndThymeleafProject.Entities.Employee;
import com.project.springbootAndThymeleafProject.Service.Abstracts.IEmployeeService;
@Service
public class EmployeeManager implements IEmployeeService{
	
	//Dependcy injection
	private IEmployeeDao employeeDao;
	@Autowired
	public EmployeeManager(IEmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	@Override
	public List<Employee> getAllEmployee() {
		return this.employeeDao.findAll();
	}

	@Override
	public void add(Employee employee) {
		if(employee.getId()>0) {
			
		}
		this.employeeDao.save(employee);
	}

	@Override
	public void update(Employee employee) {
		Employee employeeToUpdate=this.getById(employee.getId());
		this.employeeDao.save(employeeToUpdate);
	}

	@Override
	public void delete(long id) {
		Employee employeeToDelete=this.employeeDao.findById(id).orElse(null);
		this.employeeDao.delete(employeeToDelete);
	}

	@Override
	public List<Employee> getByEmail(String email){
		
		return this.employeeDao.getByEmail(email);
	}

	@Override
	public Employee getById(long id) {
		return this.employeeDao.getById(id);
	}

	@Override
	public void updateEmployee(Employee employee) {
		Employee updateToemployee=this.getById(employee.getId());
		updateToemployee.setFirstName(employee.getFirstName());
		updateToemployee.setLastName(employee.getLastName());
		updateToemployee.setEmail(employee.getEmail());
		this.employeeDao.updateEmployee(updateToemployee.getFirstName(),updateToemployee.getLastName(),updateToemployee.getEmail(),updateToemployee.getId());
	}


}
