package com.esprit.kaddemback.entities;


import javax.persistence.*;
import java.io.Serializable;

@Entity

@Table(name = "DetailEquipe")
public class DetailEquipe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDetailEquipe")
    private Long idDetailEquipe;
    private Long salle;
    private String thematique;
    @OneToOne(mappedBy="detailEquipe")
    private Equipe equipe;

    public Long getSalle() {
        return salle;
    }

    public Long getIdDetailEquipe() {
        return idDetailEquipe;
    }

    public void setIdDetailEquipe(Long idDetailEquipe) {
        this.idDetailEquipe = idDetailEquipe;
    }

    public void setSalle(Long salle) {
        this.salle = salle;
    }

    public String getThematique() {
        return thematique;
    }

    public void setThematique(String thematique) {
        this.thematique = thematique;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }
    @Override
    public String toString() {
        return "DetailEquipe{" +
                "salle=" + salle +
                ", thematique='" + thematique + '\'' +
                ", equipe=" + equipe +
                '}';
    }

}