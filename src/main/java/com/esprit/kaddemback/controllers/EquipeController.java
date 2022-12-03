package com.esprit.kaddemback.controllers;

import com.esprit.kaddemback.entities.Equipe;
import com.esprit.kaddemback.services.EquipeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class EquipeController {
    @Autowired
    EquipeServiceImpl service;
    @GetMapping("/Equipe")
    public List<Equipe> retrieveAllEquipe() {
        return service.retrieveAllEquipes();
    }

    @GetMapping("/EquipeById/{id}")
    public Equipe retrieveEquipeById(@PathVariable Long id)
    {
        return service.retrieveEquipeById(id);
    }

    //the push methods

    @PostMapping("/addEquipe")
    @ResponseBody
    public Equipe addEquipe(@RequestBody Equipe e) {
        return service.addEquipe(e);
    }

    //the update method
    @PutMapping("/updateEquipe")
    public Equipe updateEquipe(@RequestBody Equipe equipe) {
        return service.updateEquipe(equipe);
    }

    //the remove method
    @DeleteMapping("/deleteEquipe/{id}")
    public String deleteEquipe(@PathVariable long id) {
        return service.deleteEquipe(id);
    }

}
