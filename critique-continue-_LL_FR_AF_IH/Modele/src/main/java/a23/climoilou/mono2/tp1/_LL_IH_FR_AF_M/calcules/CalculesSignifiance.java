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

    public int signifiance() {
        List<CombinaisonProduitIdCompte> combinaisonProduitIdCompteList = new ArrayList<>();

        for (Produit p : db.getProduitsService().retourLesProduits()) {
            combinaisonProduitIdCompteList.add(new CombinaisonProduitIdCompte(p.getId(),db.getRepoCritiqueLienProduit().countByProduitActuel(p.getId())));
        }

        Collections.sort(combinaisonProduitIdCompteList);
        CombinaisonProduitIdCompte combinaisonProduitIdCompte = combinaisonProduitIdCompteList.get(combinaisonProduitIdCompteList.size()-1);
        return combinaisonProduitIdCompte.val();
    }

    public float calculeSignifiance(Produit produit){
        return  (float) db.getRepoCritiqueLienProduit().countByProduitActuel(produit.getId()) /signifiance();
    }

    @Autowired
    public void setDb(DB db) {
        this.db = db;
    }
}