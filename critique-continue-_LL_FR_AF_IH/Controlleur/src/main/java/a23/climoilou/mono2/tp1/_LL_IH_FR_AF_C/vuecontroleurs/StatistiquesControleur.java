package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services.DB;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Type;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Utilisateur;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.UtilisateurParType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
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

    private UtilisateurParType utilisateurParType;

    @Autowired
    public void setUtilisateurParType(UtilisateurParType utilisateurParType) {
        this.utilisateurParType = utilisateurParType;
    }

    private DB bd;
    @FXML
    private Hyperlink filtreHyperlink;

//    @FXML
//    private ListView<String> utilisateurListView;
    @FXML
    private TreeView<String> utilisateurTreeView;

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
        this.afficherUtilisateurs();
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

//        System.out.println(this.bd.getUtilisateursService().getUtilisateurRepo().findAll());

        this.bd.getUtilisateurParTypeService().AssocierLutilisateurAuType();

        while(this.bd.getUtilisateurParTypeService().RetourneMesUtilisateurPerType().iterator().hasNext()){
            System.out.println(this.bd.getUtilisateurParTypeService().RetourneMesUtilisateurPerType().iterator().next());
        }









//        List<Utilisateur> utilisateurs = bd.getUtilisateursService().retourLesUtilisateurs();
//        ObservableList<String> nomObservableList = FXCollections.observableArrayList();
//
//        for (Utilisateur utilisateur : utilisateurs) {
//            nomObservableList.add(utilisateur.getNom());
//        }
//
//        utilisateurListView.setItems(nomObservableList);
    }
}
