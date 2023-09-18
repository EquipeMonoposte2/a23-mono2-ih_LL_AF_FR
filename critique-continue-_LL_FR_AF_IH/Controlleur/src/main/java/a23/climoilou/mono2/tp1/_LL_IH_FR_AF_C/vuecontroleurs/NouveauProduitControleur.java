package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;


/**
 * Controleur de la vue NouveauProduitVue.fxml
 */
@FxmlView("NouveauProduitVue.fxml")
@Component
public class NouveauProduitControleur implements ControleurInterface{

    @FXML
    private Button buttonCreationInput;

    @FXML
    private DatePicker dateSortieMediaInput;

    @FXML
    private TextField descriptionMediaInput;

    @FXML
    private TextField lienImageMediaInput;

    @FXML
    private TextField nomMediaInput;

    @FXML
    void createMedia(ActionEvent event) {
        System.out.println("Test du fonction du nouveau produit controleur");
    }
}
