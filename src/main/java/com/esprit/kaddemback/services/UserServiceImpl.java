package com.esprit.kaddemback.services;

import com.esprit.kaddemback.entities.Certification;
import com.esprit.kaddemback.entities.Gender;
import com.esprit.kaddemback.entities.Role;
import com.esprit.kaddemback.entities.User;
import com.esprit.kaddemback.repositories.RoleRepository;
import com.esprit.kaddemback.repositories.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.connector.Response;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
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

    @Autowired
    ServletContext context;


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
        adminUser.setFileName("me1.jpg");
        adminUser.setPrenom("Eya");
        adminUser.setNom("Bahri");
        adminUser.setEmail("eya@esprit.tn");
        adminUser.setPassword(getEncodedPassword("admin@pass"));
        adminUser.setUserName("EyaBahri1");
        adminUser.setGender(Gender.FEMALE);
        adminUser.setPhoneNumber(26821820);
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userRepository.save(adminUser);


    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public String registerNewUser(String user, MultipartFile file) throws JsonProcessingException {
        User us = new ObjectMapper().readValue(user, User.class);
        boolean isExit = new File(context.getRealPath("/Images/")).exists();
        if (!isExit)
        {
            new File (context.getRealPath("/Images/")).mkdir();
            System.out.println("mk dir.............");
        }
        String filename = file.getOriginalFilename();
            String newFileName = FilenameUtils.getBaseName(filename)+"."+ FilenameUtils.getExtension(filename);
        File serverFile = new File (context.getRealPath("/Images/"+File.separator+newFileName));
        try
        {
            System.out.println("Image");
            FileUtils.writeByteArrayToFile(serverFile,file.getBytes());

        }catch(Exception e) {
            e.printStackTrace();
        }

        us.setFileName(newFileName);
        //old
        Role role = roleRepository.findById("Etudiant").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        us.setRole(userRoles); //affecter le role Etudiant au user par défaut
        us.setPassword(getEncodedPassword(us.getPassword()));

        User usr = userRepository.save(us);

        return "ok";

    }

    public byte[] getPhoto(String userName) throws Exception{
        User user   = userRepository.findById(userName).get();
        return Files.readAllBytes(Paths.get(context.getRealPath("/Images/")+user.getFileName()));
    }

    public User updateUser(User user,String userName) {
        User existingUser = userRepository.findById(userName).orElse(null);
        existingUser.setUserName(user.getUserName());
        existingUser.setNom(user.getNom());
        existingUser.setPrenom(user.getPrenom());
        existingUser.setEmail(user.getEmail());
        existingUser.setGender(user.getGender());
        existingUser.setPhoneNumber(user.getPhoneNumber());

        return userRepository.save(existingUser);
    }
    public boolean ifEmailExist(String mail){
        return userRepository.existsByEmail(mail);
    }

    public User getUserByMail(String mail){
        return userRepository.findByEmail(mail);
    }



    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    public String deleteUser(String userName) {
        userRepository.deleteById(userName);
        return "Etudiant removed !! " + userName;
    }


    public User GetUserByUsername(String userName){
        return  userRepository.findById(userName).get();
    }

}
