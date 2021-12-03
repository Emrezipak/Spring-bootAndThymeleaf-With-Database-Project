package com.project.springbootAndThymeleafProject.DataAccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.springbootAndThymeleafProject.Entities.Employee;

public interface IEmployeeDao extends JpaRepository<Employee,Long>{

	@Query(" from Employee e where e.email like %:email%")
	List<Employee> getByEmail(@Param(value="email") String email);
		
	@Modifying
	@Transactional
	@Query("Update Employee e set e.firstName=:firstName, e.lastName=:lastName, e.email=:email where e.id=:id")
	void updateEmployee(String firstName,String lastName,String email,long id);
	
	Employee getById(long id);


}
