package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Critique;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.CritiqueProduit;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Utilisateur;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.repository.Repo_critique;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;

@Service
public class CritiquesService {

    private Repo_critique critiqueRepo;

    @Autowired
    public void setCritiqueRepo(Repo_critique critiqueRepo) {
        this.critiqueRepo = critiqueRepo;
    }

    @Transactional
    public void saveCritique(Critique critique){
        this.critiqueRepo.save(critique);
    }

    @Transactional
    public void supprimerCritique(Critique critique){
        this.critiqueRepo.delete(critique);
    }

    public Repo_critique getCritiqueRepo() {
        return critiqueRepo;
    }

    public Critique creerNouvelleCritique(Date date, Utilisateur utilisateur, LinkedList<CritiqueProduit> critiqueProduits){
        return Critique.builder().dateCritique(date).utilisateur(utilisateur).critiqueProduits(critiqueProduits).build();
    }

}
