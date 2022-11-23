package com.esprit.kaddemback.services;

import com.esprit.kaddemback.entities.Role;
import com.esprit.kaddemback.entities.User;
import com.esprit.kaddemback.repositories.RoleRepository;
import com.esprit.kaddemback.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public void initRolesAndUser(){
        Role adminRole=new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleRepository.save(adminRole);

        Role etudiantRole=new Role();
        etudiantRole.setRoleName("Etudiant");
        etudiantRole.setRoleDescription("Default role for newly created user ");
        roleRepository.save(etudiantRole);

        //Ajout de l'admin dans la base
        User adminUser = new User();
        adminUser.setNom("admin");
        adminUser.setPrenom("admin");
        adminUser.setEmail("eya@esprit.tn");
        adminUser.setPassword(getEncodedPassword("admin@pass"));
        adminUser.setUserName("admin123");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userRepository.save(adminUser);


    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public User registerNewUser(User user){

        Role role = roleRepository.findById("Etudiant").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles); //affecter le role Etudiant au user par défaut
        user.setPassword(getEncodedPassword(user.getPassword()));

        return userRepository.save(user);
    }


    public boolean ifEmailExist(String mail){
        return userRepository.existsByEmail(mail);
    }

    public User getUserByMail(String mail){
        return userRepository.findByEmail(mail);
    }

   /* public User createUserWithGoogle(User user) {
        Role role = roleRepository.findById("Etudiant").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles); //affecter le role Etudiant au user par défaut
        user.setUserName("GoogleUser");
        user.setPassword(passwordEncoder.encode("GoogleAuht"));

        return userRepository.save(user);
    }
    */


    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    public String deleteUser(String userName) {
        userRepository.deleteById(userName);
        return "Etudiant removed !! " + userName;
    }

}
