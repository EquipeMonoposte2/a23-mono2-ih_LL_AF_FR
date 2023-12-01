package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.calcules;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CalculCote {
    CalculesSignifiance calculesSignifiance;
    CalculAppreciation calculAppreciation;

    @Autowired
    public CalculCote(CalculesSignifiance calculesSignifiance, CalculAppreciation calculAppreciation) {
        this.calculesSignifiance = calculesSignifiance;
        this.calculAppreciation = calculAppreciation;
    }

    public float calculerCoteFinale(Produit produitActuel){
        float appreciation = calculAppreciation.calculeAppreciation(produitActuel);
        float signifiance = calculesSignifiance.calculerSignifiance(produitActuel);
        return 5 + appreciation * signifiance * 5;
    }
}
