package com.PrasanthProjects.Pebble.Controller;
import com.PrasanthProjects.Pebble.Repository.PebbleJpaRepository;
import com.PrasanthProjects.Pebble.Repository.ProjectJpaRepository;
import com.PrasanthProjects.Pebble.Repository.UsersJpaRepository;
import com.PrasanthProjects.Pebble.Service.Project;
import com.PrasanthProjects.Pebble.Service.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectJpaRepository projectJPARepository;
    private final UsersJpaRepository userJPARepository;
    private final PebbleJpaRepository pebbleJPARepository;

    @GetMapping("/get/{project_id}")
    public ResponseEntity<Project> getProject(@PathVariable int project_id) {
        return projectJPARepository.findById(project_id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/getAll/{userId}")
    public ResponseEntity<List<Project>> goToProjectDashboard(@PathVariable int userId) {
        List<Project> projects = projectJPARepository.findByUserUserId(userId);
        return projects.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(projects);
    }

    private Users getAuthenticatedUser() throws Exception {
        Optional<Users> user = userJPARepository.findByUsername("Prasanth");
        if(user.isEmpty()) throw new UserPrincipalNotFoundException("Prasanth");
        return user.get();
    }

    @PostMapping("/add")
    public ResponseEntity<Project> addProject(@RequestBody Project project) throws Exception {
        project.setUser(getAuthenticatedUser());
        projectJPARepository.save(project);
        return new ResponseEntity<>(project,HttpStatus.CREATED);
    }

    @PutMapping("/update/{project_id}")
    public ResponseEntity<Project> updateProject(@PathVariable int project_id,
                                                 @RequestBody Project project) throws Exception {
        Users user = getAuthenticatedUser();
        return projectJPARepository.findById(project_id)
                .map(existing -> {
                    existing.setProject(project, user);
                    return ResponseEntity.ok(projectJPARepository.save(existing));
                }).orElse(ResponseEntity.notFound().build());
    }
}