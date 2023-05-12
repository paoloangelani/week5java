package com.U2W2D5.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.U2W2D5.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long>, PagingAndSortingRepository<Employee, Long>{
	
	Optional<Employee> findByUsername(String username);
	
	Optional<Employee> findByEmail(String email);
	
	boolean existsByUsername(String username);
	
	boolean existsByEmail(String email);
	
}
