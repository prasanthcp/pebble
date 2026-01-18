package com.prasanthprojects.pebble.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class authresponse {
    private String Token;
    private String username;
    private int userId;
}
