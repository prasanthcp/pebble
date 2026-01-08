package com.PrasanthProjects.Pebble.Controller;

import com.PrasanthProjects.Pebble.Repository.UsersJpaRepository;
import com.PrasanthProjects.Pebble.Service.Project;
import com.PrasanthProjects.Pebble.Service.Users;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.attribute.UserPrincipalNotFoundException;

@RestController
public class UserController {

    private UsersJpaRepository usersJpaRepository;

    public UserController(UsersJpaRepository usersJpaRepository) {
        this.usersJpaRepository = usersJpaRepository;
    }

    @GetMapping("/getUser/{username}")
    public ResponseEntity<Users> getAUser(@PathVariable String username) throws Exception {
        System.out.println(username);
        Users user =  usersJpaRepository.findByUsername(username);
        if(user==null) {
            throw new UserPrincipalNotFoundException(username);
        } else return new ResponseEntity<>(user,HttpStatus.OK);
    }
}
