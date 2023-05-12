package com.U2W2D5.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.U2W2D5.entity.Device;
import com.U2W2D5.entity.DeviceStatus;
import com.U2W2D5.entity.DeviceType;
import com.U2W2D5.entity.Employee;
import com.U2W2D5.repository.DeviceRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DeviceService {
	
	@Autowired DeviceRepository devRepo;
	
	@Autowired EmployeeService empDAO;
	
	public Device findById(Long id) {
		if(!devRepo.existsById(id)) {
			throw new EntityNotFoundException("No device found with given ID.");
		}
		return devRepo.findById(id).get();
	}
	
	public Device saveDevice(Device d) {
		return devRepo.save(d);
	}
	
	public String deleteDevice(Long id) {
		Device d = findById(id);
		devRepo.delete(d);
		return "Device successfully deleted!";
	}
	
	public Device updateDevice(Device d) {
		if (!devRepo.existsById(d.getId())) {
			throw new EntityNotFoundException("No device found with given ID.");
		}
		return d;
	}
	
	public void assignDevice(Long emp_id, Long dev_id) {
		Employee emp = empDAO.findById(emp_id);
		Device dev = findById(dev_id);

		dev.setStatus(DeviceStatus.ASSIGNED);
		Set<Device> devices = emp.getDevices();
		devices.add(dev);
		log.info("Succesfully assigned device to employee!");
		emp.setDevices(devices);
		empDAO.updateEmployee(emp);
		updateDevice(dev);
	}
	
	public void changeStatus(Long id, DeviceStatus status) {
		Device device = findById(id);
		device.setStatus(status);
		updateDevice(device);
	}
	
	public Page<Device> findByType(DeviceType type, Pageable pageable) {
		return (Page<Device>) devRepo.findByType(type, pageable);
	}
	
	public Page<Device> findByStatus(DeviceStatus status, Pageable pageable) {
		return (Page<Device>) devRepo.findByStatus(status, pageable);
	}
	
	public Page<Device> findAllDevices(Pageable pageable) {
		return (Page<Device>) devRepo.findAll(pageable);
	}
	
}
