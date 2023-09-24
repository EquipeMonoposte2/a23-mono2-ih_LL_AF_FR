package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DB {

    private CritiquesService critiquesService;

    private ProduitsService produitsService;

    private UtilisateursService utilisateursService;



    @Autowired
    public void setUtilisateursService(UtilisateursService utilisateursService) {
        this.utilisateursService = utilisateursService;
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
}
