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

                    //ajout du contenu aux tabs
                    navigationControleur.getTabNouveauProduit().setContent(fabriquerRoot(NouveauProduitControleur.class, fxWeaver)); //produit vue
                    navigationControleur.getTabStatistique(); //
                    navigationControleur.getTabVisualisationProduit(); //
                    navigationControleur.getTabCompte(); //
                    navigationControleur.getTabNouvelleCritique().setContent(fabriquerRoot(CritiqueControleur.class, fxWeaver));
                    navigationControleur.getTabVisualisationProduit().setContent(fabriquerRoot(VisualisationProduitControleur.class, fxWeaver));

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
                    primaryStage.setScene(new Scene(fabriquerRoot(CreationCompteControleur.class, fxWeaver)));
                    //on peut ajouter listener si on ferme la window top right X
                    primaryStage.setOnCloseRequest(closeEvent ->{
                        System.err.println("vue fermer top right");
                    });
                    primaryStage.show();
                }
                //event pour envoyer à la navigation après création de compte
                else if(event instanceof CreationCompteEvent){
                    primaryStage.setScene(new Scene(fabriquerRoot(SuccesCreationCompteControleur.class, fxWeaver)));

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

    /**
     * Fabrique un Node qui aura le role d'un root.
     * @param controleurClass controleur du root à créer
     * @param fxweaver FxWeaver à utiliser pour le chargement des vues
     * @return root root avec le controleur commandé en paramètre
     */
    private <T> Parent fabriquerRoot(Class<T> controleurClass, FxWeaver fxweaver) {
        FxControllerAndView<T, AnchorPane> controllerAndView = fxweaver.load(controleurClass);
        Parent root = controllerAndView.getView().get();
        controllerAndView.getController();
        return root;
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
