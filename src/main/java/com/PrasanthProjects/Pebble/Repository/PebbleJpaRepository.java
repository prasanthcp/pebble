package com.PrasanthProjects.Pebble.Repository;
import com.PrasanthProjects.Pebble.Service.Pebble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface PebbleJpaRepository extends JpaRepository<Pebble,Integer> {
    ArrayList<Pebble> findByProjectProjectId(int projectId);
    long countByProjectProjectId(int projectId);
    ArrayList<Pebble> findByProjectProjectIdOrderByPebbleIdDesc(int projectId);
}
