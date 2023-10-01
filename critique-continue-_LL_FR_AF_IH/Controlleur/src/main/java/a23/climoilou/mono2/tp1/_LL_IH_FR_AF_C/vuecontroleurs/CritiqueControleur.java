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
import org.springframework.context.ApplicationContext;
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
    private ApplicationContext applicationContext;

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
    private ListView<String> nouvelleCritique;

    @FXML
    private Button soumettreCritiqueButton;

    @Autowired
    public CritiqueControleur(ApplicationEventPublisher applicationEventPublisher, DB db, ApplicationContext applicationContext)
    {
        this.applicationEventPublisher = applicationEventPublisher;
        this.db = db;
        this.applicationContext = applicationContext;
    }

    @FXML
    private void initialize() {

        // Initialisation de l'interface utilisateur ici
        utilisateur = applicationContext.getBean(Utilisateur.class);
        critique = Critique.builder().utilisateur(utilisateur).critiqueLienProduits(new ArrayList<>()).build();

        // Setup de la liste de jeux
        Iterable<Produit> produitIterable = db.getProduitsService().getProduitRepository().findAll();
        produitList = new ArrayList<>();
        produitIterable.forEach(produitList::add);

        // Ajout des valeurs dans choicebox
        choixJeuCritique.getItems().addAll(produitList.stream().map(Produit::getNom).toList());
        choixPoidsCritique.getItems().addAll(EnumEcart.values());

        // Selection des premiers elements des choicebox
        choixJeuCritique.setValue(choixJeuCritique.getItems().get(0));
        choixPoidsCritique.setValue(choixPoidsCritique.getItems().get(0));
    }

    /**
     * Ajouter un jeu a la critique actuelle
     * @param event
     */
    @FXML
    void ajouterJeu(ActionEvent event) {

        EnumEcart poidsJeu = choixPoidsCritique.getValue();
        Produit jeu;

        Optional<Produit> produitOptional = produitList.stream()
                .filter(produit -> produit.getNom().equals(choixJeuCritique.getValue()))
                .findFirst();

        // Si le jeu existe, on passe a la suite
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
                nouvelleCritique.getItems().add(jeu.getNom() + " - Différence " + poidsJeu.toString() + " par rapport au jeu en dessous" + (estNeutre ? " - Neutre" : ""));
            }
        }
    }

    /**
     * Soumettre la critique actuelle et informer le system afin de MAJ les donnees etc...
     * @param event
     */
    @FXML
    void soumettreCritique(ActionEvent event) {
        LocalDate date = dateCritique.getValue();

        //Si le champ date est vide, on met la date du jour
        if(date == null){
            date = LocalDate.now();
        }

        //On set la date passee en entree
        critique.setDateCritique(date);

        //Detection de la critique neutre
        if(critique.possedeNeutre()){

            //Detection si produit deja dans une critique de l'utilisateur aujourd'hui
            utilisateur = db.getUtilisateursService().getUtilisateurRepo().findFirstByIdentifiant("jeanMichel");
            List<Critique> listeCritiqueUser = db.getCritiquesService().getCritiqueRepo().findAllByUtilisateur(utilisateur);

            LocalDate finalDate = date;
            if(!(listeCritiqueUser.stream().anyMatch(critique1 -> critique1.getDateCritique().isEqual(finalDate)))){

                //save de la critique en BD
                //TODO bug ici
                db.getUtilisateursService().sauvegarderUtilisateur(utilisateur);
                db.getCritiquesService().saveCritique(critique);

                //Informer le systeme de la nouvelle critique
                applicationEventPublisher.publishEvent(new SoumettreCritiqueEvent(critique));
                System.out.println("Critique neutre");
            }
        }

        System.out.println("Critique clean");
        //Fin = clean de la critique et de la ListeView pour en refaire une nouvelle
        critique = Critique.builder().utilisateur(utilisateur).critiqueLienProduits(new ArrayList<>()).build();
        nouvelleCritique.getItems().clear();
    }

}
