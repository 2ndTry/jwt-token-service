package com.miroshnikov.alexey.jwtservice.security;

import com.miroshnikov.alexey.jwtservice.model.User;
import com.miroshnikov.alexey.jwtservice.security.jwt.JwtUser;
import com.miroshnikov.alexey.jwtservice.security.jwt.JwtUserFactory;
import com.miroshnikov.alexey.jwtservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.findByUserName(username);

        if (user == null) throw new UsernameNotFoundException(String.format("User with name {} not fount", username));

        JwtUser jwtUser = JwtUserFactory.create(user);

        log.info("IN loadByUserName - user with username: {} successfully loaded", username);

        return jwtUser;
    }
}
