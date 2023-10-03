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
//where annotation @EqualsAndHashCode.Include est présente
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
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

    //Pour l'instant c'est un string, pour le path, s'il y a une meilleure facon ont changera.
    private String image;

    @OneToMany(mappedBy = "produitActuel")
    @ToString.Exclude
    private List<CritiqueLienProduit> critiqueProduits = new ArrayList<>();

    public boolean critiqueApres(LocalDate dateDebut) {
        boolean[] retValeur = {false};
        critiqueProduits.stream().forEach(critiqueLienProduit -> {
            if(critiqueLienProduit.getCritiqueActuelle().getDateCritique().isAfter(dateDebut)){
                retValeur[0] = true;
            }
        });
        return retValeur[0];
    }

    public boolean critiqueAvant(LocalDate dateFin) {
        boolean[] retValeur = {false};
        critiqueProduits.forEach(critiqueLienProduit -> {
            if(critiqueLienProduit.getCritiqueActuelle().getDateCritique().isBefore(dateFin)){
                retValeur[0] = true;
            }
        });
        return retValeur[0];
    }
}
