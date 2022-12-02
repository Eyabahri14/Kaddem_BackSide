package com.esprit.kaddemback.controllers;


import com.esprit.kaddemback.entities.Certification;
import com.esprit.kaddemback.entities.User;
import com.esprit.kaddemback.repositories.CertificationRepository;
import com.esprit.kaddemback.services.CertificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import groovy.transform.AutoImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class CertificationController {
    @Autowired
    CertificationService certificationService;

    @Autowired
    CertificationRepository certificationRepository;


    @GetMapping("/certifs")//affichage+pagination
    public Page<Certification> showPage(@RequestParam(defaultValue = "0") int page){
        return certificationRepository.findAll(PageRequest.of(page, 4));
    }

    @PostMapping("/addCertif")
    public Certification addCertif(@RequestBody Certification c)  {
        return certificationService.addCertification(c);
    }

    //the remove method
    @DeleteMapping("/deleteCertif/{id}")
    public String deleteCertif(@PathVariable long id) {
        return certificationService.deleteCertification(id);
    }

    //the update method
    @PutMapping("/updateCertif")
    public Certification updateCertification(@RequestBody Certification certification) {
        return certificationService.updateCertification(certification);
    }

}
