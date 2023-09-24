package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Utilisateur;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.repository.Repo_Utilisateur;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UtilisateursService {
  private Repo_Utilisateur utilisateurRepo;

  @Autowired
    public void setUtilisateurRepo(Repo_Utilisateur utilisateurRepo) {
        this.utilisateurRepo = utilisateurRepo;
    }

    @Transactional
    public void sauvegarderUtilisateur(Utilisateur utilisateur){
        this.utilisateurRepo.save(utilisateur);
    }

    @Transactional
    public void surpprimerUtilisateur(Utilisateur utilisateur){
        this.utilisateurRepo.delete(utilisateur);
    }

    @Transactional
    public List<Utilisateur> retourLesUtilisateurs(){
      List<Utilisateur> utilisateursList = new ArrayList<>();

        for (Utilisateur util: this.utilisateurRepo.findAll()) {
            utilisateursList.add(util);
        }
        return utilisateursList;
    }
}
