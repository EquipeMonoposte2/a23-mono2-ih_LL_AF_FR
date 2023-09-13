package a23.climoilou.mono2.tp1._LL_IH_FR_AF;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Utilisateur {

    @Id
    private Long id;
    private String nom;
    private LocalDate dateDeNaissance;


    private Type type;


    public Utilisateur(String nom, LocalDate dateDeNaissance, Type type) {
        this.nom = nom;
        this.dateDeNaissance = dateDeNaissance;
        this.type = type;
    }



    public Utilisateur() {

    }



    public Long getId() {
        return id;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(LocalDate dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }


}
