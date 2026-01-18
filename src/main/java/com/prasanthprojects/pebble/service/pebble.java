package com.prasanthprojects.pebble.service;
import jakarta.persistence.*;
//import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
//@Data @Builder
//@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class pebble {
    public LocalDateTime getPebbleStartTime() {
        return pebbleStartTime;
    }

    public void setPebbleStartTime(LocalDateTime pebbleStartTime) {
        this.pebbleStartTime = pebbleStartTime;
    }

    public Integer getPebbleId() {
        return pebbleId;
    }

    public void setPebbleId(Integer pebbleId) {
        this.pebbleId = pebbleId;
    }

    public users getUser() {
        return user;
    }

    public void setUser(users user) {
        this.user = user;
    }

    public String getPebbleNotes() {
        return pebbleNotes;
    }

    public void setPebbleNotes(String pebbleNotes) {
        this.pebbleNotes = pebbleNotes;
    }

    public String getPebbleState() {
        return pebbleState;
    }

    public void setPebbleState(String pebbleState) {
        this.pebbleState = pebbleState;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public LocalDateTime getPebbleEndTime() {
        return pebbleEndTime;
    }

    public void setPebbleEndTime(LocalDateTime pebbleEndTime) {
        this.pebbleEndTime = pebbleEndTime;
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

    public Integer getObjectVersion() {
        return objectVersion;
    }

    public void setObjectVersion(Integer objectVersion) {
        this.objectVersion = objectVersion;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pebbleId;

    public pebble(LocalDateTime lastUpdateDate, Integer pebbleId, project project, users user, String pebbleNotes, String pebbleState, String topic, LocalDateTime pebbleStartTime, LocalDateTime pebbleEndTime, LocalDateTime creationDate, Integer objectVersion) {
        this.lastUpdateDate = lastUpdateDate;
        this.pebbleId = pebbleId;
        this.project = project;
        this.user = user;
        this.pebbleNotes = pebbleNotes;
        this.pebbleState = pebbleState;
        this.topic = topic;
        this.pebbleStartTime = pebbleStartTime;
        this.pebbleEndTime = pebbleEndTime;
        this.creationDate = creationDate;
        this.objectVersion = objectVersion;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id")
    private project project;

    @ManyToOne @JoinColumn(name = "user_id")
    private users user;

    private String pebbleNotes;
    private String pebbleState;
    private String topic;

    private LocalDateTime pebbleStartTime;
    private LocalDateTime pebbleEndTime;

    @CreatedDate
    private LocalDateTime creationDate;

    @LastModifiedDate
    private LocalDateTime lastUpdateDate;

    private Integer objectVersion = 1;

    public pebble() {
        this.pebbleState = "STARTED";
        this.pebbleStartTime = LocalDateTime.now();
    }

    public void setPebble(pebble existing, users user) {
        this.user = user;
        this.pebbleNotes = existing.getPebbleNotes();
        this.pebbleStartTime = existing.getPebbleStartTime();
        this.pebbleEndTime = existing.getPebbleEndTime();
        this.pebbleState = "COMPLETED";
        this.topic = existing.getTopic();
        this.objectVersion++;
    }

    public void setProject(project project) {
        this.project = project;
    }

    public project getProject() {
        return project;
    }
}
