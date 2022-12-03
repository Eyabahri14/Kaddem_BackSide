package com.esprit.kaddemback.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@Table(name = "Equipe")
public class Equipe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEquipe")
    private Long idEquipe;
    private String nomEquipe;
    @Enumerated(EnumType.STRING)
    private Niveau niveau;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Etudiant> etudiants;

    @OneToOne
    private DetailEquipe detailEquipe;
}
