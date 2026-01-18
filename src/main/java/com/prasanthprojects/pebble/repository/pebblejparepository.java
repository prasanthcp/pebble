package com.prasanthprojects.pebble.repository;
import com.prasanthprojects.pebble.service.pebble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface pebblejparepository extends JpaRepository<pebble,Integer> {
    ArrayList<pebble> findByProjectProjectId(int projectId);
    long countByProjectProjectId(int projectId);
    ArrayList<pebble> findByProjectProjectIdOrderByPebbleIdDesc(int projectId);
}
