package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs;


import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.TreeViewElements.CustomTreeCell;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Developpeur;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Items.Dossier;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Items.InfoSupplementaire;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Items.TreeItemI;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services.DB;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.TypeParticipant;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.calcules.algosrecursifs.CalculeMoyenne;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
@FxmlView("AProposVue.fxml")
@PropertySource("classpath:application.properties")
public class AProposControleur implements Initializable {



    private DB db;

    private CalculeMoyenne calculeMoyenne;

    @Autowired
    public void setDb(DB db) {
        this.db = db;
    }

    @Autowired
    public void setCalculeMoyenne(CalculeMoyenne calculeMoyenne) {
        this.calculeMoyenne = calculeMoyenne;
    }

    @Value("${propriete.nomProduit}")
    private String nomProjet;

    @Value("${propriete.nomEquipe}")
    private String nomEquipe;

    @FXML
    private TreeView<TreeItemI> treeView;


    @FXML
    private Text textEquipe;

    @FXML
    private Text textNomProf;

    @FXML
    private Text textNomProjet;

    @FXML
    private Label moyenne;

    @FXML
    void calculeMoyenne(ActionEvent event) {
        double moy = calculeMoyenne.moyenne(root);
        int compte = calculeMoyenne.compte(root);
        double val =  moy/compte;
        moyenne.setText(String.valueOf(val));

        if (val>=60){
            moyenne.setTextFill(Color.GREEN);
        }else {
            moyenne.setTextFill(Color.RED);
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TreeViewRoot treeViewRoot = new TreeViewRoot();
        //List<Developpeur> developpeurList = db.getDeveloppeurRepo().findAll();
        createTreeView(this.treeView);
    }

    TreeItem<TreeItemI> root;
    public void createTreeView(TreeView<TreeItemI> treeView) {

        List<Developpeur> developpeurList = db.getDevService().getAll();

        root =  new javafx.scene.control.TreeItem<>(new Dossier("À propos Info",folderIcon()),folderIcon());

        List<TreeItem<Developpeur>> developpeurs = developpeurList.stream().map(d-> d.getTypeParticipant() == TypeParticipant.etudiant ? new javafx.scene.control.TreeItem<>(d,etudiantIcon()): new javafx.scene.control.TreeItem<>(d,profIcon())).toList();

        TreeItem dossierPersonne = new javafx.scene.control.TreeItem<>(new Dossier("Personnes impliqué",folderIcon()),folderIcon());
        dossierPersonne.getChildren().addAll(developpeurs);

        TreeItem dossierInfo = new javafx.scene.control.TreeItem<>(new Dossier("Informations projet",folderIcon()),folderIcon());
        dossierInfo.getChildren().addAll(new javafx.scene.control.TreeItem<>(new InfoSupplementaire("Année","2023"),interogationIcon()),
                new javafx.scene.control.TreeItem<>(new InfoSupplementaire("Équipe",nomEquipe),interogationIcon()),
                new javafx.scene.control.TreeItem<>(new InfoSupplementaire("École","Cégep Limoilou"),interogationIcon()),
                new javafx.scene.control.TreeItem<>(new InfoSupplementaire("Application",nomProjet),interogationIcon()),
                new javafx.scene.control.TreeItem<>(new InfoSupplementaire("Cours","Application monoposte 2"),interogationIcon()));

        root.getChildren().addAll(dossierPersonne,dossierInfo);

        treeView.setRoot(root);

        treeView.setCellFactory(c->new CustomTreeCell(this.db));
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
