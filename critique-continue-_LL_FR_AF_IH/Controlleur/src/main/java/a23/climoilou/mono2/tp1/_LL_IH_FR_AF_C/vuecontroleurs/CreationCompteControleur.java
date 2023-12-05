package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.events.ApplicationFXEvent;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services.DB;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Type;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Utilisateur;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.UtilisateurSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import lombok.Getter;
import lombok.Setter;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
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
    private Label messageErreurCompte;


    @Autowired
    public void setSession(UtilisateurSession session) {
        this.session = session;
    }

    @Autowired
    public void setDb(DB db) {
        this.db = db;
    }

    @FXML
    void creerClick(ActionEvent event) {
        Utilisateur utilisateur = null;
        if (dropDowntypes.getValue() != null && db.getUtilisateursService().validationCreationUtilisateur(dateNaissance.getValue(), Type.valueOf(dropDowntypes.getValue()), nomUtilisateur.getText(), identifiant.getText())) {
            utilisateur = db.getUtilisateursService().getUtilisateurRepo().findFirstByIdentifiant(identifiant.getText());
            if (utilisateur != null && isUpdate) {
                utilisateur.setDateDeNaissance(dateNaissance.getValue());
                utilisateur.setNom(nomUtilisateur.getText());
                utilisateur.setType(Type.valueOf(dropDowntypes.getValue()));
                utilisateur.setIdentifiant(session.getSession().getIdentifiantUtilisateur());

                db.getUtilisateursService().updateUtilisateur(utilisateur);
                session.getSession().setPermission(utilisateur.getType());
            }
            else if(utilisateur==null) {
                    utilisateur = Utilisateur.builder().dateDeNaissance(dateNaissance.getValue()).nom(nomUtilisateur.getText()).critiqueList(new ArrayList<>()).type(Type.valueOf(dropDowntypes.getValue())).identifiant(identifiant.getText()).build();
                    db.getUtilisateursService().sauvegarderUtilisateur(utilisateur);
                    session.connection(utilisateur.getIdentifiant(), utilisateur.getType());

                    ApplicationFXEvent applicationFXEvent = ApplicationFXEvent.builder().estCreationCompteEvent(true).utilisateur(session).build();
                    applicationEventPublisher.publishEvent(applicationFXEvent);

            }
            else {
                messageErreurCompte.setText("Erreur identifiant d'utilisateur déjà existant.");
            }
        } else {
            messageErreurCompte.setText("Erreur tous les champs doivent être remplis.");
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

}
