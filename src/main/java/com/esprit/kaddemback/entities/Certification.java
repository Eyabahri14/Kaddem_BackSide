package com.esprit.kaddemback.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table( name = "CERTIFICATION")
public class Certification implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IdCertif")
    private Long idCertif; // Clé primaire

    private String nomCertif;

    private String organismeDeDelivrance;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateEmission;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateExpiration;

    private int IdDiplome;

    private String UrlDiplome;

    public Long getIdCertif() {
        return idCertif;
    }



    public String getNomCertif() {
        return nomCertif;
    }

    public void setNomCertif(String nomCertif) {
        this.nomCertif = nomCertif;
    }

    public String getOrganismeDeDelivrance() {
        return organismeDeDelivrance;
    }

    public void setOrganismeDeDelivrance(String organismeDeDelivrance) {
        this.organismeDeDelivrance = organismeDeDelivrance;
    }

    public Date getDateEmission() {
        return dateEmission;
    }

    public void setDateEmission(Date dateEmission) {
        this.dateEmission = dateEmission;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public int getIdDiplome() {
        return IdDiplome;
    }

    public void setIdDiplome(int idDiplome) {
        IdDiplome = idDiplome;
    }

    public String getUrlDiplome() {
        return UrlDiplome;
    }

    public void setUrlDiplome(String urlDiplome) {
        UrlDiplome = urlDiplome;
    }
}
