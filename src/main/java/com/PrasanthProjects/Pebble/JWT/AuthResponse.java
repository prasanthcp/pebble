package com.PrasanthProjects.Pebble.JWT;

import com.PrasanthProjects.Pebble.Service.Users;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class AuthResponse {
    private String Token;
    private String username;
    private int userId;
}
