package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.events.ConnectionEvent;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.events.CreationCompteEvent;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.events.NouveauCompteEvent;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs.*;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Type;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Utilisateur;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;


/**
 * Controleur principal de l'application
 */
public class ApplicationFX extends Application {

    private ConfigurableApplicationContext context;

    /**
     * Lancement de l'application
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("javafx init");

        FxWeaver fxWeaver = context.getBean(FxWeaver.class);

        //ici on lance l'application
        try {
            //affiche page connexion
            FxControllerAndView<ConnectionControleur, TabPane> connectionControleurTabPaneFxControllerAndView = fxWeaver.load(ConnectionControleur.class);
            Parent rootConn = connectionControleurTabPaneFxControllerAndView.getView().get();
            ConnectionControleur controleur = connectionControleurTabPaneFxControllerAndView.getController();

            //si un évenement est appelé de type ConnectionEvent (lancer de connection controleur)
            context.addApplicationListener(event -> {
                if (event instanceof ConnectionEvent) {
                    //évenement de connection
                    ConnectionEvent connectionEvent = (ConnectionEvent) event;
                    System.out.println(connectionEvent.getMessage());
                    //utilisateur provient de l'événement
                    Utilisateur utilisateur = connectionEvent.getUtilisateur();

                    //main vue (navigation)
                    FxControllerAndView<NavigationControleur, TabPane> controllerAndViewNav = fxWeaver.load(NavigationControleur.class);
                    Parent root = controllerAndViewNav.getView().get();
                    NavigationControleur navigationControleur = controllerAndViewNav.getController();
                    //ajout de l'utilisateur dans la navigation
                    navigationControleur.setUtilisateur(connectionEvent.getUtilisateur());

                    //nouveau produit vue
                    FxControllerAndView<NouveauProduitControleur, AnchorPane> controllerAndViewNewProduit = fxWeaver.load(NouveauProduitControleur.class);
                    Parent newProduitRoot = controllerAndViewNewProduit.getView().get();
                    NouveauProduitControleur nouveauProduitControleur = controllerAndViewNewProduit.getController();

                    //Visualisation du produit
                    FxControllerAndView<VisualisationProduitController, AnchorPane>  controllerAndViewVisualisationProduit= fxWeaver.load(VisualisationProduitController.class);
                    Parent newVisualisationRoot = controllerAndViewVisualisationProduit.getView().get();
                    VisualisationProduitController visualisationProduitController = controllerAndViewVisualisationProduit.getController();
                    //critique vue
                    FxControllerAndView<CritiqueControler, AnchorPane> controllerAndViewCritique = fxWeaver.load(CritiqueControler.class);
                    Parent critiqueRoot = controllerAndViewCritique.getView().get();
                    CritiqueControler critiqueControler = controllerAndViewCritique.getController();

                    //vue des statistiques
                    FxControllerAndView<StatistiquesControleur, AnchorPane> controllerAndViewStatistiques = fxWeaver.load(StatistiquesControleur.class);
                    Parent statistiqueRoot = controllerAndViewStatistiques.getView().get();
                    StatistiquesControleur statistiquesControleur = controllerAndViewStatistiques.getController();

                    //ajout du contenu aux tabs
                    navigationControleur.getTabNouveauProduit().setContent(newProduitRoot); //produit vue
                    navigationControleur.getTabStatistique().setContent(statistiqueRoot); //
                    navigationControleur.getTabVisualisationProduit(); //
                    navigationControleur.getTabCompte(); //
                    navigationControleur.getTabNouvelleCritique(); //
                    navigationControleur.getTabVisualisationProduit().setContent(newVisualisationRoot);

                    //ici nous allons pouvoir vérifier le type d'utilisateur et décider les vues à ne pas afficher (plus tard)
                    if (utilisateur.getType() != Type.Expert) {
                        //navigationControleur.getTabNouvelleCritique().setDisable(true);
                        //navigationControleur.getTabNouvelleCritique().setContent(null);
                        // navigationControleur.getTabNouvelleCritique().setClosable(false);
                    }

                    //lancement  main vue
                    primaryStage.setScene(new Scene(root));
                    primaryStage.show();
                }
                //event pour envoyer vers la page pour créer le compte
               else if (event instanceof NouveauCompteEvent){
                    FxControllerAndView<CreationCompteControleur,AnchorPane> creationCompteEventAnchorPaneFxControllerAndView = fxWeaver.load(CreationCompteControleur.class);
                    Parent rootCreation = creationCompteEventAnchorPaneFxControllerAndView.getView().get();

                    primaryStage.setScene(new Scene(rootCreation));
                    //on peut ajouter listener si on ferme la window top right
                    primaryStage.setOnCloseRequest(closeEvent ->{
                        System.err.println("vue fermer top right");
                    });
                    primaryStage.show();
                }
               //event pour envoyer
               else if(event instanceof CreationCompteEvent){
                    FxControllerAndView<SuccesCreationCompteControleur,AnchorPane> creationCompteEventAnchorPaneFxControllerAndView = fxWeaver.load(SuccesCreationCompteControleur.class);
                    Parent rootCreation = creationCompteEventAnchorPaneFxControllerAndView.getView().get();

                    primaryStage.setScene(new Scene(rootCreation));
                    primaryStage.show();


                }
            });

            //lancement connexion vue
            primaryStage.setScene(new Scene(rootConn));
            primaryStage.show();

        } catch (Exception e) {
            System.err.println(e.getMessage() + "    -    " + Arrays.toString(e.getStackTrace()));
        }

    }

    @Override
    public void init() throws Exception {
        String[] args = getParameters().getRaw().toArray(new String[0]);
        //creation explicite du contexte de l'application
        this.context = new SpringApplicationBuilder().sources(CritiqueContinueLlFrAfIhApplication.class).run(args);

    }

    @Override
    public void stop() throws Exception {
        //destruction du contexte
        this.context.stop();

        //fermeture javafx
        Platform.exit();
    }
}
