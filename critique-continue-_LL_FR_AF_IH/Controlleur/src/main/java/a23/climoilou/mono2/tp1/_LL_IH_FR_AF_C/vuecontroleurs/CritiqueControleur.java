package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.events.SoumettreCritiqueEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@FxmlView("CritiqueVue.fxml")
@Component
public class CritiqueControleur
{

    private final ApplicationEventPublisher applicationEventPublisher;

    @FXML
    private Button ajouterJeuButton;

    @FXML
    private ChoiceBox<?> choixJeuCritique;

    @FXML
    private ChoiceBox<?> choixPoidsCritique;

    @FXML
    private DatePicker dateCritique;

    @FXML
    private ListView<?> mesCritiquesAffiche;

    @FXML
    private CheckBox neutreCheckbox;

    @FXML
    private Text nomJeuCritique;

    @FXML
    private ListView<?> nouvelleCritique;

    @FXML
    private Button soumettreCritiqueButton;

    public CritiqueControleur(ApplicationEventPublisher applicationEventPublisher)
    {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @FXML
    void ajouterJeu(ActionEvent event) {
        System.out.println("Test du fonction du critique controleur");
    }

    @FXML
    void soumettreCritique(ActionEvent event) {
        applicationEventPublisher.publishEvent(new SoumettreCritiqueEvent(this));
    }


}
