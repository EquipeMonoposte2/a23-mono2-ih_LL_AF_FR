package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.calcules;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Produit;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services.DB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Classe de calcule de cote
 */
@Component
public class CalculesSignifiance
{

    private DB db;

    /**
     * Calcule compte de réference
     * @return
     */
    public CombinaisonProduitIdCompte signifiance() {
        List<CombinaisonProduitIdCompte> combinaisonProduitIdCompteList = db.getProduitsService().retourLesProduits().stream().map(p->new CombinaisonProduitIdCompte(p.getId(),db.getRepoCritiqueLienProduit().countByProduitActuel(p.getId()))).toList();
        CombinaisonProduitIdCompte compteRef = combinaisonProduitIdCompteList.stream().max((c1,c2)-> c1.compareTo(c2)).orElse(null);
        return compteRef;
    }

    /**
     *Calcule de signifiance du produit passé en paramètre et selon le compte de réference
     * @param produit
     * @return
     */
    public float calculeSignifiance(Produit produit){
        CombinaisonProduitIdCompte compteRef = signifiance();
        return  (float) db.getRepoCritiqueLienProduit().countByProduitActuel(produit.getId()) /compteRef.val();
    }

    @Autowired
    public void setDb(DB db) {
        this.db = db;
    }
}