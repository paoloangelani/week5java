package com.U2W2D5.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.U2W2D5.entity.Device;
import com.U2W2D5.entity.DeviceStatus;
import com.U2W2D5.entity.DeviceType;
import com.U2W2D5.entity.Employee;

public interface DeviceRepository extends CrudRepository<Device, Long>, PagingAndSortingRepository<Device, Long> {

	Page<Device> findByType(DeviceType type, Pageable pageable);

	Page<Device> findByStatus(DeviceStatus status, Pageable pageable);

}
