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
import javafx.stage.FileChooser;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.lang.model.element.ModuleElement;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


/**
 * Controleur de la vue NouveauProduitVue.fxml
 */
@FxmlView("NouveauProduitVue.fxml")
@Component
public class NouveauProduitControleur {
    private DB bd;

    FileChooser fileChooser = new FileChooser();

    @Autowired
    public void setBd(DB bd) {
        this.bd = bd;
    }

    @FXML
    private Button buttonCreationInput;

    @FXML
    private Button buttonSaveFile1;
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
                messageErreur.getStyleClass().removeAll();
                messageErreur.getStyleClass().add("validMsg");
                messageErreur.setText("Produit créé.");
                applicationEventPublisher.publishEvent(new NouveauProduitEvent());
            } else {
                messageErreur.setText("Erreur ce produit est déjà existant.");
            }
        } else {
            messageErreur.setText("Erreur tous les champs doivent être remplis.");
        }
    }

    @FXML
    void uploadFile(ActionEvent event) throws IOException {
        File imageProduit = fileChooser.showOpenDialog(null);


            if(imageProduit != null){
                String pathImages = "images";
                Path path = Paths.get(pathImages);
                Files.createDirectories(path);
                try{
                    File target = new File(pathImages, imageProduit.getName());
                    Files.copy(imageProduit.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
                lienImageMediaInput.setText(imageProduit.getName());
                }catch (IOException e){
                    e.printStackTrace();
                }
            }else{
                System.out.println("bonjour");
            }



    }
}