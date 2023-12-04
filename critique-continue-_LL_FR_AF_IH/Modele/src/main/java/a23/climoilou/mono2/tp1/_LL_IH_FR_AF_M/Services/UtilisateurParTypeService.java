package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services;


import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.UtilisateurParType;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.repository.Repo_utilisateur_categorie;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurParTypeService {

    private Repo_utilisateur_categorie repo_utilisateur_categorie;

    @Autowired
    public void setRepo_utilisateur_categorie(Repo_utilisateur_categorie repo_utilisateur_categorie) {
        this.repo_utilisateur_categorie = repo_utilisateur_categorie;
    }

    @Transactional
    public void SaveUtilisateurCategorie(UtilisateurParType s){
        this.repo_utilisateur_categorie.save(s);
    }

    @Transactional
    public Iterable<UtilisateurParType> RetourneMesUtilisateurPerType(){
        return this.repo_utilisateur_categorie.findAll();
    }



}
