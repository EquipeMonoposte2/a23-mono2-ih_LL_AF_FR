package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Produit;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Control;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;


@Component
@FxmlView("vueProduit.fxml")
public class VisualisationProduitControleur {

    @FXML
    private ListView<Produit> listeFilms;

    @FXML
    private ImageView imageFilm;

    @FXML
    private Text titreFilm;

    @FXML
    private TextArea descriptionFilm;

    @FXML
    private Text dateFilm;

    @FXML
    public void initialize() {

    }
}
