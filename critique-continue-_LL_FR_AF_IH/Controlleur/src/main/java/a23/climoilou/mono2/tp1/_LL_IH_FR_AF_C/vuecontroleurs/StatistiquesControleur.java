package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@FxmlView("StatistiqueVue.fxml")
public class StatistiquesControleur {
    private ConfigurableApplicationContext context;


    @FXML
    private Hyperlink filtreHyperlink;

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


    public void afficherFiltres(ActionEvent actionEvent) {
        FxWeaver fxWeaver = context.getBean(FxWeaver.class);
        //<FiltresControleur, AnchorPane> controllerAndView = fxWeaver.load(FiltresControleur.class);

    }
}
