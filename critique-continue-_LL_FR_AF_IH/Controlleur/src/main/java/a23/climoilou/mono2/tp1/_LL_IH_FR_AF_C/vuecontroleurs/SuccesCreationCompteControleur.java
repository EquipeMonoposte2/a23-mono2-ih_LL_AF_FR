package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.events.ConnectionEvent;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * Controleur de la vue succès
 */
@FxmlView("SuccesCreationCompteVue.fxml")
@Component
public class SuccesCreationCompteControleur {

    private final ApplicationEventPublisher applicationEventPublisher;

    private Utilisateur utilisateur;
    public SuccesCreationCompteControleur(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @FXML
    private Label nomUtilisateur;

    /**
     * Lance un évenement de type ConnectionEvent qui sera attrapé dans applicationfx
     * @param event
     */
    @FXML
    void connect(ActionEvent event) {
        //conditions

        //on reprend l'utilisateur
        if(true){

            applicationEventPublisher.publishEvent(new ConnectionEvent(this,"lancer de succes creation compte controleur",this.utilisateur));
        }
    }

    @FXML
    void initialize(){
        //String nom = this.utilisateur.getNom();
        //nomUtilisateur.setText(utilisateur.getNom());
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
