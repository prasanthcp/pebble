package com.prasanthprojects.pebble.service;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
//@Builder @Data @NoArgsConstructor @AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class users {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(nullable = false, unique = true)
    private String username;
    private String encryptedPassword;

    @CreatedDate
    private LocalDateTime creationDate;

    @LastModifiedDate
    private LocalDateTime lastUpdateDate;

    private int objectVersion;
    private String role;

    public users(int objectVersion, int userId, String username, String encryptedPassword, LocalDateTime creationDate, LocalDateTime lastUpdateDate, String role) {
        this.objectVersion = objectVersion;
        this.userId = userId;
        this.username = username;
        this.encryptedPassword = encryptedPassword;
        this.creationDate = creationDate;
        this.lastUpdateDate = lastUpdateDate;
        this.role = role;
    }

    public users() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public int getObjectVersion() {
        return objectVersion;
    }

    public void setObjectVersion(int objectVersion) {
        this.objectVersion = objectVersion;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
