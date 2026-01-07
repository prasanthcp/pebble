package com.PrasanthProjects.Pebble.Controller;

import com.PrasanthProjects.Pebble.Repository.PebbleJpaRepository;
import com.PrasanthProjects.Pebble.Repository.ProjectJpaRepository;
import com.PrasanthProjects.Pebble.Repository.UsersJpaRepository;
import com.PrasanthProjects.Pebble.Service.Project;
import com.PrasanthProjects.Pebble.Service.Users;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("project")
public class projectController {

    private ProjectJpaRepository projectJPARepository;
    private UsersJpaRepository userJPARepository;
    private PebbleJpaRepository pebbleJPARepository;

    public projectController(ProjectJpaRepository projectJPARepository, UsersJpaRepository userJPARepository, PebbleJpaRepository pebbleJPARepository) {
        this.projectJPARepository = projectJPARepository;
        this.userJPARepository = userJPARepository;
        this.pebbleJPARepository = pebbleJPARepository;
    }

    @GetMapping("/getAll")
    public ResponseEntity<ArrayList<Project>> goToProjectDashboard(ModelMap modelMap) throws Exception {
        modelMap.put("username",getAuthenticatedUser().getUsername());
        String username = (String)modelMap.getAttribute("username");
        ArrayList<Project> projects = projectJPARepository.findByUserUserId(getAuthenticatedUser().getUserId());
        return new ResponseEntity(projects, HttpStatus.OK);
    }

    @GetMapping("/get/{project_id}")
    public ResponseEntity<Project> getProject(@PathVariable int project_id) {
        Optional project = projectJPARepository.findById(project_id);

        if(project.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        return new ResponseEntity(project.get(), HttpStatus.OK);
    }

    private Users getAuthenticatedUser() throws Exception {
        Users user = userJPARepository.findByUsername("Prasanth");
        if(user==null) throw new UserPrincipalNotFoundException("Prasanth");
        return (Users)user;
    }

    @PostMapping("/add")
    public ResponseEntity<Project> goToProjectDashboard(@RequestBody Project project) throws Exception {
        project.setUser(getAuthenticatedUser());
        projectJPARepository.save(project);
        return new ResponseEntity(project,HttpStatus.CREATED);
    }

}


//@GetMapping("/")
//    @ResponseBody
//    public String getHelloWorld() {
//        return "Hello Prasanth, Welcome to Your Pebble!";
//    }