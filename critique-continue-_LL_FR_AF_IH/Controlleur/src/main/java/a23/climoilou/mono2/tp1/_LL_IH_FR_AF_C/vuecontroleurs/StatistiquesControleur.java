package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.TreeViewElements.CustomListViewCell;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.TreeViewElements.Items.TreeItemUserType;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Items.ListItemI;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Items.TreeItemI;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Items.UtilisateurItem;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services.DB;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Type;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Utilisateur;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.UtilisateurParType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@FxmlView("StatistiqueVue.fxml")
public class StatistiquesControleur {
    private ConfigurableApplicationContext context;

    private FxWeaver fxWeaver;

    private DB bd;
    @FXML
    private Hyperlink filtreHyperlink;

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


    /**
     * La TableView sert a afficher les utilisateurs selon leurs
     * type.
     *
     * pour se faire il y a 3 traitements a faire dans cette function
     * traiter les neouds parent (types)
     * leurs noeuds enfants (UserType of types)
     * Ainsi que les utilisateurs qui ont les memes types.
     *
     */
    public void afficherUtilisateurs() {

        TreeItem<TreeItemI> root = new TreeItem<TreeItemI>(new TreeItemUserType("Nos utilisateurs par catégorie"));

        this.utilisateurTreeView.setRoot(root);

        List<UtilisateurParType> allTypes = this.bd.getUtilisateurParTypeService().RetourneUtilisateurType();
        Map<Long, TreeItem<TreeItemI>> MapUserType = new HashMap<>();


        allTypes.stream()
                .filter(utp -> utp.getParent() == null)
                .forEach(utp -> {
                    TreeItem<TreeItemI> noeudParent = new TreeItem<TreeItemI>(new TreeItemUserType(utp.getType().toString()));
                    root.getChildren().add(noeudParent);
                    MapUserType.put(utp.getId(), noeudParent);
                });


        for (UtilisateurParType utp : allTypes) {
            TreeItem<TreeItemI> parentEnfantEnfant = MapUserType.get(utp.getId());
            if (utp.getParent() != null) {

                TreeItem<TreeItemI> UserTypeEnfant = new TreeItem<TreeItemI>(new TreeItemUserType(utp.getType().toString()));
                TreeItem<TreeItemI> UserTypeParent = MapUserType.get(utp.getParent().getId());
                if (UserTypeParent != null) {
                    UserTypeParent.getChildren().add(UserTypeEnfant);
                }
                MapUserType.put(utp.getId(), UserTypeEnfant);
            }


            this.bd.getUtilisateursService().retourLesUtilisateurs().stream()
                    .filter(user -> user.getType().equals(utp.getType()))
                    .map(userFiltered -> new TreeItem<TreeItemI>(new TreeItemUserType(userFiltered.getIdentifiant())))
                    .forEach(utilisateurPrType -> {
                        if(parentEnfantEnfant != null ){
                            parentEnfantEnfant.getChildren().add(utilisateurPrType);
                        }
                    });
        }
    }


    /**
     * s'occupe de remplir la Table recursive a partir des utilisateurs.
     * Elle a besoin d'une map avec Un cle Type et une liste d'utilisateur
     *
     * Pour bien suivre la recursion de la table, elle va commencer par les Parent (les types)
     *
     * TrouverOUCreerParent(Type type)
     * Va retourner le parent.
     *
     * Une fois le parent trouver, la "loop" sur les utilisateur va recuperer tous les enfants qui ont le meme Type.
     *
     */
    public void RemplisLaDatbleRecursive(){
        List<Utilisateur> users = this.bd.getUtilisateursService().retourLesUtilisateurs();
        Map<Type, List<Utilisateur>> utilisateurParType = users.stream()
                .collect(Collectors.groupingBy(Utilisateur::getType));

        for (Map.Entry<Type, List<Utilisateur>> entry : utilisateurParType.entrySet()) {
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


    /**
     * Revois un type, verifie dans la BD si l'objet UserParType a un parent.
     * s'il n'est pas null, l'object existe deja et ne sera pas creer comme noeud parent.
     * Sinon, ont creer le noeud comme Parent et ont lui attribut un null au parent. Cela fait en sorte que le prochain
     * noeud avec le meme type ne sera pas le parent.
     * @param type
     * @return Sois un nouveau neouds parent, ou le noeud actuel.
     */
    public UtilisateurParType TrouverOUCreerParent(Type type){
        UtilisateurParType TypeParent = this.bd.getUtilisateurParTypeService().
                getRepo_utilisateur_categorie().
                RetourneLesParentsNullOuVide(type);

        if (TypeParent != null) {
            return TypeParent;
        }

        UtilisateurParType NouveauParent = this.NouvelleInstanceUtilisateurType();
        NouveauParent.setType(type);
        NouveauParent.setParent(null);

        return this.bd.getUtilisateurParTypeService().getRepo_utilisateur_categorie().save(NouveauParent);

    }




}
