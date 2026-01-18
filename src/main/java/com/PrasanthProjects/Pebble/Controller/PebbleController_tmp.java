package com.prasanthprojects.pebble.controller;
import com.prasanthprojects.pebble.repository.pebblejparepository;
import com.prasanthprojects.pebble.repository.projectjparepository;
import com.prasanthprojects.pebble.repository.usersjparepository;
import com.prasanthprojects.pebble.service.pebble;
import com.prasanthprojects.pebble.service.project;
import com.prasanthprojects.pebble.service.users;
//import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

//@RequiredArgsConstructor
@RestController @RequestMapping("pebble")
public class pebblecontroller {

    private projectjparepository projectJpaRepository;
    private usersjparepository userJpaRepository;
    private pebblejparepository pebbleJpaRepository;
    private usercontroller userController;

    public pebblecontroller(projectjparepository projectJpaRepository, usersjparepository userJpaRepository, pebblejparepository pebbleJpaRepository, usercontroller userController) {
        this.projectJpaRepository = projectJpaRepository;
        this.userJpaRepository = userJpaRepository;
        this.pebbleJpaRepository = pebbleJpaRepository;
        this.userController = userController;
    }

    public pebblecontroller() {
    }

    @GetMapping("getAll/{project_id}")
    public ResponseEntity<ArrayList<pebble>> goTopebbleDashboard(@PathVariable int project_id) throws Exception {
        ArrayList<pebble> pebbles =
                pebbleJpaRepository.findByProjectProjectIdOrderByPebbleIdDesc(project_id);
        return ResponseEntity.ok(pebbles);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<pebble> goTopebble(@PathVariable int id) throws Exception {
        return pebbleJpaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("add/{project_id}")
    public ResponseEntity<pebble> addpebble(@RequestBody pebble pebble, @PathVariable int project_id) throws Exception {
        pebble.setUser(userController.getAuthenticatedUser());
        pebble.setProject(projectJpaRepository.findById(project_id).get());
        pebble.setObjectVersion(1);
        long pebblesCount = pebbleJpaRepository.countByProjectProjectId(project_id);

        if(pebblesCount == 0) {
            project project = projectJpaRepository.findByProjectId(project_id)
                                .orElseThrow(()-> new RuntimeException("project not found !"));
            project.setProjectStatus(1);
            project.setProjectStartDate(LocalDateTime.now());
            projectJpaRepository.save(project);
        }

        pebble.setPebbleState("STARTED"); pebble.setObjectVersion(1);
        pebble.setCreationDate(LocalDateTime.now());
        return ResponseEntity.ok(pebbleJpaRepository.save(pebble));
    }

    @PostMapping("update/{pebble_id}")
    public ResponseEntity<pebble> updatepebble(@RequestBody pebble pebble, @PathVariable int pebble_id) throws Exception {

        users user = userController.getAuthenticatedUser();
        return pebbleJpaRepository
                .findById(pebble_id)
                .map(existing -> {
                    existing.setPebble(pebble, user);
                    return ResponseEntity.ok(pebbleJpaRepository.save(existing));
                }).orElse(ResponseEntity.notFound().build());
    }

}
