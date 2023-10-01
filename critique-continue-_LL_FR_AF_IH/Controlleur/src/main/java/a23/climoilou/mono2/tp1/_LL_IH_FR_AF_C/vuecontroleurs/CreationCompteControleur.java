package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.events.ApplicationFXEvent;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services.DB;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Type;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Utilisateur;
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

    private DB bd;

    private ApplicationContext applicationContext;
    @Autowired
    public void setBd(DB bd) {
        this.bd = bd;
    }

    private boolean isUpdate;

    @FXML
    private Text titreVue;

    @FXML
    private Button buttonCreation;

    private final ApplicationEventPublisher applicationEventPublisher;

    public CreationCompteControleur(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.isUpdate =false;
    }
    @Autowired
    public void setContext(ApplicationContext context) {
        this.applicationContext = context;
    }

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
        if (dropDowntypes.getValue() != null && bd.getUtilisateursService().validationCreationUtilisateur(dateNaissance.getValue(), Type.valueOf(dropDowntypes.getValue()), nomUtilisateur.getText(), identifiant.getText()) != null) {
            utilisateur = bd.getUtilisateursService().validationCreationUtilisateur(dateNaissance.getValue(), Type.valueOf(dropDowntypes.getValue()), nomUtilisateur.getText(), identifiant.getText());
            if (utilisateur != null) {
                //save utilisateur ou update
                bd.getUtilisateursService().sauvegarderUtilisateur(utilisateur);
                //à ajouter update
                if(isUpdate){
                    modifierContextUtilisateur(utilisateur);
                }
                else {
                    //lancement de l'événement de creation
                    ApplicationFXEvent applicationFXEvent = ApplicationFXEvent.builder().estCreationCompteEvent(true).utilisateur(utilisateur).build();
                    applicationEventPublisher.publishEvent(applicationFXEvent);
                }
            } else {
                messageErreur.setText("Erreur nom d'utilisateur déjà existant.");
            }
        } else {
            messageErreur.setText("Erreur tous les champs doivent être remplis.");
        }
    }

    private void modifierContextUtilisateur(Utilisateur utilisateur){
        Utilisateur utilisateurBean = applicationContext.getBean(Utilisateur.class);
        utilisateurBean.setIdentifiant(utilisateur.getIdentifiant());
        utilisateurBean.setDateDeNaissance(utilisateur.getDateDeNaissance());
        utilisateurBean.setCritiqueList(utilisateur.getCritiqueList());
        utilisateurBean.setNom(utilisateur.getNom());
        utilisateurBean.setType(utilisateur.getType());
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
