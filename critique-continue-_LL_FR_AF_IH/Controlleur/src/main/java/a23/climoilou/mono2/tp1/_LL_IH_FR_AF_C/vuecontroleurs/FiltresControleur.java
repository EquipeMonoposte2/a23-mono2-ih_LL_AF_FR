package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.events.FiltresEvent;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Type;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@FxmlView("FiltresVue.fxml")
public class FiltresControleur {

    private ApplicationEventPublisher applicationEventPublisher;


    @FXML
    private CheckBox amateurCheckbox;

    @FXML
    private Button confirmerButton;

    @FXML
    private DatePicker debutDatePicker;

    @FXML
    private CheckBox expertChecbox;

    @FXML
    private DatePicker finDatePicker;

    @FXML
    private CheckBox influenceurCheckbox;

    public void confirmerFiltres(ActionEvent actionEvent) {
        Set<Type> typesUtilisateurs = new HashSet<>();
        if(amateurCheckbox.isSelected()){
            typesUtilisateurs.add(Type.AMATEUR);
        }
        if(influenceurCheckbox.isSelected()){
            typesUtilisateurs.add(Type.Influencer);
        }
        if(expertChecbox.isSelected()){
            typesUtilisateurs.add(Type.Expert);
        }
        FiltresEvent event = FiltresEvent.builder()
                .typesUtilisateurs(typesUtilisateurs)
                .dateDebut(debutDatePicker.getValue())
                .dateFin(finDatePicker.getValue()).build();
        applicationEventPublisher.publishEvent(event);
    }

    @Autowired
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
