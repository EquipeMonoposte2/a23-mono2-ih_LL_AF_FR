package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//where annotation @EqualsAndHashCode.Include est présente
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Builder
@Entity
public class CritiqueProduit
{

    @EmbeddedId
    private CritiqueProduitKey id;

    @ManyToOne
    @MapsId ( "produitId" )
    @JoinColumn(name = "produit_id")
    private Produit produit;

    @ManyToOne
    @MapsId ( "critiqueId" )
    @JoinColumn(name = "critique_id")
    private Critique critique;

    private EnumEcart enumEcart;
    private boolean estNeutre;
}
