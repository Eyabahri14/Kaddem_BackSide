package com.esprit.kaddemback.controllers;

import com.esprit.kaddemback.entities.DetailEquipe;
import com.esprit.kaddemback.services.DetailEquipeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class DetailEquipeController {
    @Autowired
    DetailEquipeServiceImpl service;

    @GetMapping("/DetailEquipe")
    public List<DetailEquipe> retrieveAllDetailEquipes() {
        return service.retrieveAllDetailEquipes();
    }

    @GetMapping("/DetailEquipeById/{id}")
    public DetailEquipe retrieveDetailEquipeById(@PathVariable Long id)
    {
        return service.retrieveDetailEquipeById(id);
    }

    //the push methods

    @PostMapping("/addDetailEquipe")
    @ResponseBody
    public DetailEquipe addDetailEquipe(@RequestBody DetailEquipe e) {
        return service.addDetailEquipe(e);
    }

    //the update method
    @PutMapping("/updateDetailEquipe")
    public DetailEquipe updateDetailEquipe(@RequestBody DetailEquipe detailEquipe) {
        return service.updateDetailEquipe(detailEquipe);
    }

    //the remove method
    @DeleteMapping("/deleteDetailEquipe/{id}")
    public String deleteDetailEquipe(@PathVariable long id) {
        return service.deleteDetailEquipe(id);
    }
}
