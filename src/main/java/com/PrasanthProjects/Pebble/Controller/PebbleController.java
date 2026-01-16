package com.PrasanthProjects.Pebble.Controller;

import com.PrasanthProjects.Pebble.Repository.PebbleJpaRepository;
import com.PrasanthProjects.Pebble.Repository.ProjectJpaRepository;
import com.PrasanthProjects.Pebble.Repository.UsersJpaRepository;
import com.PrasanthProjects.Pebble.Service.Pebble;
import com.PrasanthProjects.Pebble.Service.Project;
import com.PrasanthProjects.Pebble.Service.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@RequiredArgsConstructor
@RestController @RequestMapping("pebble")
public class PebbleController {

    private final ProjectJpaRepository projectJPARepository;
    private final UsersJpaRepository userJPARepository;
    private final PebbleJpaRepository pebbleJPARepository;
    private final UserController userController;

    @GetMapping("getAll/{project_id}")
    public ResponseEntity<ArrayList<Pebble>> goToPebbleDashboard(@PathVariable int project_id) throws Exception {
        ArrayList<Pebble> pebbles =
                pebbleJPARepository.findByProjectProjectIdOrderByPebbleIdDesc(project_id);
        return ResponseEntity.ok(pebbles);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Pebble> goToPebble(@PathVariable int id) throws Exception {
        return pebbleJPARepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("add/{project_id}")
    public ResponseEntity<Pebble> addPebble(@RequestBody Pebble pebble, @PathVariable int project_id) throws Exception {
        pebble.setUser(userController.getAuthenticatedUser());
        pebble.setProject(projectJPARepository.findById(project_id).get());
        pebble.setObjectVersion(1);
        long pebblesCount = pebbleJPARepository.countByProjectProjectId(project_id);

        if(pebblesCount == 0) {
            Project project = projectJPARepository.findByProjectId(project_id)
                                .orElseThrow(()-> new RuntimeException("Project not found !"));
            project.setProjectStatus(1);
            project.setProjectStartDate(LocalDateTime.now());
            projectJPARepository.save(project);
        }

        pebble.setPebbleState("STARTED"); pebble.setObjectVersion(1);
        pebble.setCreationDate(LocalDateTime.now());
        return ResponseEntity.ok(pebbleJPARepository.save(pebble));
    }

    @PostMapping("update/{pebble_id}")
    public ResponseEntity<Pebble> updatePebble(@RequestBody Pebble pebble, @PathVariable int pebble_id) throws Exception {

        Users user = userController.getAuthenticatedUser();
        return pebbleJPARepository
                .findById(pebble_id)
                .map(existing -> {
                    existing.setPebble(pebble, user);
                    return ResponseEntity.ok(pebbleJPARepository.save(existing));
                }).orElse(ResponseEntity.notFound().build());
    }

}
