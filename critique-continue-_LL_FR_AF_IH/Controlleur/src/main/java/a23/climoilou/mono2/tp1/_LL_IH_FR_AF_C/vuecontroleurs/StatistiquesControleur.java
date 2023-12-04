package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.TreeViewElements.Items.TreeItemI;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.TreeViewElements.Items.TreeItemUserType;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@FxmlView("StatistiqueVue.fxml")
public class StatistiquesControleur{
    private ConfigurableApplicationContext context;

    private FxWeaver fxWeaver;

//    private UtilisateurParType utilisateurParType;
//
//    @Autowired
//    public void setUtilisateurParType(UtilisateurParType utilisateurParType) {
//        this.utilisateurParType = utilisateurParType;
//    }

    private DB bd;
    @FXML
    private Hyperlink filtreHyperlink;

//    @FXML
//    private ListView<String> utilisateurListView;
    @FXML
    private TreeView<TreeItemI> utilisateurTreeView;

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
        this.RemplisLaDatbleRecursive();
    }

    private void afficherCritiques(Pane pane) {

    }

    public UtilisateurParType NouvelleInstanceUtilisateurType(){
        return this.context.getBean(UtilisateurParType.class);
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

        TreeItem<TreeItemI> root = new TreeItem<>(new TreeItemUserType("Nos utilisateurs par catégorie"));

        this.utilisateurTreeView.setRoot(root);

        List<UtilisateurParType> allTypes = this.bd.getUtilisateurParTypeService().RetourneUtilisateurType();
        Map<Long, TreeItem<TreeItemI>> MapUserType = new HashMap<>();

        for (UtilisateurParType utp : allTypes) {
            if (utp.getParent() == null) {
                TreeItem<TreeItemI> node = new TreeItem<>(new TreeItemUserType(utp.getType().toString()));
                root.getChildren().add(node);
                MapUserType.put(utp.getId(), node);
            }
        }



        for (UtilisateurParType utp : allTypes) {
            TreeItem<TreeItemI> parentEnfantEnfant = MapUserType.get(utp.getId());
            if (utp.getParent() != null) {

                TreeItem<TreeItemI> UserTypeEnfant = new TreeItem<>(new TreeItemUserType(utp.getType().toString()));
                for (Utilisateur util: this.bd.getUtilisateursService().retourLesUtilisateurs()) {
                    if (util.getType().equals(utp.getParent().getType())){
                        System.out.println(util);
                    }

                }
                TreeItem<TreeItemI> UserTypeParent = MapUserType.get(utp.getParent().getId());
                if (UserTypeParent != null) {
                    UserTypeParent.getChildren().add(UserTypeEnfant);
                }
                MapUserType.put(utp.getId(), UserTypeEnfant);
            }


            for (Utilisateur user : this.bd.getUtilisateursService().retourLesUtilisateurs()) {
                if (user.getType().equals(utp.getType())) {
                    TreeItem<TreeItemI> userNode = new TreeItem<>(new TreeItemUserType(user.getIdentifiant())); // Adjust as needed for user representation
                    if (parentEnfantEnfant != null) {
                        parentEnfantEnfant.getChildren().add(userNode);
                    }
                }
            }
        }

        }


    public void RemplisLaDatbleRecursive(){
        List<Utilisateur> users = this.bd.getUtilisateursService().retourLesUtilisateurs();
        Map<Type, List<Utilisateur>> usersByType = users.stream()
                .collect(Collectors.groupingBy(Utilisateur::getType));

        for (Map.Entry<Type, List<Utilisateur>> entry : usersByType.entrySet()) {
            Type type = entry.getKey();
            List<Utilisateur> utilisateurParTypes = entry.getValue();

            UtilisateurParType parentType = TrouverOUCreerParent(type);

            for (Utilisateur user : utilisateurParTypes) {

                UtilisateurParType child = this.NouvelleInstanceUtilisateurType();
                child.setType(user.getType());
                child.setParent(parentType);

                parentType.getChildren().add(child);
            }

            this.bd.getUtilisateurParTypeService().getRepo_utilisateur_categorie().save(parentType);
        }
    }


    public UtilisateurParType TrouverOUCreerParent(Type type){
        UtilisateurParType TypeParent = this.bd.getUtilisateurParTypeService().getRepo_utilisateur_categorie().RetourneLesIdParentsVide(type);
        if (TypeParent != null) {
            return TypeParent;
        }

        UtilisateurParType NouveauParent = this.NouvelleInstanceUtilisateurType();
        NouveauParent.setType(type);
        NouveauParent.setParent(null);

        return this.bd.getUtilisateurParTypeService().getRepo_utilisateur_categorie().save(NouveauParent);

    }


}
