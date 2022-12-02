package com.esprit.kaddemback.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table( name ="Offre")

public class Offre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idOffre")
    private Long idOffre; // Clé primaire
    private String nomOffre;
    private String descriptionOffre;
    private String TypeOffre;

    @ManyToOne
    Partenaire partenaire;

    public Long getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(Long idOffre) {
        this.idOffre = idOffre;
    }

    public String getNomOffre() {
        return nomOffre;
    }

    public void setNomOffre(String nomOffre) {
        this.nomOffre = nomOffre;
    }

    public String getDescriptionOffre() {
        return descriptionOffre;
    }

    public void setDescriptionOffre(String descriptionOffre) {
        this.descriptionOffre = descriptionOffre;
    }

    public String getTypeOffre() {
        return TypeOffre;
    }

    public void setTypeOffre(String typeOffre) {
        TypeOffre = typeOffre;
    }

    public Partenaire getPartenaire() {
        return partenaire;
    }

    public void setPartenaire(Partenaire partenaire) {
        this.partenaire = partenaire;
    }
}
