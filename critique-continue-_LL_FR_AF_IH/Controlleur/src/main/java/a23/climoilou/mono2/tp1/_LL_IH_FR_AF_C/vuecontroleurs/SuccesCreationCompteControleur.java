package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.events.ApplicationFXEvent;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Utilisateur;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.UtilisateurSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import lombok.Builder;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controleur de la vue succès
 */
@FxmlView("SuccesCreationCompteVue.fxml")
@Component
public class SuccesCreationCompteControleur implements Initializable {

    private final ApplicationEventPublisher applicationEventPublisher;

    private UtilisateurSession session;

    public SuccesCreationCompteControleur(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @FXML
    private Label nomUtilisateur;

    /**
     * Lance un évenement de type ApplicationFxEvent qui sera attrapé dans applicationfx
     * @param event
     */
    @FXML
    void connect(ActionEvent event) {
        ApplicationFXEvent applicationFXEvent = ApplicationFXEvent.builder().estConnectionEvent(true).utilisateur(session.getUtilisateur()).build();
        applicationEventPublisher.publishEvent(applicationFXEvent);
    }
    @Autowired
    public void setSession(UtilisateurSession session) {
        this.session = session;

        //affiche le nom de l'utilisateur
        if (session.getUtilisateur()!=null){
            nomUtilisateur.setText(session.getUtilisateur().getNom());}
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //affiche le nom de l'utilisateur
        if (session.getUtilisateur()!=null){
            nomUtilisateur.setText(session.getUtilisateur().getNom());
        }
    }
}
