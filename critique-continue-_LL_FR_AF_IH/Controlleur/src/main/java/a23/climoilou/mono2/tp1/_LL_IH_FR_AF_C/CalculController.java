package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.events.SoumettreCritiqueEvent;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Critique;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.CritiqueLienProduit;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Produit;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services.DB;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.calcules.CalculCote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CalculController {
    private CalculCote calculCote;

    private DB db;

    @EventListener
    public void onSoumissionCritique(SoumettreCritiqueEvent event){
        Critique critique =  event.getNouvelleCritique();
        for(CritiqueLienProduit critiqueLienProduit : critique.getCritiqueLienProduits()){
            Produit produit = critiqueLienProduit.getProduitActuel();
            produit.setCote(calculCote.calculerCoteFinale(produit));
            db.getProduitsService().saveProduit(produit);
        }
    }

    @Autowired
    public void setCalculCote(CalculCote calculCote){
        this.calculCote = calculCote;
    }

    @Autowired
    public void setDb(DB db){
        this.db = db;
    }
}
