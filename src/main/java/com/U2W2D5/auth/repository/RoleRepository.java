package com.U2W2D5.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.U2W2D5.auth.entity.ERole;
import com.U2W2D5.auth.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
	Optional<Role> findByRoleName(ERole roleName);

}
