package com.prasanthprojects.pebble.service;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity  @Data @Builder
@AllArgsConstructor @EntityListeners(AuditingEntityListener.class)
public class pebble {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pebbleId;

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

    @Builder.Default
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

}
