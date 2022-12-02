package com.esprit.kaddemback.services;

import com.esprit.kaddemback.entities.Partenaire;
import com.esprit.kaddemback.repositories.PartenaireRepository;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartenaireServiceImpl {


    @Autowired
    PartenaireRepository partenaireRepository;


    public Partenaire ajouterPartenaire(Partenaire p) {
      return  partenaireRepository.save(p);
       // log.info("Ajouter Offre");

    }

    public Iterable<Partenaire> retrieveAllPartenaire() {return partenaireRepository.findAll();}



    public void deletePartenaire(Long id) {
        partenaireRepository.deleteById(id);}


    public Partenaire updatePartenaire(Partenaire p) {return partenaireRepository.save(p);}


    public Partenaire retrievePartenaire(Long idPartenaire) {
        return partenaireRepository.findById(idPartenaire).get();
    }


  /* public void assignPartenaireToUniversite( Long idPartenaire , Long idUniver) {

        Partenaire partenaire = partenaireRepository.findById(idPartenaire).orElse(null);
        Universite universite = universiteRepository.findById(idUniver).orElse(null);
        partenaire.setUniversites(universite);
        partenaireRepository.save(partenaire);
    }
    */
}