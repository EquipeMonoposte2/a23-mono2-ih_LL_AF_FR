package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class DataBase {
    private CritiquesService serviceCritique;

    private UtilisateursService serviceUser;

    private ProduitsService produitsService;

    @Autowired
    public void setServiceCritique(CritiquesService serviceCritique) {
        this.serviceCritique = serviceCritique;
    }

    @Autowired
    public void setServiceUser(UtilisateursService serviceUser) {
        this.serviceUser = serviceUser;
    }
    @Autowired
    public void setProduitsService(ProduitsService produitsService) {
        this.produitsService = produitsService;
    }
}
