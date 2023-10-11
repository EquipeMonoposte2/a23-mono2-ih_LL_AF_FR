package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs;


import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.events.ApplicationFXEvent;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services.DB;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Utilisateur;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.calcules.CalculesCote;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 *
 */
@FxmlView("ConnectionVue.fxml")
@Component
public class ConnectionControleur {

    private CalculesCote cote;
    private DB bd;

    @Autowired
    public void setBd(DB bd) {
        this.bd = bd;
    }

    private final ApplicationEventPublisher applicationEventPublisher;

    public ConnectionControleur(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
    @FXML
    private TextField nomUtilisateurTextField;

    /**
     * Lance un évenement de type connection si l'utilisateur est valide ( avec applicationEventPublisher) sera entendu (ConfigurableApplicationContext context) de ApplicationFx
     * @param event
     */
    @FXML
    void connect(ActionEvent event) {
    // bd.getUtilisateursService().sauvegarderUtilisateur(utilisateurTemporaire);
        System.out.println(bd.getUtilisateursService().retourLesUtilisateurs().get(0));
        //Utilisateur utilisateur =bd.getUtilisateursService().retourLesUtilisateurs().get(0);
        /*if (true) {
            //passe un message pour tester et un utilisateur pour référence dans la navigation de l'application
            ApplicationFXEvent applicationFXEvent = ApplicationFXEvent.builder().estConnectionEvent(true).estCreationCompteEvent(false).estCreationCompteEvent(false).utilisateur(utilisateur).estDeconnectionEvent(false).build();
            applicationEventPublisher.publishEvent(applicationFXEvent);
        }*/

        System.out.println(cote.signifiance());


    }
    @Autowired
    public void setCote(CalculesCote cote) {
        this.cote = cote;
    }




    @FXML
    void ouvrirFormulaireCreationCompte(ActionEvent event) {
        applicationEventPublisher.publishEvent(ApplicationFXEvent.builder().estNouveauCompteEvent(true).build());
    }
}
