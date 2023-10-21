package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Lazy;

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
        critiqueLienProduits.add(CritiqueLienProduit.builder().produitActuel(produit).critiqueActuelle(this).ecart(enumEcart).estNeutre(estNeutre).ordreListe(critiqueLienProduits.size()+1).build());
    }

    /**
     * Retourne vrai si la critique possède un jeu neutre
     * @return
     */
    public boolean possedeNeutre(){
        return critiqueLienProduits.stream().anyMatch(CritiqueLienProduit::isEstNeutre);
    }

    public int obtenirIndiceNeutre(){
        int retour = -1;

        if(possedeNeutre()){
            retour = critiqueLienProduits.stream().filter(CritiqueLienProduit::isEstNeutre).findFirst().get().getOrdreListe();
        }
        return retour;
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
