package com.PrasanthProjects.Pebble.Controller;

import com.PrasanthProjects.Pebble.Repository.PebbleJpaRepository;
import com.PrasanthProjects.Pebble.Repository.ProjectJpaRepository;
import com.PrasanthProjects.Pebble.Repository.UsersJpaRepository;
import com.PrasanthProjects.Pebble.Service.Project;
import com.PrasanthProjects.Pebble.Service.Users;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("project")
public class ProjectController {

    private ProjectJpaRepository projectJPARepository;
    private UsersJpaRepository userJPARepository;
    private PebbleJpaRepository pebbleJPARepository;

    public ProjectController(ProjectJpaRepository projectJPARepository, UsersJpaRepository userJPARepository, PebbleJpaRepository pebbleJPARepository) {
        this.projectJPARepository = projectJPARepository;
        this.userJPARepository = userJPARepository;
        this.pebbleJPARepository = pebbleJPARepository;
    }

    @GetMapping("/get/{project_id}")
    public ResponseEntity<Project> getProject(@PathVariable int project_id) {
        Optional project = projectJPARepository.findById(project_id);
        return project.isEmpty() ? new ResponseEntity(HttpStatus.NOT_FOUND) : ResponseEntity.ok( (Project)project.get() );
    }

    @GetMapping("/getAll/{userId}")
    public ResponseEntity<ArrayList<Project>> goToProjectDashboard(@PathVariable int userId) {
        ArrayList<Project> projects = projectJPARepository.findByUserUserId(userId);
        return projects.isEmpty() ? new ResponseEntity(HttpStatus.NOT_FOUND) :  ResponseEntity.ok(projects);
    }

    private Users getAuthenticatedUser() throws Exception {
        Users user = userJPARepository.findByUsername("Prasanth");
        if(user==null) throw new UserPrincipalNotFoundException("Prasanth");
        return user;
    }

    @PostMapping("/add")
    public ResponseEntity<Project> addProject(@RequestBody Project project) throws Exception {
        project.setUser(getAuthenticatedUser());
        projectJPARepository.save(project);
        return new ResponseEntity<>(project,HttpStatus.CREATED);
    }

    @PostMapping("/update/{project_id}")
    public ResponseEntity<Project> updateProject(@PathVariable int project_id,
                                                 @RequestBody Project project) throws Exception {
        Optional<Project> existingProject = projectJPARepository.findById(project_id);
        if(existingProject.isEmpty()) return ResponseEntity.notFound().build();

        Project existing = existingProject.get();
        existing.setProject(project, getAuthenticatedUser());
        projectJPARepository.save(existing);
        return ResponseEntity.ok(existing);
    }
}


//@GetMapping("/")
//    @ResponseBody
//    public String getHelloWorld() {
//        return "Hello Prasanth, Welcome to Your Pebble!";
//    }