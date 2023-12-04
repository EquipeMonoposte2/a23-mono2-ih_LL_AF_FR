package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.repository.Repo_critique_lien_produit;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DB {

    private CritiquesService critiquesService;

    private Repo_critique_lien_produit repoCritiqueLienProduit;
    private ProduitsService produitsService;

    private UtilisateursService utilisateursService;

    @Getter
    private UtilisateurParTypeService utilisateurParTypeService;

    @Autowired
    public void setUtilisateurParTypeService(UtilisateurParTypeService utilisateurParTypeService) {
        this.utilisateurParTypeService = utilisateurParTypeService;
    }

    @Autowired
    public void setUtilisateursService(UtilisateursService utilisateursService) {
        this.utilisateursService = utilisateursService;
    }

    @Autowired
    public void setRepoCritiqueLienProduit(Repo_critique_lien_produit repoCritiqueLienProduit) {
        this.repoCritiqueLienProduit = repoCritiqueLienProduit;
    }

    @Autowired
    public void setCritiquesService(CritiquesService critiquesService) {
        this.critiquesService = critiquesService;
    }

    @Autowired
    public void setProduitsService(ProduitsService produitsService) {
        this.produitsService = produitsService;
    }

    public UtilisateursService getUtilisateursService() {
        return utilisateursService;
    }
    public ProduitsService getProduitsService() {
        return produitsService;
    }

    public CritiquesService getCritiquesService() {
        return critiquesService;
    }

    public Repo_critique_lien_produit getRepoCritiqueLienProduit() {
        return repoCritiqueLienProduit;
    }
}
