package com.PrasanthProjects.Pebble.Service;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Pebble {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;
    private String notes, state;
    private double topic1Studied, topic2Studied, topic3Studied, topic4Studied, topic5Studied;
    private LocalDate creationDate, lastUpdateDate;
    private int objectVersion;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    public Pebble() {
    }

    public double getTopic2Studied() {
        return topic2Studied;
    }

    public void setTopic2Studied(double topic2Studied) {
        this.topic2Studied = topic2Studied;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public double getTopic1Studied() {
        return topic1Studied;
    }

    public void setTopic1Studied(double topic1Studied) {
        this.topic1Studied = topic1Studied;
    }

    public double getTopic3Studied() {
        return topic3Studied;
    }

    public void setTopic3Studied(double topic3Studied) {
        this.topic3Studied = topic3Studied;
    }

    public double getTopic4Studied() {
        return topic4Studied;
    }

    public void setTopic4Studied(double topic4Studied) {
        this.topic4Studied = topic4Studied;
    }

    public double getTopic5Studied() {
        return topic5Studied;
    }

    public void setTopic5Studied(double topic5Studied) {
        this.topic5Studied = topic5Studied;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDate lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public int getObjectVersion() {
        return objectVersion;
    }

    public void setObjectVersion(int objectVersion) {
        this.objectVersion = objectVersion;
    }
}
