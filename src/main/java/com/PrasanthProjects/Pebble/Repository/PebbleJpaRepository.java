package com.PrasanthProjects.Pebble.Repository;
import com.PrasanthProjects.Pebble.Service.Pebble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PebbleJpaRepository extends JpaRepository<Pebble,Integer> {

}
