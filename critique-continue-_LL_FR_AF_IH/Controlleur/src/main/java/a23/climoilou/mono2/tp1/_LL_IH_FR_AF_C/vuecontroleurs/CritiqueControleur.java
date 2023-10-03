package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.events.SoumettreCritiqueEvent;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Critique;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.EnumEcart;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Produit;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services.DB;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@FxmlView("CritiqueVue.fxml")
@Component
public class CritiqueControleur
{
    private final ApplicationEventPublisher applicationEventPublisher;
    private Critique critique;
    private Utilisateur utilisateur;
    private DB db;
    private List<Produit> produitList;

    @Autowired
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @FXML
    private Button ajouterJeuButton;

    @FXML
    private ChoiceBox<String> choixJeuCritique;

    @FXML
    private ChoiceBox<EnumEcart> choixPoidsCritique;

    @FXML
    private DatePicker dateCritique;

    @FXML
    private ListView<?> mesCritiquesAffiche;

    @FXML
    private CheckBox neutreCheckbox;

    @FXML
    private Text nomJeuCritique;

    @FXML
    private ListView<?> nouvelleCritique;

    @FXML
    private Button soumettreCritiqueButton;

    @Autowired
    public CritiqueControleur(ApplicationEventPublisher applicationEventPublisher, DB db)
    {
        this.applicationEventPublisher = applicationEventPublisher;
        this.db = db;
    }

    @FXML
    private void initialize() {

        // Initialisation de l'interface utilisateur ici
        critique = Critique.builder().utilisateur(utilisateur).critiqueLienProduits(new ArrayList<>()).build();

        // Setup de la liste
        Iterable<Produit> produitIterable = db.getProduitsService().getProduitRepository().findAll();
        produitList = new ArrayList<>();
        produitIterable.forEach(produitList::add);

        // Ajout des jeux
        choixJeuCritique.getItems().addAll(produitList.stream().map(Produit::getNom).toList());
    }

    @FXML
    void ajouterJeu(ActionEvent event) {

        EnumEcart poidsJeu = choixPoidsCritique.getValue();
        Produit jeu;

        Optional<Produit> produitOptional = produitList.stream()
                .filter(produit -> produit.getNom().equals(choixJeuCritique.getValue()))
                .findFirst();

        if (produitOptional.isPresent()) {

            jeu = produitOptional.get();
            boolean estNeutre = neutreCheckbox.isSelected();

            //Validations des champs

            //Si neutre est coché mais que la critique possede deja un jeu neutre, on enleve neutre
            if(estNeutre && critique.possedeNeutre()){
                estNeutre = false;
            }

            //Si le joueur a deja critiqué ce jeu, on rajoute pas
            if(!critique.possedeJeu(jeu)){
                //Ajout
                critique.ajouterJeu(jeu,poidsJeu,estNeutre);
            }
        }
    }

    @FXML
    void soumettreCritique(ActionEvent event) {
        LocalDate date = dateCritique.getValue();

        //Si le champ date est vide, on met la date du jour
        if(date == null){
            date = LocalDate.now();
        }

        //On set la date passee en entree
        critique.setDateCritique(date);

        applicationEventPublisher.publishEvent(new SoumettreCritiqueEvent(this));

        //Fin = clean de la critique pour en refaire une nouvelle
        critique = Critique.builder().utilisateur(utilisateur).critiqueLienProduits(new ArrayList<>()).build();
    }

}
