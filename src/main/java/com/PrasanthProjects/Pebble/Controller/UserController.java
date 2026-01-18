package com.prasanthprojects.pebble.controller;

import com.prasanthprojects.pebble.repository.usersjparepository;
import com.prasanthprojects.pebble.service.users;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.attribute.UserPrincipalNotFoundException;

@RestController
public class usercontroller {

    private usersjparepository usersJpaRepository;

    public usercontroller(usersjparepository usersJpaRepository) {
        this.usersJpaRepository = usersJpaRepository;
    }

    public usercontroller() {
    }

    public users getAuthenticatedUser() throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return usersJpaRepository.findByUsername(username)
                .orElseThrow(()-> new UserPrincipalNotFoundException(username));
    }
}
