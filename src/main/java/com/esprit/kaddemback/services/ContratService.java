package com.esprit.kaddemback.services;

import com.esprit.kaddemback.entities.Contrat;
import com.esprit.kaddemback.repositories.ContratRepository;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ContratService {
    @Autowired
    private ContratRepository contratRepository;

    //the get methods (retrieve)
    public List<Contrat> retrieveAllContrats() {
        return contratRepository.findAll();
    }

    public Contrat retrieveContratById(Long id) {
        return contratRepository.findById(id).orElse(null);
    }

    //the push methods
    public Contrat addContrat(Contrat c) {
        return contratRepository.save(c);
    }

    //the update method
    public Contrat updateContrat(Contrat contrat) {
        Contrat existingContrat = contratRepository.findById(contrat.getIdContrat()).orElse(null);
        existingContrat.setDateDebutContrat(contrat.getDateDebutContrat());
        existingContrat.setDateFinContrat(contrat.getDateFinContrat());
        existingContrat.setSpecialite(contrat.getSpecialite());
        existingContrat.setArchive(contrat.getArchive());
        existingContrat.setMontantContrat(contrat.getMontantContrat());
        return contratRepository.save(existingContrat);
    }

    //the delete method

    public String deleteContrat(Long id) {
        contratRepository.deleteById(id);
        return "Contrat removed !! " + id;
    }





}
