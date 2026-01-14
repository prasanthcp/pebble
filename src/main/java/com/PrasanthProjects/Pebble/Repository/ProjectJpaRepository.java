package com.PrasanthProjects.Pebble.Repository;

import com.PrasanthProjects.Pebble.Service.Pebble;
import com.PrasanthProjects.Pebble.Service.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface ProjectJpaRepository extends JpaRepository<Project,Integer> {
    Optional<Project> findByProjectId(int projectId);
    ArrayList<Project> findByUserUserId(int userId);
}
