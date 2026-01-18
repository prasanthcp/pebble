package com.prasanthprojects.pebble.repository;

import com.prasanthprojects.pebble.service.project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface projectjparepository extends JpaRepository<project,Integer> {
    Optional<project> findByProjectId(int projectId);
    ArrayList<project> findByUserUserId(int userId);
}
