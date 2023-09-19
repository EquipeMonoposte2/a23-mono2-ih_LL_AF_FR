package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.events.CreationCompteEvent;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Type;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@FxmlView("CreationCompteVue.fxml")
@Component
public class CreationCompteControleur {

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
    void creerClick(ActionEvent event) {
        //validation et creation d'utilisateur

        if(true){
            applicationEventPublisher.publishEvent(new CreationCompteEvent(this,new Utilisateur()));
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
