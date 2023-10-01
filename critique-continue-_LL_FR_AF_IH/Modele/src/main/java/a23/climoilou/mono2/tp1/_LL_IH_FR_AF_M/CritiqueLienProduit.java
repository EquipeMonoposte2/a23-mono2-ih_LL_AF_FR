package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M;

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
public class CritiqueLienProduit
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produitActuel;

    @ManyToOne
    @JoinColumn(name = "critique_id")
    private Critique critiqueActuelle;

    private EnumEcart enumEcart;
    private boolean estNeutre;

}
