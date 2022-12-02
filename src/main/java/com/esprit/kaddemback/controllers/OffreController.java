package com.esprit.kaddemback.controllers;

import com.esprit.kaddemback.entities.Certification;
import com.esprit.kaddemback.entities.Offre;
import com.esprit.kaddemback.services.OffreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/OffreC")
public class OffreController {

    @Autowired
    OffreServiceImpl ioffreService;

    @GetMapping("/")
    public Iterable<Offre>  GetAllOffre(){
        return ioffreService.retrieveAllOffre();
    }

    @GetMapping("/retrieve-Offre/{Offre-id}")
    public Offre retrieveOffre(@PathVariable("Offre-id") Long OffreId) {
        return ioffreService.retrieveOffre(OffreId);
    }

    @PostMapping("/addOffre")
    @ResponseBody
    public void addOffre(@RequestBody Offre offre) {
        ioffreService.ajouterOffre(offre);
    }

    //the update method
    @PutMapping("/updateOffre")
    public Offre updatePOffre(@RequestBody Offre offre) {
        return ioffreService.updateOffre(offre);
    }



    @DeleteMapping("/deleteOffre/{Offre-id}")
    @ResponseBody
    public void deleteEtudiant(@PathVariable("Offre-id") Long OffreId ) {
        ioffreService.deleteOffre(OffreId);
    }
    @PutMapping(value = "/affectationOffre_Partenaire/{offre-id}/{par-id}")
    public void affectationOffrePartenaire(@PathVariable("offre-id}") Long offretId ,@PathVariable("par-id}") Long parId )
    {
        ioffreService.assignOffreToPartenaire(offretId,parId);
    }

}