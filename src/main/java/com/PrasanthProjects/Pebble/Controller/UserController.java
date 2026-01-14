package com.PrasanthProjects.Pebble.Controller;

import com.PrasanthProjects.Pebble.Repository.UsersJpaRepository;
import com.PrasanthProjects.Pebble.Service.Project;
import com.PrasanthProjects.Pebble.Service.Users;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Optional;

@RestController
public class UserController {

    private UsersJpaRepository usersJpaRepository;

    public UserController(UsersJpaRepository usersJpaRepository) {
        this.usersJpaRepository = usersJpaRepository;
    }

    public Users getAuthenticatedUser() throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return usersJpaRepository.findByUsername(username)
                .orElseThrow(()-> new UserPrincipalNotFoundException(username));
    }
}
