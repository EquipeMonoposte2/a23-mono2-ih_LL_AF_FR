package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.events.NouveauProduitEvent;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Produit;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services.DB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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

    @FXML
    private Label messageErreur;

    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public NouveauProduitControleur(ApplicationEventPublisher applicationEventPublisher){
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * Boutton creation de produit
     *
     * @param event
     */
    @FXML
    void createMedia(ActionEvent event) throws InterruptedException {

        Produit produit;
        if (bd.getProduitsService().creationValidationProduit(nomMediaInput.getText(), descriptionMediaInput.getText(), dateSortieMediaInput.getValue(), lienImageMediaInput.getText()) != null) {
            produit = bd.getProduitsService().getProduitRepository().findFirstByNom(this.nomMediaInput.getText());
            if (produit == null) {
                bd.getProduitsService().saveProduit(bd.getProduitsService().creationValidationProduit(nomMediaInput.getText(), descriptionMediaInput.getText(), dateSortieMediaInput.getValue(), lienImageMediaInput.getText()));
                messageErreur.setText("Produit créé.");
                applicationEventPublisher.publishEvent(new NouveauProduitEvent());
            } else {
                messageErreur.setText("Erreur ce produit est déjà existant.");
            }
        } else {
            messageErreur.setText("Erreur tous les champs doivent être remplis.");
        }
    }
}