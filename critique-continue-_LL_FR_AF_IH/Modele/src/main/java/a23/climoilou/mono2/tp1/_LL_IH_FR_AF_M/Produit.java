package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
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

    private String description;

    private LocalDate dateDeSortie;

    //Pour l'instant c'est un string, pour le path, s'il y a une meilleure facon ont changera.
    private String image;

    @OneToMany(mappedBy = "produitActuel")
    private List<CritiqueLienProduit> critiqueProduits = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produit)) return false;
        Produit produit = (Produit) o;
        return getId().equals(produit.getId());
    }

}
