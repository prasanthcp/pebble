package com.prasanthprojects.pebble.jwt;

import com.prasanthprojects.pebble.repository.usersjparepository;
//import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class authservice {

    private usersjparepository usersJpaRepository;
    private  jwtutil jwtUtil;


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

    public authservice(usersjparepository usersJpaRepository, jwtutil jwtUtil) {
        this.usersJpaRepository = usersJpaRepository;
        this.jwtUtil = jwtUtil;
    }

    public authservice() {
    }

    public usersjparepository getUsersJpaRepository() {
        return usersJpaRepository;
    }

    public void setUsersJpaRepository(usersjparepository usersJpaRepository) {
        this.usersJpaRepository = usersJpaRepository;
    }

    public jwtutil getJwtUtil() {
        return jwtUtil;
    }

    public void setJwtUtil(jwtutil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

}
