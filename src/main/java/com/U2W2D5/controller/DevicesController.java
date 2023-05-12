package com.U2W2D5.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.U2W2D5.entity.Device;
import com.U2W2D5.entity.DeviceStatus;
import com.U2W2D5.entity.DeviceType;
import com.U2W2D5.entity.Employee;
import com.U2W2D5.service.DeviceService;
import com.U2W2D5.service.EmployeeService;

import jakarta.persistence.EntityNotFoundException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/devices")
public class DevicesController {
	
	@Autowired
	DeviceService devService;
	
	@Autowired
	EmployeeService empService;
	
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAllDevices(Pageable pageable) {
		return new ResponseEntity<>(devService.findAllDevices(pageable), HttpStatus.OK);
	}
	
	@GetMapping(path = "/available")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getAvailableDevices(Pageable pageable){
		return new ResponseEntity<>(devService.findByStatus(DeviceStatus.AVAILABLE, pageable), HttpStatus.OK);
	}
	
	@GetMapping(path = "/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getById(@PathVariable(name = "id") Long id) {
		return new ResponseEntity<>(devService.findById(id), HttpStatus.OK);
	}
	
	@GetMapping(path = "/status/{status}")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<?> getStatusDevices(@PathVariable(name = "status") String status, Pageable pageable){
		return new ResponseEntity<>(devService.findByStatus(DeviceStatus.valueOf(status.toUpperCase()), pageable), HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> createDevice(@RequestBody Device d){
		return new ResponseEntity<>(devService.saveDevice(d), HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteById(@PathVariable Long id){
			return new ResponseEntity<>(devService.deleteDevice(id), HttpStatus.OK);
	}
	
	@PutMapping(path = "/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> assignDevice(@RequestBody Employee e, @PathVariable(name = "id") Long id){
		Device d = devService.findById(id);
		if (!d.getStatus().equals(DeviceStatus.AVAILABLE)) {
			throw new EntityNotFoundException("Unable to assign device as its status is: " + d.getStatus());
		}
		Employee emp = empService.findById(e.getId());
		d.setStatus(DeviceStatus.ASSIGNED);
		Set<Device> devices = emp.getDevices();
		devices.add(d);
		emp.setDevices(devices);
		empService.updateEmployee(emp);
		return new ResponseEntity<>(devService.updateDevice(d), HttpStatus.OK);
	}
	

}
