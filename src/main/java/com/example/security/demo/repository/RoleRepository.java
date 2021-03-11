package com.example.security.demo.repository;

import java.util.Optional;

import com.example.security.demo.model.Role;
import com.example.security.demo.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}