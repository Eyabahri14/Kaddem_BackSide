package com.esprit.kaddemback.services;

import com.esprit.kaddemback.entities.Certification;
import com.esprit.kaddemback.entities.User;
import com.esprit.kaddemback.repositories.CertificationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class CertificationService {

    @Autowired
    CertificationRepository certificationRepository;

    @Autowired
    ServletContext context;


    public List<Certification> GetAllCertifications() {
        return certificationRepository.findAll();
    }

    public Certification addCertification(Certification c)  {

        return certificationRepository.save(c);
    }

    public String deleteCertification(Long idCertif) {
        certificationRepository.deleteById(idCertif);
        return "Certif removed !! " + idCertif;
    }


    //the update method
    public Certification updateCertification(Certification certification) {
        Certification existingCertification = certificationRepository.findById(certification.getIdCertif()).orElse(null);
        existingCertification.setNomCertif(certification.getNomCertif());
        existingCertification.setOrganismeDeDelivrance(certification.getOrganismeDeDelivrance());
        existingCertification.setDateEmission(certification.getDateEmission());
        existingCertification.setDateExpiration(certification.getDateExpiration());
        existingCertification.setIdDiplome(certification.getIdDiplome());
        existingCertification.setUrlDiplome(certification.getUrlDiplome());

        return certificationRepository.save(existingCertification);
    }






}
