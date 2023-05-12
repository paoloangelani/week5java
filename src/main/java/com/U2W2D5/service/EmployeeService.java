package com.U2W2D5.service;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.U2W2D5.entity.Employee;
import com.U2W2D5.repository.DeviceRepository;
import com.U2W2D5.repository.EmployeeRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeService {

	 @Autowired private EmployeeRepository empRepo;
	 @Autowired private DeviceRepository devRepo;
	 
	 @Autowired @Qualifier("FakeUser") private ObjectProvider<Employee> fakeProvider;
	 
	public void saveFakeEmployee() {
		 Employee u = fakeProvider.getObject();
		 saveEmployee(u);
		 log.info("Fake user succesfully created!");
	 }

	public Employee saveEmployee(Employee u) {
		if (empRepo.existsByEmail(u.getEmail())) {
			throw new EntityExistsException("Email already registered!");
		}
		empRepo.save(u);
		log.info("User succesfully saved");
		return u;
	}
	
	public String deleteEmployee(Long id) {
		Employee e = findById(id);
		empRepo.delete(e);
		return "Employee successfully deleted!";
	}
	
	public Employee updateEmployee(Employee e) {
		if (!empRepo.existsById(e.getId())) {
			throw new EntityNotFoundException("No employee saved on DB with given ID. Please make sure employee is already saved before updating.");
		}
		empRepo.save(e);
		return e;
	}
	
	public Employee findById(Long id) {
		if(!empRepo.existsById(id)) {
			throw new EntityNotFoundException("No employee found with given ID.");
		}
		return empRepo.findById(id).get();
	}
	
	public Employee findByUsername(String username) {
		if (!empRepo.existsByUsername(username)) {
			throw new EntityNotFoundException("No employee found with given username.");
		}
		return empRepo.findByUsername(username).get();
	}
	
	public Employee findByEmail(String email) {
		if (!empRepo.existsByEmail(email)) {
			throw new EntityNotFoundException("No employee found with given email.");
		}
		return empRepo.findByEmail(email).get();
	}
	
	public Page<Employee> findAllUsers(Pageable pageable) {
		return (Page<Employee>) empRepo.findAll(pageable);
	}
}
