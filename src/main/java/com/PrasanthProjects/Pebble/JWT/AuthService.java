package com.prasanthprojects.pebble.jwt;

import com.prasanthprojects.pebble.repository.usersjparepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class authservice {

    private final usersjparepository usersJpaRepository;
    private final jwtutil jwtUtil;

    public String authenticate(String username, String password) {

        com.prasanthprojects.pebble.service.users user = usersJpaRepository.findByUsername(username)
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
