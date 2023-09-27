package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
@FxmlView("StatistiqueVue.fxml")
public class StatistiquesControleur{
    private ConfigurableApplicationContext context;

    private FxWeaver fxWeaver;


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
        FxControllerAndView<FiltresControleur, AnchorPane> controllerAndView = fxWeaver.load(FiltresControleur.class);
        Parent root = controllerAndView.getView().get();
        Stage filtresStage = new Stage();
        filtresStage.setResizable(false);
        filtresStage.initModality(Modality.APPLICATION_MODAL);
        filtresStage.setScene(new Scene(root));
        filtresStage.showAndWait();
    }

    @Autowired
    public void setContext(ConfigurableApplicationContext context) {
        this.context = context;
    }

    @Autowired
    public void setFxWeaver(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
    }
}
