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

    public CritiqueProduitKey getId()
    {
        return id;
    }

    public void setId(CritiqueProduitKey id)
    {
        this.id = id;
    }

    public Produit getProduit()
    {
        return produit;
    }

    public void setProduit(Produit produit)
    {
        this.produit = produit;
    }

    public Critique getCritique()
    {
        return critique;
    }

    public void setCritique(Critique critique)
    {
        this.critique = critique;
    }

    public EnumEcart getEnumEcart()
    {
        return enumEcart;
    }

    public void setEnumEcart(EnumEcart enumEcart)
    {
        this.enumEcart = enumEcart;
    }
}
