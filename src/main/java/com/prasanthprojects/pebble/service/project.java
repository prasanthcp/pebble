package com.prasanthprojects.pebble.service;
import jakarta.persistence.*;
//import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity //@Data @Builder // Use the builder for creation, keep setters for updates.
//@NoArgsConstructor @AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class project {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer projectId;

    private String projectCode;
    private String projectName;

    @ManyToOne @JoinColumn(name = "user_id")
    private users user;

    private String projectDescription;
    private String topic1;
    private String topic2;
    private String topic3;

    private Integer projectStatus = 1; // default inactive

    private Integer frequency = 6; // default frequency

    private LocalDateTime projectStartDate = LocalDateTime.now(); // default to today

    @CreatedDate
    private LocalDateTime creationDate;

    @LastModifiedDate
    private LocalDateTime lastUpdateDate;

    private Integer objectVersion = 0;

    /** Custom update method to copy fields from another project and associate with a given user. */
    public void setProject(project project, users user) {
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

    public project(Integer projectId, String projectCode, String projectName, users user, String projectDescription, String topic1, String topic2, String topic3, Integer projectStatus, Integer frequency, LocalDateTime projectStartDate, LocalDateTime creationDate, LocalDateTime lastUpdateDate, Integer objectVersion) {
        this.projectId = projectId;
        this.projectCode = projectCode;
        this.projectName = projectName;
        this.user = user;
        this.projectDescription = projectDescription;
        this.topic1 = topic1;
        this.topic2 = topic2;
        this.topic3 = topic3;
        this.projectStatus = projectStatus;
        this.frequency = frequency;
        this.projectStartDate = projectStartDate;
        this.creationDate = creationDate;
        this.lastUpdateDate = lastUpdateDate;
        this.objectVersion = objectVersion;
    }

    public project() {
    }

    public users getUser() {
        return user;
    }

    public void setUser(users user) {
        this.user = user;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getTopic1() {
        return topic1;
    }

    public void setTopic1(String topic1) {
        this.topic1 = topic1;
    }

    public String getTopic2() {
        return topic2;
    }

    public void setTopic2(String topic2) {
        this.topic2 = topic2;
    }

    public String getTopic3() {
        return topic3;
    }

    public void setTopic3(String topic3) {
        this.topic3 = topic3;
    }

    public Integer getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(Integer projectStatus) {
        this.projectStatus = projectStatus;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public LocalDateTime getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(LocalDateTime projectStartDate) {
        this.projectStartDate = projectStartDate;
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
}
