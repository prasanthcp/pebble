package com.prasanthprojects.pebble.jwt;

import com.prasanthprojects.pebble.repository.usersjparepository;
import com.prasanthprojects.pebble.service.users;
//import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
//@RequiredArgsConstructor
@RequestMapping("/auth")
public class authcontroller {

    private authservice authService;
    private usersjparepository usersJpaRepository;

    public authcontroller() {
    }

    public authcontroller(authservice authService, usersjparepository usersJpaRepository) {
        this.authService = authService;
        this.usersJpaRepository = usersJpaRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody com.prasanthprojects.pebble.jwt.authrequest request) {
        try {
            String username = request.getUsername();
            System.out.println(username +" ! ");
            String token = authService.authenticate(username, request.getPassword());
            System.out.println(username +" ! "+ token);
            Optional<users> user  = usersJpaRepository.findByUsername(username);
            if(user.isEmpty()) System.out.println("User not found!");
            return ResponseEntity.ok(new authresponse(token,user.get().getUsername(),user.get().getUserId()));
        } catch(Exception ex) {
            ex.getStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
