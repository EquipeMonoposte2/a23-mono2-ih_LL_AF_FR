package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M;

import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@AllArgsConstructor
@Builder
public class Filtre {
    public List<Produit> filtrerProduits(Set<Type> types, LocalDate dateDebut, LocalDate dateFin, List<Produit> produits) {
        types = types != null ? types : new HashSet<Type>();
        boolean estAmateur = types.contains(Type.Utilisateur);
        boolean estInfluenceur = types.contains(Type.Influencer);
        boolean estExpert = types.contains(Type.Expert);
        if (types.contains(Type.Expert) || types.contains(Type.Utilisateur) || types.contains(Type.Influencer)) {
            produits = filtrerRoles(produits, estAmateur, estInfluenceur, estExpert);
        }
        if (dateDebut != null || dateFin != null) {
            produits = filtrerDates(produits, dateDebut, dateFin);
        }
        return produits;
    }

    private List<Produit> filtrerDates(List<Produit> produits, LocalDate dateDebut, LocalDate dateFin) {
        return produits.stream()
                .filter(produit -> {
                    boolean critiqueApres = dateDebut == null || produit.critiqueApres(dateDebut);
                    boolean critiqueAvant = dateFin == null || produit.critiqueAvant(dateFin);
                    return critiqueAvant && critiqueApres;
                }).toList();
    }

    private List<Produit> filtrerRoles(List<Produit> produits, boolean estAmateur, boolean estInfluenceur, boolean estExpert) {
        return produits.stream().filter(produit -> produit.getCritiqueProduits().stream().anyMatch(critiqueLienProduit -> {
            Type typeUtilisateur = critiqueLienProduit.getCritiqueActuelle().getUtilisateur().getType();
            return (typeUtilisateur == Type.Utilisateur && estAmateur) ||
                    (typeUtilisateur == Type.Influencer && estInfluenceur) ||
                    (typeUtilisateur == Type.Expert && estExpert);
        })).toList();
    }
}
