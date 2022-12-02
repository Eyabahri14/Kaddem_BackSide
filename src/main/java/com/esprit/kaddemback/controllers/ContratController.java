package com.esprit.kaddemback.controllers;

import com.esprit.kaddemback.entities.Contrat;
import com.esprit.kaddemback.services.ContratService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ContratController {

    @Autowired
    ContratService service;

    //the get methods
    @GetMapping("/Contrat")
    public List<Contrat> retrieveAllContrats() {
        return service.retrieveAllContrats();
    }

    @GetMapping("/ContratById/{id}")
    public Contrat retrieveContratById(@PathVariable Long id)
    {
        return service.retrieveContratById(id);
    }

    //the push methods

    @PostMapping("/addContrat")
    @ResponseBody
    public Contrat addContrat(@RequestBody Contrat e) {
        return service.addContrat(e);
    }

    //the update method
    @PutMapping("/updateContrat")
    public Contrat updateContrat(@RequestBody Contrat Contrat) {
        return service.updateContrat(Contrat);
    }

    //the remove method
    @DeleteMapping("/deleteContrat/{id}")
    public String deleteContrat(@PathVariable long id) {
        return service.deleteContrat(id);
    }


}
