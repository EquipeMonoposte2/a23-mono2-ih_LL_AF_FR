package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.events.CategorieEvent;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.events.NouveauProduitEvent;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Categorie;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Produit;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services.CategorieService;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services.DB;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.repository.Repo_Categorie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxWeaver;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.lang.model.element.ModuleElement;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;


/**
 * Controleur de la vue NouveauProduitVue.fxml
 */
@FxmlView("NouveauProduitVue.fxml")
@Component
public class NouveauProduitControleur {
    FileChooser fileChooser = new FileChooser();


    private FxWeaver fxWeaver;

    private DB bd;

    private Repo_Categorie categorieRepo;

    @Autowired
    public void setCategorieRepo(Repo_Categorie categorieRepo) {
        this.categorieRepo = categorieRepo;
    }

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
    @FXML
    private TextField categorieTextField;


    private Stage categorieStage;

    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public NouveauProduitControleur(ApplicationEventPublisher applicationEventPublisher){
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @FXML
    void choisirCategorie(ActionEvent event) {
        FxControllerAndView<CategorieControleur, AnchorPane> controllerAndView = fxWeaver.load(CategorieControleur.class);
        Parent root = controllerAndView.getView().get();
        categorieStage = new Stage();
        categorieStage.setResizable(false);
        categorieStage.initModality(Modality.APPLICATION_MODAL);
        categorieStage.setScene(new Scene(root));
        categorieStage.showAndWait();
    }

    /**
     * Boutton creation de produit
     *
     * @param event
     */
    @FXML
    void createMedia(ActionEvent event) throws InterruptedException {

        Produit produit;
        if (bd.getProduitsService().creationValidationProduit(nomMediaInput.getText(), descriptionMediaInput.getText(), dateSortieMediaInput.getValue(), lienImageMediaInput.getText(), null) != null) {
            produit = bd.getProduitsService().getProduitRepository().findFirstByNom(this.nomMediaInput.getText());
            if (produit == null) {
                bd.getProduitsService().saveProduit(bd.getProduitsService().creationValidationProduit(nomMediaInput.getText(), descriptionMediaInput.getText(), dateSortieMediaInput.getValue(), lienImageMediaInput.getText(), categorieRepo.findByNom(categorieTextField.getText())));
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

    /**
     * Rajoute une image au jeux
     * @param event
     * @throws IOException
     */
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

    @EventListener
    public void mettreAJourCategorie(CategorieEvent event) {
        if (event.getNouvelleCategorie() == null && event.getChoix() != null) {
            categorieTextField.setText(event.getChoix().getNom());
            categorieStage.close();
        }
    }

    @Autowired
    public void setFxWeaver(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
    }
}