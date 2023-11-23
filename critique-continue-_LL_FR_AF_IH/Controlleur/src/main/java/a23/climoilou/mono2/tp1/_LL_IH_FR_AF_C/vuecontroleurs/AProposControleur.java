package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.TreeViewElements.*;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.TreeViewElements.Items.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("AProposVue.fxml")
@PropertySource("classpath:application.properties")
public class AProposControleur implements Initializable {




    @Value("${propriete.nomProfesseur}")
    private String nomProf;

    @Value("${propriete.nomProduit}")
    private String nomProjet;

    @Value("${propriete.antoine}")
    private String antoine;

    @Value("${propriete.ibrahim}")
    private String ibrahim;

    @Value("${propriete.lawrence}")
    private String lawrence;

    @Value("${propriete.felix}")
    private String felix;


    @FXML
    private TreeView<TreeItemI> treeView;


    @FXML
    private Text textEquipe;

    @FXML
    private Text textNomProf;

    @FXML
    private Text textNomProjet;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TreeViewRoot treeViewRoot = new TreeViewRoot();
        createTreeView(this.treeView);
    }

    TreeItem<TreeItemI> root;
    public void createTreeView(TreeView<TreeItemI> treeView) {

        root =  new javafx.scene.control.TreeItem<>(new Dossier("À propos Info",folderIcon()),folderIcon());

        TreeItem dossierPersonne = new javafx.scene.control.TreeItem<>(new Dossier("Personne impliqué",folderIcon()),folderIcon());
        dossierPersonne.getChildren().addAll(new javafx.scene.control.TreeItem<>(new PersonneParticipant(ibrahim, TypeParticipant.etudiant,"",etudiantIcon()),etudiantIcon()),
                new javafx.scene.control.TreeItem<>(new PersonneParticipant(felix, TypeParticipant.etudiant,"",etudiantIcon()),etudiantIcon()),
                new javafx.scene.control.TreeItem<>(new PersonneParticipant(antoine,TypeParticipant.etudiant,"",etudiantIcon()),etudiantIcon()),
                new javafx.scene.control.TreeItem<>(new PersonneParticipant(lawrence,TypeParticipant.etudiant,"",etudiantIcon()),etudiantIcon()),
                new javafx.scene.control.TreeItem<>(new PersonneParticipant(nomProf,TypeParticipant.professeur,"",profIcon()),profIcon()));

        TreeItem dossierInfo = new javafx.scene.control.TreeItem<>(new Dossier("Informations projet",folderIcon()),folderIcon());
        dossierInfo.getChildren().addAll(new javafx.scene.control.TreeItem<>(new InfoSupplementaire("Année","2023"),interogationIcon()),
                new javafx.scene.control.TreeItem<>(new InfoSupplementaire("Équipe","4"),interogationIcon()),
                new javafx.scene.control.TreeItem<>(new InfoSupplementaire("École","Cégep Limoilou"),interogationIcon()),
                new javafx.scene.control.TreeItem<>(new InfoSupplementaire("Application",nomProjet),interogationIcon()),
                new javafx.scene.control.TreeItem<>(new InfoSupplementaire("Cours","Application monoposte 2"),interogationIcon()));

        root.getChildren().addAll(dossierPersonne,dossierInfo);

        treeView.setRoot(root);

        treeView.setCellFactory(c->new CustomTreeCell());
    }
    public ImageView folderIcon(){
        return new ImageView(new Image(AProposControleur.class.getResourceAsStream("images/dossier.png"), 30, 30, true, true));
    }
    public ImageView profIcon(){
        return new ImageView(new Image(AProposControleur.class.getResourceAsStream("images/prof.png"), 30, 30, true, true));
    }

    public ImageView etudiantIcon(){
        return new ImageView(new Image(AProposControleur.class.getResourceAsStream("images/etudiant.png"), 30, 30, true, true));
    }

    public ImageView interogationIcon(){
        return new ImageView(new Image(AProposControleur.class.getResourceAsStream("images/inte.png"), 30, 30, true, true));

    }

}
