package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.events.CreationCompteEvent;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services.DB;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Type;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@FxmlView("CreationCompteVue.fxml")
@Component
public class CreationCompteControleur {

    private DB bd;

    @Autowired
    public void setBd(DB bd) {
        this.bd = bd;
    }

    private final ApplicationEventPublisher applicationEventPublisher;

    public CreationCompteControleur(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
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
        System.out.println(dateNaissance.getValue());
        System.out.println(dropDowntypes.getValue());
        System.out.println(nomUtilisateur.getText());
        System.out.println(identifiant.getText());
        if(dateNaissance.getValue()!=null &&  dropDowntypes.getValue() != null && nomUtilisateur.getText()!=null && identifiant.getText()!=null){
            utilisateur = bd.getUtilisateursService().validationCreationService(dateNaissance,Type.valueOf(dropDowntypes.getValue()),nomUtilisateur,identifiant);
            if(utilisateur!=null ){
                bd.getUtilisateursService().sauvegarderUtilisateur(utilisateur);
                applicationEventPublisher.publishEvent(new CreationCompteEvent(this,new Utilisateur()));
            }
            else {
                messageErreur.setText("Erreur nom d'utilisateur déjà existant.");
            }
        }
        else {
            messageErreur.setText("Erreur tous les champs doivent être remplis.");
        }
    }

    private void ajoutDesChoixAuChoiceBox() {
        List<String> lesTypePaiments = new ArrayList<>();
        for (Type paimentType :Type.values()) {
            lesTypePaiments.add(String.valueOf(paimentType));
        }
        dropDowntypes.getItems().addAll(lesTypePaiments);
    }

    @FXML
    void initialize(){
        ajoutDesChoixAuChoiceBox();
    }

}
