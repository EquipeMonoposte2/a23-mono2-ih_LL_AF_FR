package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Utilisateur;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.repository.Repo_Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public class UtilisateursService {
   /* private final Repo_Utilisateur repoUtilisateur;



    @Autowired
    public UtilisateursService(Repo_Utilisateur repoUtilisateur) {
        this.repoUtilisateur = repoUtilisateur;
    }

    public List<Utilisateur> getRepoUtilisateur() {
        return (List<Utilisateur>) repoUtilisateur.findAll();
    }

    public Utilisateur findUtilisateurByID(Long id){
        return repoUtilisateur.findById(id).orElse(null);
    }

    public void createUtilisateur(Utilisateur utilisateur){
        if (utilisateur != null){
            repoUtilisateur.save(utilisateur);
        }else {
            System.err.println("utilisateur null on create : "+this);
        }
    }

    public void deleteUtilisateur(Utilisateur utilisateur){
        if (utilisateur != null){
            repoUtilisateur.delete(utilisateur);
        }else {
            System.err.println("utilisateur null on delete : "+this);
        }
    }*/

}
