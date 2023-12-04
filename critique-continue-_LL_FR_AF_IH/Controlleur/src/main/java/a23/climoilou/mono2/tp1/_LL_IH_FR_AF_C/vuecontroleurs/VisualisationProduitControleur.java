package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.Service_ibrahim.FlashingSearchService;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.Service_ibrahim.ImageMoverService;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.TreeViewElements.CustomTreeCellProduit;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.TreeViewElements.Items.ProduitAlphabeltical;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.TreeViewElements.Items.ProduitItemI;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.TreeViewElements.Items.ProduitTreeItem;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Produit;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services.DB;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@FxmlView("vueProduit.fxml")
public class VisualisationProduitControleur {

    private DB bd;

    private ImageMoverService imageMoverService;

    private FlashingSearchService flashingSearchService;

    private ApplicationContext context;

    @Autowired
    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    @Autowired
    public void setFlashingSearchService(FlashingSearchService flashingSearchService) {
        this.flashingSearchService = flashingSearchService;
    }

    @Autowired
    public void setImageMoverService(ImageMoverService imageMoverService) {
        this.imageMoverService = imageMoverService;
    }

    @Autowired
    public void setBd(DB bd) {
        this.bd = bd;
    }


    @FXML
    private TreeView<ProduitItemI> tree_view_visualisation_prod;

    @FXML
    private ImageView image_film;

    @FXML
    private Text id_titre_film;

    @FXML
    private TextArea description_film;

    @FXML
    private Text date_movie_id;

    @FXML
    private TextField text_input_recherche;

    @FXML
    private Label label_recherche;

    @FXML
    private Button btn_lancer_recherche;



    @FXML
    public void initialize() {

        if(imageMoverService.getState() == Worker.State.READY){
            imageMoverService.start();
        }

        if(flashingSearchService.getState() == Worker.State.READY){
            flashingSearchService.start();
        }



        creeTreeView(this.tree_view_visualisation_prod);
    }

    /**
     * Lance la Treeview
     */
    public void lancerRecherche(){
        if(this.text_input_recherche.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Vous devez écrire quelque chose dans la recherche svp.");

            alert.showAndWait();
        }else {
            rechercheRec(this.tree_view_visualisation_prod.getRoot(), this.text_input_recherche.getText());
        }
    }


    public void creeTreeView(TreeView<ProduitItemI> treeView){

        TreeItem<ProduitItemI> root = new TreeItem<>(new ProduitAlphabeltical("Nos jeux"), new ImageView(new Image("file:images/controller.png",  30, 30, true, true)));

        treeView.setRoot(root);
        loadTreeItem(bd.getProduitsService().getProduitRepository().RetourneProduitTrier(), root);

    }

    /**
     * Methode facade pour la recursion
     * @param listeDeProduitSansRoot La liste de produit dans le premier item (root)
     * @param root le premier item (root)
     */
    public void loadTreeItem(List<Produit> listeDeProduitSansRoot, TreeItem<ProduitItemI> root){
        if(listeDeProduitSansRoot.isEmpty()){
            System.out.println("its empty brother");

        }

        loadPureRec(listeDeProduitSansRoot, root, 0);

    }

    /**
     * Methode recursive qui remplis le treeView.
     * Ne retourne rien.
     * @param liste La Liste de produit
     * @param root Le premier noeud de l'arbre
     * @param index index de la liste a parcourir
     */
    private void loadPureRec(List<Produit> liste, TreeItem<ProduitItemI> root, int index){
        if(index >= liste.size()){
            this.tree_view_visualisation_prod.setCellFactory(x->new CustomTreeCellProduit());

            this.tree_view_visualisation_prod.setOnMouseClicked(ae -> {
                TreeItem<ProduitItemI> selected =  this.tree_view_visualisation_prod.getSelectionModel().getSelectedItem();
                if(selected != null){
                    if (selected.getValue() instanceof ProduitTreeItem){
                        afficheProduitSlected(((ProduitTreeItem) selected.getValue()).getInnerProduitTree());
                    }
                }
            });
            return;
        }

        Produit produitCourant = liste.get(index);
        char afficheLettre =  produitCourant.getNom().charAt(0);
        TreeItem<ProduitItemI> lettreNode = CreeOuTrouveProduitAlphabetical(root, afficheLettre);

        lettreNode.getChildren().add(new TreeItem<>(new ProduitTreeItem(produitCourant), new ImageView(new Image("file:images/controller.png",  30, 30, true, true))));


        loadPureRec(liste, root, index +1);


    }


