package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView("FiltresVue.fxml")
public class FiltresControleur {

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
        Stage stage = (Stage) confirmerButton.getScene().getWindow();

        stage.close();
    }
}
