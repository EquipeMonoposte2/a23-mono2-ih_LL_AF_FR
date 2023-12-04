package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services;


import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Utilisateur;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.UtilisateurParType;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.repository.Repo_Utilisateur;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.repository.Repo_utilisateur_categorie;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UtilisateurParTypeService {

    private ApplicationContext context;
    private Repo_Utilisateur repo_utilisateur;
    private Repo_utilisateur_categorie repo_utilisateur_categorie;

    @Autowired
    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    @Autowired
    public void setRepo_utilisateur(Repo_Utilisateur repo_utilisateur) {
        this.repo_utilisateur = repo_utilisateur;
    }

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

    public void AssocierLutilisateurAuType(){
        List<Utilisateur> tousLesUser = new ArrayList<>();
        repo_utilisateur.findAll().forEach(tousLesUser::add);

        for (Utilisateur u : tousLesUser){
            UtilisateurParType utilisateurParType = context.getBean(UtilisateurParType.class);

            utilisateurParType.setType(u.getType());
            this.SaveUtilisateurCategorie(utilisateurParType);
        }
    }







}
