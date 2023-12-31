package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.events.ApplicationFXEvent;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services.DB;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.calcules.CalculAppreciation;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.calcules.CalculCote;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.calcules.CalculesSignifiance;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Type;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Utilisateur;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.UtilisateurSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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

    private DB bd;
    private UtilisateurSession session;
    @Autowired
    public void setSession(UtilisateurSession session) {
        this.session = session;
    }
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
        Utilisateur utilisateur = bd.getUtilisateursService().getUtilisateurRepo().findFirstByIdentifiant(this.nomUtilisateurTextField.getText());
        if (utilisateur != null) {
            session = session.connection(utilisateur.getIdentifiant(), utilisateur.getType());
            ApplicationFXEvent applicationFXEvent =
                    ApplicationFXEvent.builder().
                            estConnectionEvent(true).
                            estCreationCompteEvent(false).
                            estCreationCompteEvent(false).
                            utilisateur(session).
                            estDeconnectionEvent(false).
                            build();

            applicationEventPublisher.publishEvent(applicationFXEvent);
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de connexion");
            alert.setContentText("Le nom d'utilisateur n'est pas valide.");
            alert.show();
        }

    }

    @FXML
    void ouvrirFormulaireCreationCompte(ActionEvent event) {
        applicationEventPublisher.publishEvent(ApplicationFXEvent.builder().estNouveauCompteEvent(true).build());
    }
}
