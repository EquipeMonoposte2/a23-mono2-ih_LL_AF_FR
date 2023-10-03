package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
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
public class Critique {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate dateCritique;

    @ManyToOne
    @JoinColumn(name="utilisateur_id")
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "critiqueActuelle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CritiqueLienProduit> critiqueLienProduits;

    /**
     * Ajoute un jeu à la critique
     * @param produit
     * @param enumEcart
     */
    public void ajouterJeu(Produit produit, EnumEcart enumEcart, boolean estNeutre){
        critiqueLienProduits.add(CritiqueLienProduit.builder().produitActuel(produit).critiqueActuelle(this).enumEcart(enumEcart).estNeutre(estNeutre).build());
    }

    /**
     * Retourne vrai si la critique possède un jeu neutre
     * @return
     */
    public boolean possedeNeutre(){
        return critiqueLienProduits.stream().anyMatch(CritiqueLienProduit::isEstNeutre);
    }

    /**
     * Retourne vrai si la critique possède deja le jeu
     * @param produit
     * @return
     */
    public boolean possedeJeu(Produit produit){
        return critiqueLienProduits.stream().anyMatch(critiqueProduit -> critiqueProduit.getProduitActuel().equals(produit));
    }

}
