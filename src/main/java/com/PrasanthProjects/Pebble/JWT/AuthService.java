package com.PrasanthProjects.Pebble.JWT;

import com.PrasanthProjects.Pebble.Repository.UsersJpaRepository;
import com.PrasanthProjects.Pebble.Service.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthService {

    private final UsersJpaRepository usersJpaRepository;
    private final JwtUtil jwtUtil;

    public String authenticate(String username, String password) {

        Users user = usersJpaRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Invalid username"));

        if (!user.getEncryptedPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        System.out.println("-----> User logged in: " + user.getUserId());

        String token = jwtUtil.generateJWTToken(username);
        System.out.println("-----> JWT issued: " + token);

        return token;
    }
}
