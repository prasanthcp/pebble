package com.prasanthprojects.pebble.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class authrequest {
    private String username;
    private String password;
}
