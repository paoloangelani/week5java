package com.U2W2D5.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees", uniqueConstraints = { @UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email") })
@Data
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(	name = "employees_devices", 
				joinColumns = @JoinColumn(name = "employee_id"), 
				inverseJoinColumns = @JoinColumn(name = "device_id"))
	private Set<Device> devices = new HashSet<>();	
}
