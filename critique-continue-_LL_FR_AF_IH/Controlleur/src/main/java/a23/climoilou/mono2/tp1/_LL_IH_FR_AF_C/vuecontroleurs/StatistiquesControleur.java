package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@FxmlView("StatistiqueVue.fxml")
public class StatistiquesControleur {

    @FXML
    private AnchorPane toutesCritiquesPane;

    @FXML
    private Tab toutesCritiquesTab;

    @FXML
    private Tab utilisateursTab;

    @FXML
    private AnchorPane utilsateursPane;

    @FXML
    private AnchorPane vosCritiquesPane;

    @FXML
    private Tab vosCritiquesTab;

    @FXML
    void initialize() {

    }

}
