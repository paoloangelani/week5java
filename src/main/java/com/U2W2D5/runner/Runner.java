package com.U2W2D5.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.U2W2D5.entity.Device;
import com.U2W2D5.entity.DeviceStatus;
import com.U2W2D5.entity.DeviceType;
import com.U2W2D5.entity.Employee;
import com.U2W2D5.service.DeviceService;
import com.U2W2D5.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Runner implements ApplicationRunner {
	
	@Autowired
	EmployeeService employeeDAO;
	
	@Autowired
	DeviceService deviceDAO;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
	log.info("### Main runner executed. ###");
	
//	employeeDAO.saveFakeEmployee();
//	Device d = Device.builder().type(DeviceType.LAPTOP).status(DeviceStatus.AVAILABLE).build();
//	deviceDAO.saveDevice(d);
//	deviceDAO.assignDevice(1l, 1l);
	}

}
