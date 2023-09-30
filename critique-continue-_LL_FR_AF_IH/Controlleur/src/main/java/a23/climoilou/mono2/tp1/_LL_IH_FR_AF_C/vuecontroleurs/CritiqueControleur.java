package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.events.SoumettreCritiqueEvent;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Critique;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@FxmlView("CritiqueVue.fxml")
@Component
public class CritiqueControleur
{

    private final ApplicationEventPublisher applicationEventPublisher;
    private Critique critique;

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

    @Autowired
    public CritiqueControleur(ApplicationEventPublisher applicationEventPublisher)
    {
        this.applicationEventPublisher = applicationEventPublisher;
        critique = new Critique();
        // Setup de la liste

        // Setup des choice box

    }

    @FXML
    void ajouterJeu(ActionEvent event) {
    }

    @FXML
    void soumettreCritique(ActionEvent event) {
        applicationEventPublisher.publishEvent(new SoumettreCritiqueEvent(this));
    }


}
