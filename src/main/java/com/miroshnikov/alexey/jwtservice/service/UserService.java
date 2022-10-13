package com.miroshnikov.alexey.jwtservice.service;

import com.miroshnikov.alexey.jwtservice.model.User;

import java.util.List;

public interface UserService {

    User register(User user);
    List<User> getAll();
    User findByUserName(String user);
    User findById(Long id);
    void delete(Long id);
}
