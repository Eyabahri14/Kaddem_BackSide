package com.esprit.kaddemback.controllers;

import com.esprit.kaddemback.entities.User;
import com.esprit.kaddemback.repositories.UserRepository;
import com.esprit.kaddemback.services.PdfService;
import com.esprit.kaddemback.services.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserServiceImpl userService;

@Autowired
    UserRepository userRepository;
    @Autowired
    private  PdfService pdfService;


    @PostConstruct //lors de l'execution
    public void initRoleAndUser() {
        userService.initRolesAndUser();
    }




    @GetMapping(path="/ImgUsers/{userName}")
    public byte[] getPhoto(@PathVariable("userName") String userName) throws Exception{
        return userService.getPhoto(userName);
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

    @GetMapping("/pdf")
    public void downloadPdf(HttpServletResponse response){
        try{
            Path file = Paths.get(pdfService.generateUsersPdf().getAbsolutePath());
            if (Files.exists(file)){
                response.setContentType("application/pdf");
                response.addHeader("Content-Disposition", "attachment; filename"+ file.getFileName());
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @PostMapping({"/registerNewUser"})
    public String registerNewUser(@RequestParam("user") String user,@RequestParam("file") MultipartFile file) throws JsonProcessingException {
        return  userService.registerNewUser(user,file);
    }

    @PutMapping("/updateUser/{userName}")
    public User updateUser(@RequestBody User user,@PathVariable("userName") String userName) {
        return userService.updateUser(user,userName);
    }

    @GetMapping(path="/getUser/{userName}")
    public User getUserByUsername(@PathVariable("userName") String userName) throws Exception{
        return userService.GetUserByUsername(userName);
    }


}
