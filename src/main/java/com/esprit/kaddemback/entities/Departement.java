package com.esprit.kaddemback.entities;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table( name = "DEPARTEMENT")
public class Departement implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="IdDepart")
    private Long idDepart; // Clé primaire
    private String nomDepart;

    public Long getIdDepart() {
        return idDepart;
    }

    public void setIdDepart(Long idDepart) {
        this.idDepart = idDepart;
    }

    public String getNomDepart() {
        return nomDepart;
    }

    public void setNomDepart(String nomDepart) {
        this.nomDepart = nomDepart;
    }

    @OneToMany(mappedBy = "departement")
    private Set<User> users;
}