package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.events.ApplicationFXEvent;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services.DB;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Type;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Utilisateur;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.UtilisateurSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import lombok.Getter;
import lombok.Setter;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Controleur de création de compte
 */
@FxmlView("CreationCompteVue.fxml")
@Component
@Getter
@Setter
public class CreationCompteControleur {


    private UtilisateurSession session;

    private DB db;

    private boolean isUpdate;

    private final ApplicationEventPublisher applicationEventPublisher;

    public CreationCompteControleur(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.isUpdate =false;
    }

    @FXML
    private Text titreVue;

    @FXML
    private Button buttonCreation;

    @FXML
    private DatePicker dateNaissance;

    @FXML
    private ChoiceBox<String> dropDowntypes;

    @FXML
    private TextField nomUtilisateur;

    @FXML
    private TextField identifiant;

    @FXML
    private Text messageErreur;

    @FXML
    void creerClick(ActionEvent event) {
        //validation et creation d'utilisateur
        Utilisateur utilisateur = null;
        if (dropDowntypes.getValue() != null && db.getUtilisateursService().validationCreationUtilisateur(dateNaissance.getValue(), Type.valueOf(dropDowntypes.getValue()), nomUtilisateur.getText(), identifiant.getText())) {
            utilisateur = db.getUtilisateursService().getUtilisateurRepo().findFirstByIdentifiant(identifiant.getText());
            if (utilisateur != null && isUpdate) {
                //utilisateur update
                session.modificationSession(Utilisateur.builder().dateDeNaissance(dateNaissance.getValue()).nom(nomUtilisateur.getText()).critiqueList(utilisateur.getCritiqueList()).type(Type.valueOf(dropDowntypes.getValue())).identifiant(identifiant.getText()).build());
                System.out.println("id = "+identifiant.getText());
                db.getUtilisateursService().updateUtilisateur(Utilisateur.builder().dateDeNaissance(dateNaissance.getValue()).nom(nomUtilisateur.getText()).critiqueList(utilisateur.getCritiqueList()).type(Type.valueOf(dropDowntypes.getValue())).identifiant(identifiant.getText()).build());
            }
            else if(utilisateur==null) {
                    //sauvegarder utilisateur et instancier utilisateur
                    utilisateur = Utilisateur.builder().dateDeNaissance(dateNaissance.getValue()).nom(nomUtilisateur.getText()).critiqueList(new ArrayList<>()).type(Type.valueOf(dropDowntypes.getValue())).identifiant(identifiant.getText()).build();
                    db.getUtilisateursService().sauvegarderUtilisateur(utilisateur);
                    session.connection(utilisateur.getIdentifiant());
                    System.out.println(session.getUtilisateur().toString());
                    //lancement de l'événement de creation
                    ApplicationFXEvent applicationFXEvent = ApplicationFXEvent.builder().estCreationCompteEvent(true).utilisateur(utilisateur).build();
                    applicationEventPublisher.publishEvent(applicationFXEvent);

            }
            else {
                messageErreur.setText("Erreur identifiant d'utilisateur déjà existant.");
            }
        } else {
            messageErreur.setText("Erreur tous les champs doivent être remplis.");
        }
    }

    private void ajoutDesChoixAuChoiceBox() {
        List<String> lesTypePaiments = new ArrayList<>();
        for (Type paimentType : Type.values()) {
            lesTypePaiments.add(String.valueOf(paimentType));
        }
        dropDowntypes.getItems().addAll(lesTypePaiments);
    }

    @FXML
    void initialize() {
        ajoutDesChoixAuChoiceBox();
    }

    @Autowired
    public void setSession(UtilisateurSession session) {
        this.session = session;
    }

    @Autowired
    public void setDb(DB db) {
        this.db = db;
    }
}
