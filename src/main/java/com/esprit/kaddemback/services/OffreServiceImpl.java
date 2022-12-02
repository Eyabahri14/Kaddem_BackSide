package com.esprit.kaddemback.services;

import com.esprit.kaddemback.entities.Certification;
import com.esprit.kaddemback.entities.Offre;
import com.esprit.kaddemback.entities.Partenaire;
import com.esprit.kaddemback.repositories.OffreRepository;
import com.esprit.kaddemback.repositories.PartenaireRepository;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OffreServiceImpl {
@Autowired
    OffreRepository offreRepository;
@Autowired
    PartenaireRepository partenaireRepository;

    
    public Long ajouterOffre(Offre o) {
        offreRepository.save(o);
        //log.info("Ajouter Offre");
        return o.getIdOffre();
    }
    
    public Iterable<Offre> retrieveAllOffre() {return offreRepository.findAll();}


    
    public void deleteOffre(Long id) {
        offreRepository.deleteById(id);}


    public Offre updateOffre(Offre offre) {
        Offre existingOffre = offreRepository.findById(offre.getIdOffre()).orElse(null);
        existingOffre.setNomOffre(offre.getNomOffre());
        existingOffre.setDescriptionOffre(offre.getDescriptionOffre());
        existingOffre.setTypeOffre(offre.getTypeOffre());

        return offreRepository.save(existingOffre);
    }

    
    public Offre retrieveOffre(Long idOffre) {
        return offreRepository.findById(idOffre).get();
    }


    
    public void assignOffreToPartenaire( Long idOffre , Long idPar) {

        Offre offre = offreRepository.findById(idOffre).orElse(null);
        Partenaire partenaire = partenaireRepository.findById(idPar).orElse(null);
        offre.setPartenaire(partenaire);
        offreRepository.save(offre);
    }

}
