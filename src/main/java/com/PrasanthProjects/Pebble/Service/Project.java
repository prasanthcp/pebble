package com.PrasanthProjects.Pebble.Service;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity @Data @Builder // Use the builder for creation, keep setters for updates.
@NoArgsConstructor @AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Project {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer projectId;

    private String projectCode;
    private String projectName;

    @ManyToOne @JoinColumn(name = "user_id")
    private Users user;

    private String projectDescription;
    private String topic1;
    private String topic2;
    private String topic3;

    @Builder.Default
    private Integer projectStatus = 1; // default inactive

    @Builder.Default
    private Integer frequency = 6; // default frequency

    @Builder.Default
    private LocalDateTime projectStartDate = LocalDateTime.now(); // default to today

    @CreatedDate
    private LocalDateTime creationDate;

    @LastModifiedDate
    private LocalDateTime lastUpdateDate;

    @Builder.Default
    private Integer objectVersion = 0;

    /** Custom update method to copy fields from another project and associate with a given user. */
    public void setProject(Project project, Users user) {
        this.user = user;
        this.projectName = project.getProjectName();
        this.projectDescription = project.getProjectDescription();
        this.topic1 = project.getTopic1();
        this.topic2 = project.getTopic2();
        this.topic3 = project.getTopic3();
        this.objectVersion++;
    }

    @PrePersist @PreUpdate
    public void generateCode() {
        if(projectName.isEmpty())
            this.projectCode = projectName.trim().replaceAll("[^a-zA-Z0-9\\s]", "")
                                .replaceAll("\\s+", "-").toLowerCase();
    }
}