    /**
     * Permet de differencier le parent et l'enfant.
     *
     * @param root le premier Element de l'arbre pour savoir ou commencer
     * @param letter La lettre a verifier
     * @return Sois l'item recu ou  un nouveau neoud parent alphabetical
     */
    private TreeItem<ProduitItemI> CreeOuTrouveProduitAlphabetical(TreeItem<ProduitItemI> root, char letter) {
        TreeItem<ProduitItemI> EnfantAlphabet = null;

        for (TreeItem<ProduitItemI> child : root.getChildren()) {
            if (child.getValue() instanceof ProduitAlphabeltical && ((ProduitAlphabeltical) child.getValue()).getLettre() == letter) {
                EnfantAlphabet = child;
                break;
            }
        }

        if (EnfantAlphabet == null) {
            EnfantAlphabet = new TreeItem<>(new ProduitAlphabeltical(letter), new ImageView(new Image("file:/images/folder.jpg", 30, 30, true, true)));
            root.getChildren().add(EnfantAlphabet);
        }

        return EnfantAlphabet;
    }


    /**
     * Methode qui recoit un produit et l'affiche en UI
     * @param produitChoisi Le produit a afficher
     */
    public void afficheProduitSlected(Produit produitChoisi) {
        String path = "file:images/" + produitChoisi.getImage().toString();
        Image image = new Image(path);
        image_film.setImage(image);
        id_titre_film.setText(produitChoisi.getNom());
        description_film.setText(produitChoisi.getDescription());
        date_movie_id.setText(produitChoisi.getDateDeSortie().toString());
        imageMoverService.setCurrentX(image_film.getLayoutX());
        imageMoverService.setXMax(image_film.getLayoutBounds().getMaxX());
        imageMoverService.setXMin(image_film.getLayoutBounds().getMinX());


        imageMoverService.valueProperty().addListener((a, o, n) -> {
            image_film.setLayoutX(n);
        });
    }


    /**
     * Fonction de recherche recursive dans l'arbre.
     * @param produitDansLarbre au debut c'est le root, mais au cours de la recherche devient l'element actuel
     * @param nomRecherche Le nom ecris dans le tex input
     * @return soit l'item ou null (si rien).
     */
    public TreeItem<ProduitItemI> rechercheRec(TreeItem<ProduitItemI> produitDansLarbre, String nomRecherche){

        this.flashingSearchService.valueProperty().addListener((a,o,n) -> {
            if(n != null){

                String cssColor = String.format("#%02X%02X%02X",
                        (int)(n.getRed() * 255),
                        (int)(n.getGreen() * 255),
                        (int)(n.getBlue() * 255));

                this.btn_lancer_recherche.setStyle("-fx-background-color: " + cssColor + ";");
                this.text_input_recherche.setStyle("-fx-background-color: " + cssColor + ";");
                this.image_film.setStyle("-fx-border-color: " + cssColor + ";");
                this.label_recherche.setStyle("-fx-background-color: " + cssColor + ";");

            }
        });

            if (produitDansLarbre.getValue() instanceof ProduitAlphabeltical produitAlphabeltical){

                if(produitAlphabeltical.getLettre() == nomRecherche.charAt(0)) {

                    for (TreeItem<ProduitItemI> reponse : produitDansLarbre.getChildren()){

                        if(reponse.getValue() instanceof ProduitTreeItem produitTreeItem){

                            if(produitTreeItem.getInnerProduitTree().getNom().equals(nomRecherche)){
                                this.afficheProduitSlected(produitTreeItem.getInnerProduitTree());

                            }else {
                                Produit produitNonTrouver = context.getBean(Produit.class);
                                produitNonTrouver.setImage("file:images/404.png");
                                produitNonTrouver.setNom("oh non ce produit n'existe pas");
                                System.out.println(produitNonTrouver);
                                this.afficheProduitSlected(produitNonTrouver);
                            }
                        }
                    }

                }

                for (TreeItem<ProduitItemI> child : produitDansLarbre.getChildren()) {
                    TreeItem<ProduitItemI> foundItem = rechercheRec(child, nomRecherche);
                    if (foundItem != null) {
                        return foundItem;
                    }
                }
            }


        return null;

    }




}
