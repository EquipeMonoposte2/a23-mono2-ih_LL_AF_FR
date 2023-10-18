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

    /**
     * Calcule l'appreciation de chaque produit
     */
    public void calculeAppreciation() {
        List<Produit> produits = db.getProduitsService().retourLesProduits();
        List<Utilisateur> utilisateurs = db.getUtilisateursService().retourLesUtilisateurs();
        Map<Utilisateur, List<Map<Produit, Float>>> appreciationsUtilisateur = new HashMap<>();
        for (Utilisateur utilisateur : utilisateurs) {

            List<Critique> critiquesUtilisateurs = utilisateur.getCritiqueList();
            List<Map<Produit, Float>> appreciationUtilisateurActuelle = new ArrayList<>();

            for (Critique critiqueActuelle : critiquesUtilisateurs) {

                List<CritiqueLienProduit> critiqueLienProduits = critiqueActuelle.getCritiqueLienProduits();
                Collections.sort(critiqueLienProduits);

                //On obtient l indice du neutre dans la liste
                int indiceNeutre = critiqueActuelle.obtenirIndiceNeutre() - 1;

                //On obtient la map de pointage
                HashMap<Produit, Float> mapProduitAppreciation = obtenirMapPointageCalcule(critiqueLienProduits, indiceNeutre);

                //TODO : On calcule l'appreciation
                //Les divisions
                List<Float> pointages = mapProduitAppreciation.values().stream().sorted().toList();
                float plusForteAppreciation = pointages.get(mapProduitAppreciation.size() - 1);
                float plusFaibleAppreciation = pointages.get(0);
                diviserAppreciation(plusForteAppreciation, plusFaibleAppreciation, mapProduitAppreciation);

                appreciationUtilisateurActuelle.add(mapProduitAppreciation);
            }
            appreciationsUtilisateur.put(utilisateur, appreciationUtilisateurActuelle);
        }

        for (Produit produitActuel : produits) {
            /*float moyenneExpert = calculerMoyenne();
            float moyenneInfluenceur;
            float moyenneAmateur;*/
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
            produitActuel.setCote(5 + moyenneGlobaleDuProduit *  calculeSignifiance.calculerSignifiance(produitActuel) * 5);
            db.getProduitsService().saveProduit(produitActuel);
        }

    }

    private float additionnerMoyennes(float moyenneAmateur, float moyenneInfluenceur, float moyenneExpert) {
        return 0.45f * moyenneExpert + 0.22f * moyenneAmateur + 0.33f * moyenneInfluenceur;
    }

    private float calculerMoyenne(Map<Utilisateur, List<Map<Produit, Float>>> appreciations, Produit produitActuel) {
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


    private void diviserAppreciation(float plusForteAppreciation, float plusFaibleAppreciation, Map<Produit, Float> produitAppreciation) {
        for (Map.Entry<Produit, Float> entry : produitAppreciation.entrySet()) {
            if (entry.getValue() < 0f) {
                entry.setValue(-entry.getValue() / plusFaibleAppreciation);
            } else if (entry.getValue() > 0f) {
                entry.setValue(entry.getValue() / plusForteAppreciation);
            }
        }
    }

    private HashMap<Produit, Float> obtenirMapPointageCalcule(List<CritiqueLienProduit> critiqueLienProduits, int indiceNeutre) {
        HashMap<Produit, Float> mapProduitAppreciation = new HashMap<>();

        //On ajoute le neutre
        mapProduitAppreciation.put(critiqueLienProduits.get(indiceNeutre).getProduitActuel(), 0f);

        //On ajoute les autres
        pointageNegatif(critiqueLienProduits, indiceNeutre, mapProduitAppreciation);
        pointagePositif(critiqueLienProduits, indiceNeutre, mapProduitAppreciation);


        return mapProduitAppreciation;
    }

    private void pointagePositif(List<CritiqueLienProduit> critiqueLienProduits, int indiceNeutre, HashMap<Produit, Float> mapProduitAppreciation) {
        //Positifs
        float pointageActuel = 0f;
        for (int i = indiceNeutre + 1; i < critiqueLienProduits.size(); i++) {

            switch (critiqueLienProduits.get(i).getEcart()) {
                case FAIBLE -> pointageActuel += 1f;
                case NORMAL -> pointageActuel += 2f;
                case GRAND -> pointageActuel += 3f;
            }

            //On ajoute le produit actuel
            mapProduitAppreciation.put(critiqueLienProduits.get(i).getProduitActuel(), pointageActuel);
        }
    }

    private void pointageNegatif(List<CritiqueLienProduit> critiqueLienProduits, int indiceNeutre, HashMap<Produit, Float> mapProduitAppreciation) {
        //Negatifs
        float pointageActuel = 0f;
        for (int i = indiceNeutre - 1; i >= 0; i--) {

            switch (critiqueLienProduits.get(i).getEcart()) {
                case FAIBLE -> pointageActuel -= 1f;
                case NORMAL -> pointageActuel -= 2f;
                case GRAND -> pointageActuel -= 3f;
            }

            //On ajoute le produit actuel
            mapProduitAppreciation.put(critiqueLienProduits.get(i).getProduitActuel(), pointageActuel);
        }
    }
}