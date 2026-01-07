package com.PrasanthProjects.Pebble.Repository;

import com.PrasanthProjects.Pebble.Service.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ProjectJpaRepository extends JpaRepository<Project,Integer> {
    ArrayList<Project> findByUserUserId(int userId);
}
