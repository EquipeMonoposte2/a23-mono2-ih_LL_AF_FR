package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs;


import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.events.ConnectionEvent;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.events.CreationCompteEvent;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.events.NouveauCompteEvent;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services.DB;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Type;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 */
@FxmlView("ConnectionVue.fxml")
@Component
public class ConnectionControleur {

    private DB bd;

    @Autowired
    public void setBd(DB bd) {
        this.bd = bd;
    }

    private final ApplicationEventPublisher applicationEventPublisher;

    public ConnectionControleur(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * Lance un évenement de type connection si l'utilisateur est valide ( avec applicationEventPublisher) sera entendu (ConfigurableApplicationContext context) de ApplicationFx
     * @param event
     */
    @FXML
    void connect(ActionEvent event) {
        //condition pour valider la connection
        //user temporaire
        Utilisateur utilisateurTemporaire = new Utilisateur(Long.getLong("1"),"Tommy","9989978",LocalDate.now(),Type.Utilisateur,new ArrayList<>());

       // bd.getUtilisateursService().sauvegarderUtilisateur(utilisateurTemporaire);

        System.out.println(bd.getUtilisateursService().retourLesUtilisateurs().get(0));



        if (true) {
            //passe un message pour tester et un utilisateur pour référence dans la navigation de l'application
            applicationEventPublisher.publishEvent(new ConnectionEvent(this,"BouttonClick dans connection controleur",utilisateurTemporaire));
        }
    }

    @FXML
    void ouvrirFormulaireCreationCompte(ActionEvent event) {
        applicationEventPublisher.publishEvent(new NouveauCompteEvent(this));
    }
}
