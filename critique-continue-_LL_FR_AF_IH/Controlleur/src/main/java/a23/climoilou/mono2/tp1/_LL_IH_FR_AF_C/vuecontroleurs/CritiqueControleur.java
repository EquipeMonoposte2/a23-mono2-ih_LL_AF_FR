package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.TreeViewElements.CritiqueTreeViewNodeCompatible;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.TreeViewElements.CritiqueTreeViewNodeCompatibleFactory;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.CustomCells.*;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.ProduitClassement;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.events.NouveauProduitEvent;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.events.SoumettreCritiqueEvent;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.*;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services.DB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

@FxmlView("CritiqueVue.fxml")
@Component
public class CritiqueControleur
{
    private final ApplicationEventPublisher applicationEventPublisher;
    private Critique critique;
    private UtilisateurSession utilisateurSession;
    private DB db;
    private List<Produit> produitList;
    private ApplicationContext applicationContext;
    private CritiqueTreeViewNodeCompatibleFactory critiqueTreeViewNodeCompatibleFactory;

    @FXML
    private Button ajouterJeuButton;

    @FXML
    private ChoiceBox<String> choixJeuCritique;

    @FXML
    private ChoiceBox<EnumEcart> choixPoidsCritique;

    @FXML
    private DatePicker dateCritique;

    @FXML
    private TreeView<ICritiqueTreeViewNode> mesCritiquesAffiche;

    @FXML
    private CheckBox neutreCheckbox;

    @FXML
    private Text nomJeuCritique;

    @FXML
    private ListView<IProduitClassement> nouvelleCritique;

    @FXML
    private Button soumettreCritiqueButton;

    @Autowired
    public void setUtilisateurSession(UtilisateurSession utilisateurSession) {
        this.utilisateurSession = utilisateurSession;
    }

    @Autowired
    public CritiqueControleur(ApplicationEventPublisher applicationEventPublisher, DB db, ApplicationContext applicationContext, CritiqueTreeViewNodeCompatibleFactory critiqueTreeViewNodeCompatibleFactory)
    {
        this.applicationEventPublisher = applicationEventPublisher;
        this.db = db;
        this.applicationContext = applicationContext;
        this.critiqueTreeViewNodeCompatibleFactory = critiqueTreeViewNodeCompatibleFactory;
    }

