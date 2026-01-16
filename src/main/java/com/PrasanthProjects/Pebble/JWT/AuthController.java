package com.PrasanthProjects.Pebble.JWT;

import com.PrasanthProjects.Pebble.Repository.UsersJpaRepository;
import com.PrasanthProjects.Pebble.Service.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController @RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    @Autowired private final AuthService authService;
    @Autowired private final UsersJpaRepository usersJpaRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            String username = request.getUsername();
            System.out.println(username +" ! ");
            String token = authService.authenticate(username, request.getPassword());
            System.out.println(username +" ! "+ token);
            Optional<Users> user  = usersJpaRepository.findByUsername(username);
            if(user.isEmpty()) System.out.println("User not found!");
            return ResponseEntity.ok(new AuthResponse(token,user.get().getUsername(),user.get().getUserId()));
        } catch(Exception ex) {
            ex.getStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
