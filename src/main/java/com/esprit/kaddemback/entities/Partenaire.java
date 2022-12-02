package com.esprit.kaddemback.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table( name ="Partenaire")
public class Partenaire implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idPartenaire")
    private Long idPartenaire; // Clé primaire
    private String nomPartenaire;
    private String localisation;
    private String email;
    private int numTelPar;
    private boolean mobilite;
    @Enumerated(EnumType.STRING)
    Support support ;

     /* @ManyToOne
    Universite universites; */

    @OneToMany( mappedBy="partenaire")
    private Set<Offre> offres;


    public Long getIdPartenaire() {
        return idPartenaire;
    }

    public void setIdPartenaire(Long idPartenaire) {
        this.idPartenaire = idPartenaire;
    }

    public String getNomPartenaire() {
        return nomPartenaire;
    }

    public void setNomPartenaire(String nomPartenaire) {
        this.nomPartenaire = nomPartenaire;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumTelPar() {
        return numTelPar;
    }

    public void setNumTelPar(int numTelPar) {
        this.numTelPar = numTelPar;
    }

    public boolean isMobilite() {
        return mobilite;
    }

    public void setMobilite(boolean mobilite) {
        this.mobilite = mobilite;
    }

    public Support getSupport() {
        return support;
    }

    public void setSupport(Support support) {
        this.support = support;
    }

    public Set<Offre> getOffres() {
        return offres;
    }

    public void setOffres(Set<Offre> offres) {
        this.offres = offres;
    }

    @Override
    public String toString() {
        return "Partenaire{" +
                "idPartenaire=" + idPartenaire +
                ", nomPartenaire='" + nomPartenaire + '\'' +
                ", localisation='" + localisation + '\'' +
                ", email='" + email + '\'' +
                ", numTelPar=" + numTelPar +
                ", mobilite=" + mobilite +
                ", support=" + support +
                ", offres=" + offres +
                '}';
    }
}