package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.events.ApplicationFXEvent;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@FxmlView("CompteVue.fxml")
@Component
public class CompteControleur implements Initializable {

    private final ApplicationEventPublisher applicationEventPublisher;
    private ApplicationContext applicationContext;
    @FXML
    private CreationCompteControleur compteControleur;
    @FXML
    private AnchorPane creationVueAnchorPane;
    private Utilisateur utilisateur;
    public CompteControleur(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
    @Autowired
    public void setCompteControleur(CreationCompteControleur compteControleur) {
        this.compteControleur = compteControleur;
    }
    @Autowired
    public void setContext(ApplicationContext context) {
        this.applicationContext = context;
    }
    @FXML
    void deconnectionUtilisateur(ActionEvent event) {
        applicationEventPublisher.publishEvent(ApplicationFXEvent.builder().estConnectionEvent(false).estDeconnectionEvent(true).estCreationCompteEvent(false).estNouveauCompteEvent(false).utilisateur(null).build());
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //init context
        this.utilisateur = applicationContext.getBean(Utilisateur.class);

        DatePicker datePickerDateNaissance = new DatePicker();
        datePickerDateNaissance.setValue(utilisateur.getDateDeNaissance());
        compteControleur.setUpdate(true);

        compteControleur.getTitreVue().setText("Vos information");

        compteControleur.getButtonCreation().setText("Modifier");

        compteControleur.getDateNaissance().setValue(utilisateur.getDateDeNaissance());

        compteControleur.getDropDowntypes().setValue(String.valueOf(utilisateur.getType()));

        compteControleur.getIdentifiant().setText(utilisateur.getIdentifiant());

        compteControleur.getNomUtilisateur().setText(utilisateur.getNom());
    }
}
