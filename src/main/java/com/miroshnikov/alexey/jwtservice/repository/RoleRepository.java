package com.miroshnikov.alexey.jwtservice.repository;

import com.miroshnikov.alexey.jwtservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
