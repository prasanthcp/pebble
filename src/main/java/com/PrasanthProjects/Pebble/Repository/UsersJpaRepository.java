package com.PrasanthProjects.Pebble.Repository;

import com.PrasanthProjects.Pebble.Service.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsersJpaRepository extends JpaRepository<Users,Integer> {
    public Optional<Users> findByUsername(String username);
}
