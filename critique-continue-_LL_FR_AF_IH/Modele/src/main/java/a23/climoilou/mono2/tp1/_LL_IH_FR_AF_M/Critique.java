package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.LinkedList;

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

    private Date dateCritique;

    @ManyToOne
    @JoinColumn(name="utilisateur_id")
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "critique")
    private LinkedList<CritiqueProduit> critiqueProduits;

    /**
     * Ajoute un jeu à la critique
     * @param produit
     * @param enumEcart
     */
    public void ajouterJeu(Produit produit, EnumEcart enumEcart){
        critiqueProduits.add(CritiqueProduit.builder().produit(produit).critique(this).enumEcart(enumEcart).build());
    }

    /**
     * Supprime un jeu de la critiqueq
     * @param produit
     */
    public void supprimerJeu(Produit produit){
        critiqueProduits.removeIf(critiqueProduit -> critiqueProduit.getProduit().equals(produit));
    }

}
