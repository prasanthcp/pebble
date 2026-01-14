package com.PrasanthProjects.Pebble.Service;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity  @Data @Builder
@AllArgsConstructor @EntityListeners(AuditingEntityListener.class)
public class Pebble {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pebbleId;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne @JoinColumn(name = "user_id")
    private Users user;

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

    public Pebble() {
        this.pebbleState = "STARTED";
        this.pebbleStartTime = LocalDateTime.now();
    }

    public void setPebble(Pebble existing, Users user) {
        this.user = user;
        this.pebbleNotes = existing.getPebbleNotes();
        this.pebbleState = "COMPLETED";
        this.topic = existing.getTopic();
        this.objectVersion++;
    }

}
