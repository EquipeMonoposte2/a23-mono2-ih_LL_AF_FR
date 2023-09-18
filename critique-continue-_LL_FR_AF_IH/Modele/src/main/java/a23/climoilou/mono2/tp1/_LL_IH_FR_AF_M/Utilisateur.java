package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Entity
@Component
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public Type getType() {
        return type;
    }

    public void setDateDeNaissance(LocalDate dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }


}
