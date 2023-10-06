package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.events.ApplicationFXEvent;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services.DB;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Utilisateur;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.UtilisateurSession;
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

    private DB bd;

    private UtilisateurSession session;
    @FXML
    private CreationCompteControleur modificationCompteControleur;
    @FXML
    private AnchorPane creationVueAnchorPane;
    public CompteControleur(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
    @Autowired
    public void setSession(UtilisateurSession session) {
        this.session = session;
    }

    @Autowired
    public void setBd(DB bd) {
        this.bd = bd;
    }

    @Autowired
    public void setCompteControleur(CreationCompteControleur modificationCompteControleur) {
        this.modificationCompteControleur = modificationCompteControleur;
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
        Utilisateur utilisateur = bd.getUtilisateursService()
                .getUtilisateurRepo().
                findFirstByIdentifiant(session.getIdentifiantUtilisateur());

        DatePicker datePickerDateNaissance = new DatePicker();
        datePickerDateNaissance.setValue(utilisateur.getDateDeNaissance());

        //pour assurer la modification et non la création
        modificationCompteControleur.getIdentifiant().setEditable(false);

        modificationCompteControleur.setUpdate(true);

        modificationCompteControleur.getTitreVue().setText("Vos information");

        modificationCompteControleur.getButtonCreation().setText("Modifier");

        modificationCompteControleur.getDateNaissance().setValue(utilisateur.getDateDeNaissance());

        modificationCompteControleur.getDropDowntypes().setValue(String.valueOf(utilisateur.getType()));

        modificationCompteControleur.getIdentifiant().setText(utilisateur.getIdentifiant());

        modificationCompteControleur.getNomUtilisateur().setText(utilisateur.getNom());
    }
}
