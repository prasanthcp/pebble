package com.prasanthprojects.pebble.controller;
import com.prasanthprojects.pebble.repository.pebblejparepository;
import com.prasanthprojects.pebble.repository.projectjparepository;
import com.prasanthprojects.pebble.repository.usersjparepository;
import com.prasanthprojects.pebble.repository.usersjparepository;
import com.prasanthprojects.pebble.service.project;
import com.prasanthprojects.pebble.service.users;
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
public class projectcontroller {

    private final projectjparepository projectJpaRepository;
    private final usersjparepository userJpaRepository;
    private final pebblejparepository pebbleJpaRepository;

    @GetMapping("/get/{project_id}")
    public ResponseEntity<project> getProject(@PathVariable int project_id) {
        return projectJpaRepository.findById(project_id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/getAll/{userId}")
    public ResponseEntity<List<project>> goToProjectDashboard(@PathVariable int userId) {
        List<project> projects = projectJpaRepository.findByUserUserId(userId);
        return projects.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(projects);
    }

    private users getAuthenticatedUser() throws Exception {
        Optional<users> user = userJpaRepository.findByUsername("Prasanth");
        if(user.isEmpty()) throw new UserPrincipalNotFoundException("Prasanth");
        return user.get();
    }

    @PostMapping("/add")
    public ResponseEntity<project> addProject(@RequestBody project project) throws Exception {
        project.setUser(getAuthenticatedUser());
        projectJpaRepository.save(project);
        return new ResponseEntity<>(project,HttpStatus.CREATED);
    }

    @PutMapping("/update/{project_id}")
    public ResponseEntity<project> updateProject(@PathVariable int project_id,
                                                 @RequestBody project project) throws Exception {
        users user = getAuthenticatedUser();
        return projectJpaRepository.findById(project_id)
                .map(existing -> {
                    existing.setProject(project, user);
                    return ResponseEntity.ok(projectJpaRepository.save(existing));
                }).orElse(ResponseEntity.notFound().build());
    }
}