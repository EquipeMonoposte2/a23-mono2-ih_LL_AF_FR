package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.calcules;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Critique;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.CritiqueLienProduit;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Produit;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services.DB;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CalculAppreciation {
    private DB db;
    private CalculesSignifiance calculeSignifiance;

    @Autowired
    public CalculAppreciation(DB db, CalculesSignifiance calculeSignifiance) {
        this.db = db;
        this.calculeSignifiance = calculeSignifiance;
    }

    public float calculeAppreciation(Produit produitActuel){
        List<Utilisateur> utilisateurs = db.getUtilisateursService().retourLesUtilisateurs();
        Map<Utilisateur, List<Map<Produit, Float>>> appreciationsUtilisateur = new HashMap<>();
        for (Utilisateur utilisateur : utilisateurs) {

            List<Critique> critiquesUtilisateurs = utilisateur.getCritiqueList();
            List<Map<Produit, Float>> appreciationUtilisateurActuelle = new ArrayList<>();

            for (Critique critiqueActuelle : critiquesUtilisateurs) {

                List<CritiqueLienProduit> critiqueLienProduits = critiqueActuelle.getCritiqueLienProduits();
                Collections.sort(critiqueLienProduits);

                int indiceNeutre = critiqueActuelle.obtenirIndiceNeutre() - 1;

                HashMap<Produit, Float> mapProduitAppreciation = obtenirMapPointageCalcule(critiqueLienProduits, indiceNeutre);

                //TODO : On calcule l'appreciation
                diviserAppreciation(mapProduitAppreciation);

                appreciationUtilisateurActuelle.add(mapProduitAppreciation);
            }
            appreciationsUtilisateur.put(utilisateur, appreciationUtilisateurActuelle);
        }
        Map<Utilisateur, List<Map<Produit, Float>>> appreciationsExpert = new HashMap<>();
        Map<Utilisateur, List<Map<Produit, Float>>> appreciationsAmateur = new HashMap<>();
        Map<Utilisateur, List<Map<Produit, Float>>> appreciationsInfluenceur = new HashMap<>();

        for (Map.Entry<Utilisateur, List<Map<Produit, Float>>> entry : appreciationsUtilisateur.entrySet()) {
            switch (entry.getKey().getType()) {
                case Expert -> appreciationsExpert.put(entry.getKey(), entry.getValue());
                case AMATEUR -> appreciationsAmateur.put(entry.getKey(), entry.getValue());
                case Influencer -> appreciationsInfluenceur.put(entry.getKey(), entry.getValue());
            }
        }

        float moyenneExpert = calculerMoyenne(appreciationsExpert, produitActuel);
        float moyenneInfluenceur = calculerMoyenne(appreciationsInfluenceur, produitActuel);
        float moyenneAmateur = calculerMoyenne(appreciationsAmateur, produitActuel);
        float moyenneGlobaleDuProduit = additionnerMoyennes(moyenneAmateur, moyenneInfluenceur, moyenneExpert);
        return moyenneGlobaleDuProduit;
    }


    /**
     * Additionne les 3 moyennes selon leur pondération
     * @param moyenneAmateur
     * @param moyenneInfluenceur
     * @param moyenneExpert
     * @return moyenne pondérée
     */
    public float additionnerMoyennes(float moyenneAmateur, float moyenneInfluenceur, float moyenneExpert) {
        return 0.45f * moyenneExpert + 0.22f * moyenneAmateur + 0.33f * moyenneInfluenceur;
    }

    /**
     * Calcule la moyenne d'un produit selon une liste d'appréciations
     * @param appreciations Map d'utilisateur et ses appréciations
     * @param produitActuel Produit duquel on calcule la moyenne
     * @return la moyenne d'un produit selon les appréciations sélectionnées
     */
    public float calculerMoyenne(Map<Utilisateur, List<Map<Produit, Float>>> appreciations, Produit produitActuel) {
        float valeurRetour = 0f;
        float nombreDeProduits = 0f;
        for(Map.Entry<Utilisateur, List<Map<Produit, Float>>> entry :  appreciations.entrySet()){
            for(Map<Produit, Float> appreciationActuelle : entry.getValue()){
                for(Produit p : appreciationActuelle.keySet()){
                    if(p.equals(produitActuel)){
                        valeurRetour += appreciationActuelle.get(p);
                        nombreDeProduits++;
                    }
                }
            }
        }

        return valeurRetour != 0 ? valeurRetour / nombreDeProduits : 0f;
    }


    /**
     * Divise la cote des produits par la meilleur cote positive ou la pire cote négative
     * @param produitAppreciation appréciation à calculer
     */
    public void diviserAppreciation(Map<Produit, Float> produitAppreciation) {
        List<Float> pointages = produitAppreciation.values().stream().sorted().toList();
        float plusForteAppreciation = pointages.get(produitAppreciation.size() - 1);
        float plusFaibleAppreciation = pointages.get(0);
        for (Map.Entry<Produit, Float> entry : produitAppreciation.entrySet()) {
            if (entry.getValue() < 0f) {
                entry.setValue(-entry.getValue() / plusFaibleAppreciation);
            } else if (entry.getValue() > 0f) {
                entry.setValue(entry.getValue() / plusForteAppreciation);
            }
        }
    }

    /**
     * Calcule le pointage des produits d'une critique à l'aide de l'indice du produit neutre
     * @param critiqueLienProduits Lien du produit et sa critique
     * @param indiceNeutre indice du produit "neutre"
     * @return Map de produit et de pointage
     */
    public HashMap<Produit, Float> obtenirMapPointageCalcule(List<CritiqueLienProduit> critiqueLienProduits, int indiceNeutre) {
        HashMap<Produit, Float> mapProduitAppreciation = new HashMap<>();

        mapProduitAppreciation.put(critiqueLienProduits.get(indiceNeutre).getProduitActuel(), 0f);

        pointageNegatif(critiqueLienProduits, indiceNeutre, mapProduitAppreciation);
        pointagePositif(critiqueLienProduits, indiceNeutre, mapProduitAppreciation);


        return mapProduitAppreciation;
    }

    /**
     * Calcule le pointage de tous les produits mieux notés que celui à l'indice neutre
     * @param critiqueLienProduits Liste de liens critique/produit
     * @param indiceNeutre indice du produit neutre de la critique
     * @param mapProduitAppreciation Map de produits et de leur pointage dans laquel mettre les pointage positifs
     */
    public void pointagePositif(List<CritiqueLienProduit> critiqueLienProduits, int indiceNeutre, HashMap<Produit, Float> mapProduitAppreciation) {
        float pointageActuel = 0f;
        for (int i = indiceNeutre + 1; i < critiqueLienProduits.size(); i++) {

            switch (critiqueLienProduits.get(i).getEcart()) {
                case FAIBLE -> pointageActuel += 1f;
                case NORMAL -> pointageActuel += 2f;
                case GRAND -> pointageActuel += 3f;
            }

            mapProduitAppreciation.put(critiqueLienProduits.get(i).getProduitActuel(), pointageActuel);
        }
    }

    /**
     * Calcule le pointage de tous les produits moins bien notés que celui à l'indice neutre
     * @param critiqueLienProduits Liste de liens critique/produit
     * @param indiceNeutre indice du produit neutre de la critique
     * @param mapProduitAppreciation Map de produits et de leur pointage dans laquel mettre les pointage négatifs
     */
    public void pointageNegatif(List<CritiqueLienProduit> critiqueLienProduits, int indiceNeutre, HashMap<Produit, Float> mapProduitAppreciation) {
        float pointageActuel = 0f;
        for (int i = indiceNeutre - 1; i >= 0; i--) {

            switch (critiqueLienProduits.get(i).getEcart()) {
                case FAIBLE -> pointageActuel -= 1f;
                case NORMAL -> pointageActuel -= 2f;
                case GRAND -> pointageActuel -= 3f;
            }

            mapProduitAppreciation.put(critiqueLienProduits.get(i).getProduitActuel(), pointageActuel);
        }
    }
}