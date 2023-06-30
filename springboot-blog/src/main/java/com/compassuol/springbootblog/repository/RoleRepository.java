package com.compassuol.springbootblog.repository;

import com.compassuol.springbootblog.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName( String username);
}