    @FXML
    private void initialize() {

        // Initialisation de l'interface utilisateur ici
        critique = Critique.builder().critiqueLienProduits(new ArrayList<>()).build();

        // Setup de la liste de jeux
        majChoiceBoxProduits();

        //Setup liste de poids
        choixPoidsCritique.getItems().addAll(EnumEcart.values());
        if(choixPoidsCritique.getItems().size() > 0)choixPoidsCritique.setValue(choixPoidsCritique.getItems().get(0));

        // Setup de la custom listview
        nouvelleCritique.setCellFactory((a) -> {
            ListCell<IProduitClassement> retCell = null;
            try {
                retCell = new CritiqueListViewCell();
            } catch (IOException e) {
                throw new RuntimeException("Null custom List Cell");
            }
            return retCell;
        });

        // Setup du treeView
        majMesCritiquesAffiche();
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

            //Si le joueur a deja critiqué ce jeu dans la critique actuelle, on rajoute pas
            if(!critique.possedeJeu(jeu)){

                //Ajout
                critique.ajouterJeu(jeu,poidsJeu,estNeutre);
                nouvelleCritique.getItems().add(new ProduitClassement(jeu.getNom(),poidsJeu.toString(),estNeutre));
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
        utilisateurSession = applicationContext.getBean(UtilisateurSession.class);
        Utilisateur utilisateur = db.getUtilisateursService().getUtilisateurRepo().findFirstByIdentifiant(utilisateurSession.getSession().getIdentifiantUtilisateur());

        //Si le champ date est vide, on met la date du jour
        if(date == null){
            date = LocalDate.now();
        }

        //On set la date passee en entree
        critique.setDateCritique(date);

        //Detection de la critique neutre et si on a plus d un jeu critique actuellement
        if(critique.possedeNeutre() && critique.getCritiqueLienProduits().size() > 1){

            //Detection si produit deja dans une critique de l'utilisateur aujourd'hui
            List<Critique> listeCritiqueUser = db.getCritiquesService().getCritiqueRepo().findAllByUtilisateur(utilisateur);

            LocalDate finalDate = date;
            if(!(listeCritiqueUser.stream().anyMatch(critique1 -> critique1.getDateCritique().isEqual(finalDate) && critique1.getCritiqueLienProduits().stream().anyMatch(e -> critique.possedeJeu(e.getProduitActuel()))))){

                //save de la critique en BD
                critique.setUtilisateur(utilisateur);
                db.getCritiquesService().saveCritique(critique);

                //Informer le systeme de la nouvelle critique
                applicationEventPublisher.publishEvent(new SoumettreCritiqueEvent(critique));
            }
        }

        //Fin = clean de la critique et de la ListeView pour en refaire une nouvelle
        critique = Critique.builder().critiqueLienProduits(new ArrayList<>()).build();
        nouvelleCritique.getItems().clear();
    }

    private void majChoiceBoxProduits(){
        //On maj la liste des produits
        Iterable<Produit> produitIterable = db.getProduitsService().getProduitRepository().findAll();
        produitList = new ArrayList<>();
        produitIterable.forEach(produitList::add);

        // Ajout des valeurs dans choicebox
        choixJeuCritique.getItems().clear();
        choixJeuCritique.getItems().addAll(produitList.stream().map(Produit::getNom).toList());

        // Selection des premiers elements du choicebox
        if(choixJeuCritique.getItems().size() > 0)choixJeuCritique.setValue(choixJeuCritique.getItems().get(0));
    }

    private void majMesCritiquesAffiche() {
        utilisateurSession = applicationContext.getBean(UtilisateurSession.class);
        Utilisateur utilisateur = db.getUtilisateursService().getUtilisateurRepo().findFirstByIdentifiant(utilisateurSession.getSession().getIdentifiantUtilisateur());
        List<CritiqueTreeViewNode> listeCritiqueTreeViewNode = db.getCritiquesService().getCritiquesTreeView(utilisateur);
        List<CritiqueTreeViewNodeCompatible> listeCritiqueTreeViewNodeCompatible = new ArrayList<>();

        for(CritiqueTreeViewNode critiqueTreeViewNode : listeCritiqueTreeViewNode){
            listeCritiqueTreeViewNodeCompatible.add(critiqueTreeViewNodeCompatibleFactory.createCritiqueTreeViewNodeCompatible(critiqueTreeViewNode));
        }

        // Créer la cellule de rendu personnalisée
        mesCritiquesAffiche.setCellFactory(treeView -> new CritiqueTreeCell());

        // Créer la racine du TreeView
        TreeItem<ICritiqueTreeViewNode> rootItem = new TreeItem(critiqueTreeViewNodeCompatibleFactory.createCritiqueTreeViewNodeCompatible(new CritiqueTreeViewNode(null, null)));
        rootItem.setExpanded(true);

        // Ajout des années, mois et critiques
        for (CritiqueTreeViewNodeCompatible critiqueNode : listeCritiqueTreeViewNodeCompatible) {

            // Ajouter la critique comme un nœud enfant du mois
            TreeItem<ICritiqueTreeViewNode> critiqueItem = new TreeItem<>(critiqueNode);
            rootItem.getChildren().add(critiqueItem);

            // Ajouter les enfants de la critique comme des noeuds enfants de la critique
            for (ICritiqueTreeViewNode childNode : critiqueNode.getChildren()) {
                TreeItem<ICritiqueTreeViewNode> childItem = new TreeItem<>(childNode);
                critiqueItem.getChildren().add(childItem);
            }
        }

        // Mettre à jour le TreeView avec le nouveau modèle de données
        mesCritiquesAffiche.setRoot(rootItem);
    }

    // Méthode utilitaire pour trouver ou créer un nœud pour une année donnée
    private TreeItem<ICritiqueTreeViewNode> findOrCreateYearNode(TreeItem<ICritiqueTreeViewNode> root, int year) {
        return findOrCreateNode(root, year, node -> node.getDateCritique() != null && node.getDateCritique().getYear() == year);
    }

    // Méthode utilitaire pour trouver ou créer un nœud pour un mois donné
    private TreeItem<ICritiqueTreeViewNode> findOrCreateMonthNode(TreeItem<ICritiqueTreeViewNode> parent, int month) {
        return findOrCreateNode(parent, month, node -> node.getDateCritique() != null && node.getDateCritique().getMonthValue() == month);
    }

    // Méthode générique pour trouver ou créer un noeud en fonction d'un prédicat
    private TreeItem<ICritiqueTreeViewNode> findOrCreateNode(TreeItem<ICritiqueTreeViewNode> parent, int value, Predicate<ICritiqueTreeViewNode> condition) {
        Optional<TreeItem<ICritiqueTreeViewNode>> existingNode = parent.getChildren().stream()
                .filter(item -> condition.test(item.getValue()))
                .findFirst();

        if (existingNode.isPresent()) {
            return existingNode.get();
        } else {
            CritiqueTreeViewNodeCompatible newNodeValue = critiqueTreeViewNodeCompatibleFactory.createCritiqueTreeViewNodeCompatible(new CritiqueTreeViewNode(null, null));
            TreeItem<ICritiqueTreeViewNode> newNodeItem = new TreeItem<>(newNodeValue);

            // Ajouter les enfants du parent au nouveau nœud
            for (ICritiqueTreeViewNode childNode : parent.getValue().getChildren()) {
                TreeItem<ICritiqueTreeViewNode> childItem = new TreeItem<>(childNode);
                newNodeItem.getChildren().add(childItem);
            }

            parent.getChildren().add(newNodeItem);
            return newNodeItem;
        }
    }

    // LES EVENTS LISTENER

    /**
     * Lorsqu'un nouveau produit est ajoute, on met a jour la liste des produits
     * @param event
     */
    @EventListener
    public void onNouveauProduitAjoute(NouveauProduitEvent event){
        majChoiceBoxProduits();
    }

    /**
     * Lorsqu'une nouvelle critique est soumise, on met a jour la liste des critiques
     * @param event
     */
    @EventListener
    public void onSoumissionCritique(SoumettreCritiqueEvent event)
    {
        majMesCritiquesAffiche();
    }
}