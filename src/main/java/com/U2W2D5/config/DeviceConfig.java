package com.U2W2D5.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.U2W2D5.entity.Device;
import com.U2W2D5.entity.DeviceStatus;
import com.U2W2D5.entity.DeviceType;

@Configuration
public class DeviceConfig {
	
	@Bean("Device")
	@Scope("prototype")
	public Device device(DeviceType type, DeviceStatus status) {
		return Device.builder().type(type).status(status).build();
	}
}
