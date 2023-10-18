package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.calcules.CombinaisonProduitIdCompte;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//where annotation @EqualsAndHashCode.Include est présente
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Builder
public class CritiqueLienProduit implements Comparable<CritiqueLienProduit>
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int ordreListe;

    @ManyToOne
    @JoinColumn(name = "produit_id")
    @ToString.Exclude
    private Produit produitActuel;

    @ManyToOne
    @JoinColumn(name = "critique_id")
    @ToString.Exclude
    private Critique critiqueActuelle;

    @Enumerated(EnumType.STRING)
    private EnumEcart ecart;
    private boolean estNeutre;

    @Override
    public int compareTo(CritiqueLienProduit o)
    {
        return Integer.compare(this.ordreListe, o.ordreListe);
    }
}
