package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Critique;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.CritiqueLienProduit;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.CritiqueTreeViewNode;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Utilisateur;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.repository.Repo_critique;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

@Service
public class CritiquesService {

    private Repo_critique critiqueRepo;

    @Autowired
    public void setCritiqueRepo(Repo_critique critiqueRepo) {
        this.critiqueRepo = critiqueRepo;
    }

    @Transactional
    public void saveCritique(Critique critique) {
        this.critiqueRepo.save(critique);
    }

    @Transactional
    public void supprimerCritique(Critique critique) {
        this.critiqueRepo.delete(critique);
    }

    public Repo_critique getCritiqueRepo() {
        return critiqueRepo;
    }

    public Critique creerNouvelleCritique(LocalDate date, Utilisateur utilisateur, List<CritiqueLienProduit> critiqueProduits){
        return Critique.builder().dateCritique(date).utilisateur(utilisateur).critiqueLienProduits(critiqueProduits).build();
    }

    public List<CritiqueTreeViewNode> getCritiquesTreeView(Utilisateur utilisateur) {
        List<Critique> critiques = critiqueRepo.getCritiquesByUtilisateur(utilisateur);
        Map<Integer, Map<Month, List<Critique>>> critiquesParMoisEtAnnees = new HashMap<>();

        // Organisation des critiques par année et mois
        critiques.forEach(critique -> {
            int annee = critique.getDateCritique().getYear();
            Month mois = critique.getDateCritique().getMonth();

            critiquesParMoisEtAnnees
                    .computeIfAbsent(annee, k -> new HashMap<>())
                    .computeIfAbsent(mois, k -> new ArrayList<>())
                    .add(critique);
        });

        List<CritiqueTreeViewNode> treeView = new ArrayList<>();
        critiquesParMoisEtAnnees.forEach((year, monthMap) -> {
            CritiqueTreeViewNode yearNode = new CritiqueTreeViewNode(null, LocalDate.of(year, 1, 1));
            monthMap.forEach((month, critiquesOfMonth) -> {
                CritiqueTreeViewNode monthNode = new CritiqueTreeViewNode(null, LocalDate.of(year, month, 1));
                critiquesOfMonth.forEach(critique -> {
                    CritiqueTreeViewNode critiqueNode = new CritiqueTreeViewNode(critique.getId(), critique.getDateCritique());
                    monthNode.getChildren().add(critiqueNode);
                });
                yearNode.getChildren().add(monthNode);
            });
            treeView.add(yearNode);
        });

        return treeView;
    }
}