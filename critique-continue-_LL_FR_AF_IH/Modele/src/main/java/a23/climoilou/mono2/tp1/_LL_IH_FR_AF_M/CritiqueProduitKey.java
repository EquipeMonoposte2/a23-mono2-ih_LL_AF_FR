package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class CritiqueProduitKey implements Serializable
{

    @Column(name = "produit_id")
    private Long produitId;

    @Column(name = "critique_id")
    private Long critiqueId;

    public CritiqueProduitKey() {
    }

    public Long getProduitId()
    {
        return produitId;
    }

    public void setProduitId(Long produitId)
    {
        this.produitId = produitId;
    }

    public Long getCritiqueId()
    {
        return critiqueId;
    }

    public void setCritiqueId(Long critiqueId)
    {
        this.critiqueId = critiqueId;
    }
}
