package com.PrasanthProjects.Pebble.Repository;

import com.PrasanthProjects.Pebble.Service.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersJpaRepository extends JpaRepository<Users,Integer> {
    public Users findByUsername(String username);
}
