package com.esprit.kaddemback.repositories;

import com.esprit.kaddemback.entities.DetailEquipe;
import com.esprit.kaddemback.entities.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailEquipeRepository  extends JpaRepository<DetailEquipe,String> {
    public DetailEquipe findByIdDetailEquipe(long id);
}
