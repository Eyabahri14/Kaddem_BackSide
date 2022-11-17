package com.esprit.kaddemback.controllers;

import com.esprit.kaddemback.entities.User;
import com.esprit.kaddemback.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class UserController {

    @Autowired
    UserServiceImpl userService;

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
}
