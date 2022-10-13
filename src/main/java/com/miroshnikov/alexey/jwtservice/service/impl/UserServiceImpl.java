package com.miroshnikov.alexey.jwtservice.service.impl;

import com.miroshnikov.alexey.jwtservice.model.Role;
import com.miroshnikov.alexey.jwtservice.model.Status;
import com.miroshnikov.alexey.jwtservice.model.User;
import com.miroshnikov.alexey.jwtservice.repository.RoleRepository;
import com.miroshnikov.alexey.jwtservice.repository.UserRepository;
import com.miroshnikov.alexey.jwtservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {

        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);

        User registeredUser = userRepository.save(user);

        log.info("IN register method - User: {} successfully registered", registeredUser);

        return registeredUser;
    }

    @Override
    public List<User> getAll() {

        List<User> allUsers = userRepository.findAll();

        log.info("IN getAll method - All users {} successfully get", allUsers);

        return allUsers;
    }

    @Override
    public User findByUserName(String username) {

        User user = userRepository.findByUsername(username);

        log.info("IN findByUserName method - User with name {} successfully get. User: {}", username, user);

        return user;
    }

    @Override
    public User findById(Long id) {

        User user = userRepository.findById(id).orElse(null);

        if(user == null)  {
            log.info("IN findById method - User with id {} not found", id);
            return null;
        }

        log.info("IN findById method - User with is {} successfully get. User: {}", id, user);

        return user;
    }

    @Override
    public void delete(Long id) {

        userRepository.deleteById(id);

        log.info("IN delete method - User with id {} successfully delete.", id);
    }
}
