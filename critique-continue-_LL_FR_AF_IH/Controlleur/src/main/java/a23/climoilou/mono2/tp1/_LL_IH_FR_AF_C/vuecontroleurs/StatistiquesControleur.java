package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.TreeViewElements.CustomListViewCell;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Items.ListItemI;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Items.UtilisateurItem;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services.DB;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Utilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@FxmlView("StatistiqueVue.fxml")
public class StatistiquesControleur {
    private ConfigurableApplicationContext context;

    private FxWeaver fxWeaver;

    private DB bd;
    @FXML
    private Hyperlink filtreHyperlink;

    @FXML
    private ListView<ListItemI> utilisateurListView;
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
    private CritiqueTabViewControleur critiqueTabViewController;

    @FXML
    void initialize() {
        afficherUtilisateurs();
    }

    private void afficherCritiques(Pane pane) {

    }

    public void afficherFiltres(ActionEvent actionEvent) {
        FxControllerAndView<FiltresControleur, AnchorPane> controllerAndView = fxWeaver.load(FiltresControleur.class);
        Parent root = controllerAndView.getView().get();
        Stage filtresStage = new Stage();
        filtresStage.setResizable(false);
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

    @Autowired
    public void setBd(DB bd) {
        this.bd = bd;
    }

    public void afficherUtilisateurs() {
        List<Utilisateur> utilisateurs = bd.getUtilisateursService().retourLesUtilisateurs();
        ObservableList<String> nomObservableList = FXCollections.observableArrayList();

        for (Utilisateur utilisateur : utilisateurs) {
            nomObservableList.add(utilisateur.getNom());
            utilisateurListView.getItems().add(new UtilisateurItem(
                    utilisateur.getNom(),
                    utilisateur.getIdentifiant(),
                    new ImageView(new Image("iconeUtilisateur.png", 30, 30, false, false))
            ));
        }

        utilisateurListView.setCellFactory(param -> new CustomListViewCell());
    }
}
