package com.esprit.kaddemback.services;

import com.esprit.kaddemback.entities.DetailEquipe;
import com.esprit.kaddemback.repositories.DetailEquipeRepository;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DetailEquipeServiceImpl {
    @Autowired
    private DetailEquipeRepository detailEquipeRepository ;

    public List<DetailEquipe> retrieveAllDetailEquipes() {
        return detailEquipeRepository.findAll();
    }

    public DetailEquipe retrieveDetailEquipeById(Long id) {
        return detailEquipeRepository.findByIdDetailEquipe(id);
    }

    //the push methods
    public DetailEquipe addDetailEquipe(DetailEquipe c) {
        return detailEquipeRepository.save(c);
    }

    public DetailEquipe updateDetailEquipe (DetailEquipe detailEquipe) {
        DetailEquipe existingDetailEquipe = detailEquipeRepository.findByIdDetailEquipe(detailEquipe.getIdDetailEquipe());
        existingDetailEquipe.setSalle(detailEquipe.getSalle());
        existingDetailEquipe.setThematique(detailEquipe.getThematique());
        existingDetailEquipe.setEquipe(detailEquipe.getEquipe());
        return detailEquipeRepository.save(existingDetailEquipe);
    }

    public String deleteDetailEquipe(Long id) {
        detailEquipeRepository.deleteById(id.toString());
        return "Contrat removed !! " + id;
    }

}
