package com.esprit.kaddemback.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@Table(name = "DetailEquipe")
public class DetailEquipe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDetailEquipe")
    private Long salle;
    private String thematique;
    @OneToOne(mappedBy="detailEquipe")
    private Equipe equipe;
}