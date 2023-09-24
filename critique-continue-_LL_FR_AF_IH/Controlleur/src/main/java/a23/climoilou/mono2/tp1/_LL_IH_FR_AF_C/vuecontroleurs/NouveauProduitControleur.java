package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Produit;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services.DB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.lang.model.element.ModuleElement;


/**
 * Controleur de la vue NouveauProduitVue.fxml
 */
@FxmlView("NouveauProduitVue.fxml")
@Component
public class NouveauProduitControleur {
    private DB bd;

    @Autowired
    public void setBd(DB bd) {
        this.bd = bd;
    }

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

    /**
     * Boutton creation de produit
     * @param event
     */
    @FXML
    void createMedia(ActionEvent event) {
        Produit p1 = new Produit();

        bd.getProduitsService().saveProduit(p1);

        System.out.println("Test du fonction du nouveau produit controleur");
    }
}
