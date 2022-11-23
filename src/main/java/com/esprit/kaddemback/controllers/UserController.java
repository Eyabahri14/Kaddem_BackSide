package com.esprit.kaddemback.controllers;

import com.esprit.kaddemback.entities.JwtRequest;
import com.esprit.kaddemback.entities.JwtResponse;
import com.esprit.kaddemback.entities.Role;
import com.esprit.kaddemback.entities.User;
import com.esprit.kaddemback.repositories.UserRepository;
import com.esprit.kaddemback.services.UserServiceImpl;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserServiceImpl userService;

@Autowired
    UserRepository userRepository;


    @PostConstruct //lors de l'execution
    public void initRoleAndUser() {
        userService.initRolesAndUser();
    }


    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody User user){

        return  userService.registerNewUser(user);
    }


    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/forEtudiant"})
    @PreAuthorize("hasRole('Etudiant')")

    public String forUser(){
        return "This URL is only accessible to the Etudiant";
    }




    @GetMapping("/users") //didn't use it
    public List<User> retrieveAllUsers() {
        return userService.retrieveAllUsers();
    }

    @DeleteMapping("/delete/{userName}")
    public String deleteUser(@PathVariable String userName) {
        return userService.deleteUser(userName);
    }

    @GetMapping("/list")//affichage+pagination
    public Page<User> showPage(@RequestParam(defaultValue = "0") int page){
        return userRepository.findAll(PageRequest.of(page, 4));
    }


}
