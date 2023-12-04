package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "Produit")
public class Produit {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true )
    private String nom;

    private float cote;

    private String description;

    private LocalDate dateDeSortie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categorie_id") // Nom de la colonne dans la table Produit pour la clé étrangère vers Categorie
    private Categorie categorie;

    //Pour l'instant c'est un string, pour le path, s'il y a une meilleure facon ont changera.
    private String image;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produit)) return false;
        Produit produit = (Produit) o;
        return getId().equals(produit.getId());
    }

    @OneToMany(mappedBy = "produitActuel")
    @ToString.Exclude
    private List<CritiqueLienProduit> critiqueProduits = new ArrayList<>();

    public boolean critiqueApres(LocalDate date) {
        boolean[] retValeur = {false};
        critiqueProduits.stream().forEach(critiqueLienProduit -> {
            LocalDate dateCritiqueTemp = critiqueLienProduit.getCritiqueActuelle().getDateCritique();
            if(dateCritiqueTemp.isAfter(date) || dateCritiqueTemp.isEqual(date)){
                retValeur[0] = true;
            }
        });
        return retValeur[0];
    }

    public boolean critiqueAvant(LocalDate date) {
        boolean[] retValeur = {false};
        critiqueProduits.forEach(critiqueLienProduit -> {
            LocalDate dateCritiqueTemp = critiqueLienProduit.getCritiqueActuelle().getDateCritique();
            if(dateCritiqueTemp.isBefore(date) || dateCritiqueTemp.isEqual(date)){
                retValeur[0] = true;
            }
        });
        return retValeur[0];
    }
}
