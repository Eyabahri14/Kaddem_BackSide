package com.esprit.kaddemback.controllers;

import com.esprit.kaddemback.entities.Partenaire;
import com.esprit.kaddemback.services.PartenaireServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/PartenaireC")
public class PartenaireController {

    @Autowired
    PartenaireServiceImpl ipartenaireService;

    @GetMapping("/")
    public Iterable<Partenaire>  GetAllPartenaires(){
        return ipartenaireService.retrieveAllPartenaire();
    }

    @GetMapping("/retrieve-partenaire/{Partenaire-id}")
    public Partenaire retrievePartenaire(@PathVariable("Partenaire-id") Long PartenaireId) {
        return ipartenaireService.retrievePartenaire(PartenaireId);
    }

    @PostMapping("/addPartenaire")
    @ResponseBody
    public Partenaire addPartenaire(@RequestBody Partenaire Par) {
        return ipartenaireService.ajouterPartenaire(Par);
    }

    @PutMapping("/updatePartenaire")
    @ResponseBody
    public void updatePartenaire(@RequestBody Partenaire Par) {
        ipartenaireService.updatePartenaire(Par);
    }

    @DeleteMapping("/deletePartenaire/{partenaire-id}")
    @ResponseBody
    public void deletePartenairet(@PathVariable("partenaire-id") Long PartenairetId ) {
        ipartenaireService.deletePartenaire(PartenairetId);
    }

    /*
    @PutMapping(value = "/affectationPartenaire_univer/{Par-id}/{uni-id}")
    public void affectationPartenaireUniversite(@PathVariable("Par-id}") Long partId ,@PathVariable("uni-id}") Long UniId )
    {
        ipartenaireService.assignPartenaireToUniversite(partId,UniId);
    } */

}
