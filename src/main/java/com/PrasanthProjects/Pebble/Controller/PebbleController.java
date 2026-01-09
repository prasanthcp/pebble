package com.PrasanthProjects.Pebble.Controller;

import com.PrasanthProjects.Pebble.Repository.PebbleJpaRepository;
import com.PrasanthProjects.Pebble.Repository.ProjectJpaRepository;
import com.PrasanthProjects.Pebble.Repository.UsersJpaRepository;
import com.PrasanthProjects.Pebble.Service.Pebble;
import com.PrasanthProjects.Pebble.Service.Project;
import com.PrasanthProjects.Pebble.Service.Users;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("pebble")
public class PebbleController {

    private ProjectJpaRepository projectJPARepository;
    private UsersJpaRepository userJPARepository;
    private PebbleJpaRepository pebbleJPARepository;

    public PebbleController(ProjectJpaRepository projectJPARepository, UsersJpaRepository userJPARepository, PebbleJpaRepository pebbleJPARepository) {
        this.projectJPARepository = projectJPARepository;
        this.userJPARepository = userJPARepository;
        this.pebbleJPARepository = pebbleJPARepository;
    }

    @GetMapping("getAll/{project_id}")
    public ResponseEntity<ArrayList<Pebble>> goToPebbleDashboard(@PathVariable int project_id) throws Exception {
        ArrayList<Pebble> pebbles = pebbleJPARepository.findByProjectId(project_id);
        return new ResponseEntity(pebbles, HttpStatus.OK);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Pebble> goToPebble(@PathVariable int id) throws Exception {
        Optional<Pebble> pebble = pebbleJPARepository.findById(id);
        return new ResponseEntity(pebble.get(), HttpStatus.OK);
    }

    @PostMapping("add/{project_id}")
    public ResponseEntity<Pebble> addPebble(@RequestBody Pebble pebble, @PathVariable int project_id) throws Exception {
        pebble.setUser(getAuthenticatedUser());
        pebble.setProject(projectJPARepository.findById(project_id).get());
        pebble.setState("STARTED"); pebble.setObjectVersion(1);
        pebble.setCreationDate(LocalDate.now()); pebble.setLastUpdateDate(LocalDate.now());
        pebbleJPARepository.save(pebble);
        return new ResponseEntity(pebble, HttpStatus.OK);
    }

    @PostMapping("update/{pebble_id}")
    public ResponseEntity<Pebble> updatePebble(@RequestBody Pebble pebble, @PathVariable int pebble_id) throws Exception {

        Optional<Pebble> existingPebble = pebbleJPARepository.findById(pebble_id);
        if(existingPebble.isEmpty()) return ResponseEntity.notFound().build();

        Pebble existing = existingPebble.get();
        existing.setPebble(pebble, getAuthenticatedUser());
        pebbleJPARepository.save(existing);
        return new ResponseEntity(existing, HttpStatus.OK);
    }

    private Users getAuthenticatedUser() throws Exception {
        Users user = userJPARepository.findByUsername("Prasanth");
        if(user==null) throw new UserPrincipalNotFoundException("Prasanth");
        return (Users)user;
    }

}
