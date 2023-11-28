package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.Service_ibrahim.ImageMoverService;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.TreeViewElements.Items.ProduitItemI;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Produit;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services.DB;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
@FxmlView("vueProduit.fxml")
@PropertySource("classpath:application.properties")
public class VisualisationProduitControleur {

    private DB bd;

    private ImageMoverService imageMoverService;

    @Autowired
    public void setImageMoverService(ImageMoverService imageMoverService) {
        this.imageMoverService = imageMoverService;
    }

    @Autowired
    public void setBd(DB bd) {
        this.bd = bd;
    }
//
//    @FXML
//    private ListView<Produit> list_view_all_movies;

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
    public void initialize() {
//         this.tree_view_visualisation_prod = new TreeView<Produit>();

//        System.out.println(bd.getProduitsService().getProduitRepository().retourneProduitParDate());
//        if(imageMoverService.getState() == Worker.State.READY){
//            imageMoverService.start();
//        }
//
//        description_film.setEditable(false);
//        ObservableList<Produit> listeProduits = FXCollections.observableArrayList(bd.getProduitsService().retourLesProduits());
//        list_view_all_movies.setItems(listeProduits);
//
        creeTreeVire(this.tree_view_visualisation_prod);
        selectItem();
    }

    public void creeTreeVire(TreeView<ProduitItemI> treeView){




    }


    public void selectItem(){
//                System.out.println(bd.getProduitsService().getProduitRepository().retourneProduitParDate());

//        list_view_all_movies.setOnMouseClicked((event) -> {
//            afficheProduitSlected(list_view_all_movies.getSelectionModel().getSelectedItem());
//        });
    }

    public void afficheProduitSlected(Produit produitChoisi) {
//        String path = "file:images/" + produitChoisi.getImage().toString();
//        Image image = new Image(path);
//        image_film.setImage(image);
//        id_titre_film.setText(produitChoisi.getNom());
//        description_film.setText(produitChoisi.getDescription());
//        date_movie_id.setText(produitChoisi.getDateDeSortie().toString());
//        imageMoverService.setCurrentX(image_film.getLayoutX());
//        imageMoverService.setXMax(image_film.getLayoutBounds().getMaxX());
//        imageMoverService.setXMin(image_film.getLayoutBounds().getMinX());
//
//
//        imageMoverService.valueProperty().addListener((a, o, n) -> {
//            image_film.setLayoutX(n);
//        });
    }



}
