package com.miroshnikov.alexey.jwtservice.repository;

import com.miroshnikov.alexey.jwtservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String name);
}
