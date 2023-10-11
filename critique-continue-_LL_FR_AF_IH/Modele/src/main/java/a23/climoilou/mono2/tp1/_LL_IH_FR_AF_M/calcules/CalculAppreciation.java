package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.calcules;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Critique;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.CritiqueLienProduit;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Produit;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services.DB;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Component
public class CalculAppreciation
{
    private DB db;

    @Autowired
    public CalculAppreciation(DB db)
    {
        this.db = db;
    }

    /**
     * Calcule l'appreciation de chaque produit
     */
    public void calculeAppreciation()
    {

        List<Utilisateur> utilisateurs = db.getUtilisateursService().retourLesUtilisateurs();
        for (Utilisateur utilisateur : utilisateurs)
        {

            List<Critique> critiquesUtilisateurs = utilisateur.getCritiqueList();

            for (Critique critiqueActuelle : critiquesUtilisateurs)
            {

                List<CritiqueLienProduit> critiqueLienProduits = critiqueActuelle.getCritiqueLienProduits();
                Collections.sort(critiqueLienProduits);

                //On obtient l indice du neutre dans la liste
                int indiceNeutre = critiqueActuelle.obtenirIndiceNeutre() - 1;

                //On obtient la map de pointage
                HashMap<Produit, Float> mapProduitAppreciation = obtenirMapPointageCalcule(critiqueLienProduits, indiceNeutre);

                //TODO : On calcule l'appreciation
                //Les divisions
            }
        }

    }

    private HashMap<Produit, Float> obtenirMapPointageCalcule(List<CritiqueLienProduit> critiqueLienProduits, int indiceNeutre)
    {
        HashMap<Produit, Float> mapProduitAppreciation = new HashMap<>();

        //On ajoute le neutre
        mapProduitAppreciation.put(critiqueLienProduits.get(indiceNeutre).getProduitActuel(), 0f);

        //On ajoute les autres
        pointageNegatif(critiqueLienProduits, indiceNeutre, mapProduitAppreciation);
        pointagePositif(critiqueLienProduits, indiceNeutre, mapProduitAppreciation);


        return mapProduitAppreciation;
    }

    private void pointagePositif(List<CritiqueLienProduit> critiqueLienProduits, int indiceNeutre, HashMap<Produit, Float> mapProduitAppreciation)
    {
        //Positifs
        float pointageActuel = 0f;
        for (int i = indiceNeutre + 1; i < critiqueLienProduits.size(); i++)
        {

            switch (critiqueLienProduits.get(i).getEcart())
            {
                case FAIBLE -> pointageActuel += 1f;
                case NORMAL -> pointageActuel += 2f;
                case GRAND -> pointageActuel += 3f;
            }

            //On ajoute le produit actuel
            mapProduitAppreciation.put(critiqueLienProduits.get(i).getProduitActuel(), pointageActuel);
        }
    }

    private void pointageNegatif(List<CritiqueLienProduit> critiqueLienProduits, int indiceNeutre, HashMap<Produit, Float> mapProduitAppreciation)
    {
        //Negatifs
        float pointageActuel = 0f;
        for (int i = indiceNeutre-1; i >= 0; i--)
        {

            switch (critiqueLienProduits.get(i).getEcart())
            {
                case FAIBLE -> pointageActuel -= 1f;
                case NORMAL -> pointageActuel -= 2f;
                case GRAND -> pointageActuel -= 3f;
            }

            //On ajoute le produit actuel
            mapProduitAppreciation.put(critiqueLienProduits.get(i).getProduitActuel(), pointageActuel);
        }
    }
}