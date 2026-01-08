package com.PrasanthProjects.Pebble.Controller;

import com.PrasanthProjects.Pebble.Repository.PebbleJpaRepository;
import com.PrasanthProjects.Pebble.Repository.ProjectJpaRepository;
import com.PrasanthProjects.Pebble.Repository.UsersJpaRepository;
import com.PrasanthProjects.Pebble.Service.Pebble;
import com.PrasanthProjects.Pebble.Service.Project;
import com.PrasanthProjects.Pebble.Service.Users;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.Optional;

@RequestMapping("pebbles")
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
    public ResponseEntity<ArrayList<Project>> goToPebbleDashboard(ModelMap modelMap, @PathVariable int project_id) throws Exception {
        ArrayList<Pebble> pebbles = pebbleJPARepository.findByProjectId(project_id);
        return new ResponseEntity(pebbles, HttpStatus.OK);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<ArrayList<Project>> goToPebble(@PathVariable int id) throws Exception {
        Optional<Pebble> pebble = pebbleJPARepository.findById(id);
        return new ResponseEntity(pebble.get(), HttpStatus.OK);
    }

    @PostMapping("add")
    public ResponseEntity<ArrayList<Project>> addPebble(ModelMap modelMap, @RequestBody Pebble pebble) throws Exception {
        pebble.setUser(getAuthenticatedUser());
        pebbleJPARepository.save(pebble);
        return new ResponseEntity(pebble, HttpStatus.OK);
    }

    private Users getAuthenticatedUser() throws Exception {
        Users user = userJPARepository.findByUsername("Prasanth");
        if(user==null) throw new UserPrincipalNotFoundException("Prasanth");
        return (Users)user;
    }

}
