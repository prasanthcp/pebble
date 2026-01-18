package com.prasanthprojects.pebble.jwt;

//import lombok.AllArgsConstructor;
//import lombok.Data;

//@Data @AllArgsConstructor
public class authresponse {
    private String Token;
    private String username;
    private int userId;

    public authresponse() {
    }

    public authresponse(String token, String username, int userId) {
        Token = token;
        this.username = username;
        this.userId = userId;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
