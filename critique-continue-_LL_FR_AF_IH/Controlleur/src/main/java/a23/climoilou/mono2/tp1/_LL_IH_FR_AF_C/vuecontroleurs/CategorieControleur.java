package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.events.CategorieEvent;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Categorie;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services.CategorieService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.antlr.v4.runtime.tree.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@FxmlView("CategorieVue.fxml")
public class CategorieControleur {
    private static ApplicationEventPublisher applicationEventPublisher;

    private CategorieService categorieService;
    @FXML
    private TreeView<Categorie> arbreCategories;

    @FXML
    private Button boutonConfirmer;

    @Autowired
    public void setCategorieService(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @Autowired
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @FXML
    public void initialize() {
        arbreCategories.setRoot(construireArbreCategories(categorieService.getAllCategories()));
    }

    public TreeItem<Categorie> construireArbreCategories(List<Categorie> categories) {
        Categorie parent = new Categorie();
        parent.setNom("Catégories");
        TreeItem<Categorie> racine = new TreeItem<>(parent);
        racine.setExpanded(true);

        construireArbreCategoriesRec(categories, 0, racine);

        return racine;
    }

    /**
     * Construit un arbre à partir d'une liste de Catégories
     * @param categories liste des catégories
     * @param index index de la liste à itérer
     * @param racine racine de l'arbre
     */
    private void construireArbreCategoriesRec(List<Categorie> categories, int index, TreeItem<Categorie> racine) {
        if (index < categories.size()) {
            Categorie categorie = categories.get(index);
            TreeItem<Categorie> item = new TreeItem<>(categorie);
            if (categorie.getParent() != null) {
                TreeItem<Categorie> itemParent = chercherParent(racine, categorie.getParent());
                if (itemParent != null) {
                    itemParent.getChildren().add(item);
                }
            } else {
                racine.getChildren().add(item);
            }

            construireArbreCategoriesRec(categories, index + 1, racine);
        }
    }

    /**
     * Parcours l'arbre pour trouver le noeud parent de la Categorie
     * @param racine racine de l'arbre
     * @param parent Catégorie à trouver dans l'arbre
     * @return un noeud correspondant au parent
     */
    private TreeItem<Categorie> chercherParent(TreeItem<Categorie> racine, Categorie parent) {
        return chercherParentRec(racine, parent, 0);
    }

    private TreeItem<Categorie> chercherParentRec(TreeItem<Categorie> racine, Categorie parent, int index){
        TreeItem<Categorie> found = null;

        if (index < racine.getChildren().size()) {
            TreeItem<Categorie> item = racine.getChildren().get(index);
            if (item.getValue().equals(parent)) {
                found = item;
            } else {
                found = chercherParentRec(item, parent, 0);
            }
        }

        if (found == null && index + 1 < racine.getChildren().size()) {
            found = chercherParentRec(racine, parent, index + 1);
        }

        return found;
    }

    @FXML
    void confirmerCategorie(ActionEvent event) {
        TreeItem<Categorie> selectedItem = arbreCategories.getSelectionModel().getSelectedItem();
        if (selectedItem != null && selectedItem.getParent() != null) {
            Categorie selectedCategorie = selectedItem.getValue();
            applicationEventPublisher.publishEvent(new CategorieEvent(selectedCategorie, null));
        }
    }

    public void ajouterCategorie(ActionEvent event) {
        TreeItem<Categorie> selectedItem = arbreCategories.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            Categorie aAjouter = new Categorie();
            TextInputDialog textInputDialogNouvelleCategorie = new TextInputDialog();
            textInputDialogNouvelleCategorie.initModality(Modality.APPLICATION_MODAL);
            textInputDialogNouvelleCategorie.setHeaderText("Quelle catégorie souhaitez-vous ajouter dans '" +
                    selectedItem.getValue().getNom() + "'?");
            String nomCategorie = textInputDialogNouvelleCategorie.showAndWait().get();

            if (selectedItem.getParent() != null) aAjouter.setParent(selectedItem.getValue());
            aAjouter.setNom(nomCategorie);
            selectedItem.getChildren().add(new TreeItem<>(aAjouter));
            categorieService.ajouterCategorie(aAjouter);
        } else {
            Alert erreur = new Alert(Alert.AlertType.ERROR,
                    "Vous devez sélectionner la catégorie dans laquelle en ajouter un nouvelle");
            erreur.initModality(Modality.APPLICATION_MODAL);
            erreur.showAndWait();
        }

    }
}
