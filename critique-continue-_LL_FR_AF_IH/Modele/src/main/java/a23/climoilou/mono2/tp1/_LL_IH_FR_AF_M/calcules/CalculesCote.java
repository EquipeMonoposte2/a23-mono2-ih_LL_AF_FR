package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.calcules;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Critique;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Produit;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services.DB;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Provider;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Classe de calcule de cote
 */
@Component
public class CalculesCote {

    private DB db;

    public int calculeSignifiance() {
        List<Utilisateur> utilisateurs = db.getUtilisateursService().retourLesUtilisateurs();
        List<Produit> produits = db.getProduitsService().retourLesProduits();
        Integer compteDeProduit;
        Long produitIDFinal;

        Map<Long, Integer> mapDeProduitEtNombreDeFoisCritique = new HashMap<>();


        for (Produit produit : produits) {
            Long produitID = produit.getId();
            compteDeProduit = 0;
            mapDeProduitEtNombreDeFoisCritique.put(produitID, 0);

            for (Utilisateur utilisateur : utilisateurs) {

                List<Critique> critiqueList = db.getCritiquesService().getCritiqueRepo().getCritiquesByUtilisateur(utilisateur);
                if(critiqueList!=null){
                for (Critique critique : critiqueList) {

                    if (Objects.equals(critique.getUtilisateur().getIdentifiant(), utilisateur.getIdentifiant())) {
                        compteDeProduit++;
                    }
                }

            }}
            mapDeProduitEtNombreDeFoisCritique.replace(produitID,compteDeProduit);
        }

        for (Map.Entry<Long, Integer> entryMap : mapDeProduitEtNombreDeFoisCritique.entrySet()){
            System.out.println(entryMap.getKey()+"  "+ entryMap.getValue());
        }

        return 0;
    }

    @Autowired
    public void setDb(DB db) {
        this.db = db;
    }
}
