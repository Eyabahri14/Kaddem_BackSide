package com.esprit.kaddemback.services;

import com.esprit.kaddemback.entities.Equipe;
import com.esprit.kaddemback.repositories.EquipeRepository;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class EquipeServiceImpl {
    @Autowired
    private EquipeRepository equipeRepository;
    //the get methods (retrieve)
    public List<Equipe> retrieveAllEquipes() {
        return equipeRepository.findAll();
    }

    public Equipe retrieveEquipeById(Long id) {
        return equipeRepository.findByIdEquipe(id);
    }

    //the push methods
    public Equipe addEquipe(Equipe c) {
        return equipeRepository.save(c);
    }

    public Equipe updateEquipe (Equipe equipe) {
        Equipe existingEquipe = equipeRepository.findByIdEquipe(equipe.getIdEquipe());
        existingEquipe.setNomEquipe(equipe.getNomEquipe());
        existingEquipe.setNiveau(equipe.getNiveau());
        existingEquipe.setDetailEquipe(equipe.getDetailEquipe());
        return equipeRepository.save(existingEquipe);
    }

    public String deleteEquipe(Long id) {
        equipeRepository.deleteById(id.toString());
        return "Contrat removed !! " + id;
    }
}
