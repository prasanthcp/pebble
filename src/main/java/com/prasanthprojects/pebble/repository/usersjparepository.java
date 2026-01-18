package com.prasanthprojects.pebble.repository;

import com.prasanthprojects.pebble.service.users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface usersjparepository extends JpaRepository<users,Integer> {
    public Optional<users> findByUsername(String username);
}
