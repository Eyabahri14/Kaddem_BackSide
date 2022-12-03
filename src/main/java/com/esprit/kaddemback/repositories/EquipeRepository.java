package com.esprit.kaddemback.repositories;

import com.esprit.kaddemback.entities.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipeRepository extends JpaRepository<Equipe,String> {
    public Equipe findByIdEquipe(long id);
    public List<Equipe> findAllByNiveau(String niveau);
}
