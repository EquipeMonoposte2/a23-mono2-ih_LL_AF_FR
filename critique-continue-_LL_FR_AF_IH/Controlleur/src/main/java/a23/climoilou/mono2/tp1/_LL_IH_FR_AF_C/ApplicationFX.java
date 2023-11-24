package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.events.ApplicationFXEvent;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.events.TabPaneEvent;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs.*;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Type;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Utilisateur;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.UtilisateurSession;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;


/**
 * Controleur principal de l'application
 */
@Component
public class ApplicationFX extends Application  {

    @Autowired
    private ConfigurableApplicationContext context;
    private static Stage primaryStage;
    private  FxWeaver fxWeaver;
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

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
        //init du primary stage
        ApplicationFX.primaryStage =primaryStage;
        fxWeaver = context.getBean(FxWeaver.class);

        //lance l'application
        lancementPageConnection();
    }

    public void lancementPageConnection(){
        try {
            //affiche page connexion
            FxControllerAndView<ConnectionControleur, TabPane> connectionControleurTabPaneFxControllerAndView = fxWeaver.load(ConnectionControleur.class);
            Parent rootConn = connectionControleurTabPaneFxControllerAndView.getView().get();
            ConnectionControleur controleur = connectionControleurTabPaneFxControllerAndView.getController();

            //lancement connexion vue
            primaryStage.setScene(new Scene(rootConn));
            primaryStage.show();
        } catch (Exception e) {
            System.err.println(e.getMessage() + "    -    " + Arrays.toString(e.getStackTrace()));
        }
    }

    /**
     * ApplicationFx event handler pour tous les évenement d'applications fx
     * @param applicationFXEvent
     */
    @EventListener(ApplicationFXEvent.class)
    public void filtreEvenementsApplication(ApplicationFXEvent applicationFXEvent){
        fxWeaver = context.getBean(FxWeaver.class);
        UtilisateurSession session = context.getBean(UtilisateurSession.class);

        if (applicationFXEvent.isEstConnectionEvent()) {
            //init session connecté
            session.connection(applicationFXEvent.getUtilisateur().getIdentifiantUtilisateur(), applicationFXEvent.getUtilisateur().getPermission());

            //main vue (navigation)
            FxControllerAndView<NavigationControleur, TabPane> controllerAndViewNav = fxWeaver.load(NavigationControleur.class);
            Parent root = controllerAndViewNav.getView().get();
            NavigationControleur navigationControleur = controllerAndViewNav.getController();
            //ajout de l'utilisateur dans la navigation

            //ajout du contenu aux tabs
            navigationControleur.getTabNouveauProduit().setContent(fabriquerRoot(NouveauProduitControleur.class, fxWeaver)); //produit vue
            navigationControleur.getTabStatistique().setContent(fabriquerRoot(StatistiquesControleur.class, fxWeaver)); //
            navigationControleur.getTabVisualisationProduit().setContent(fabriquerRoot(VisualisationProduitControleur.class, fxWeaver)); //
            navigationControleur.getTabCompte().setContent(fabriquerRoot(CompteControleur.class,fxWeaver)); //
            navigationControleur.getTabNouvelleCritique().setContent(fabriquerRoot(CritiqueControleur.class, fxWeaver));
            navigationControleur.getTabVisualisationProduit().setContent(fabriquerRoot(VisualisationProduitControleur.class, fxWeaver));
            navigationControleur.getTabAPropos().setContent(fabriquerRoot(AProposControleur.class,fxWeaver));

            //ici nous allons pouvoir vérifier le type d'utilisateur et décider les vues à ne pas afficher (plus tard)
            if (session.getSession().getPermission() != Type.Expert) {
                System.out.println(session.getSession().getPermission());
                navigationControleur.getTabNouvelleCritique().setDisable(true);
                navigationControleur.getTabNouvelleCritique().setContent(null);
                navigationControleur.getTabNouvelleCritique().setClosable(false);
            }

            // Lancement d un event spring lors du changement de tab
            ajoutListenerTabPane(navigationControleur);

            //lancement  main vue
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }
        //event pour envoyer vers la page pour créer le compte
        else if (applicationFXEvent.isEstNouveauCompteEvent()){
            primaryStage.setScene(new Scene(fabriquerRoot(CreationCompteControleur.class, fxWeaver)));
            //on peut ajouter listener si on ferme la window top right X
            primaryStage.setOnCloseRequest(closeEvent ->{
                System.err.println("vue fermer top right");
            });
            primaryStage.show();
        }
        //event pour envoyer à la navigation après création de compte
        else if(applicationFXEvent.isEstCreationCompteEvent()){
            FxControllerAndView<SuccesCreationCompteControleur, TabPane> succesCreationCompteControleur = fxWeaver.load(SuccesCreationCompteControleur.class);
            Parent succesRoot = succesCreationCompteControleur.getView().get();
            //init session connecté
            session.connection(applicationFXEvent.getUtilisateur().getIdentifiantUtilisateur(), applicationFXEvent.getUtilisateur().getPermission());

            primaryStage.setScene(new Scene(succesRoot));
            primaryStage.show();
        }
        else if(applicationFXEvent.isEstDeconnectionEvent()){
            //detruire bean session
            session.deconnection();
            //lancement connection
            lancementPageConnection();
        }
    }


    private void ajoutListenerTabPane(NavigationControleur navigationControleur)
    {
        // Nouvelle critique
        navigationControleur.getTabNouvelleCritique().getTabPane().getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            //on lance un event spring
            applicationEventPublisher.publishEvent(new TabPaneEvent(newValue));
        });

        // Nouveau produit
        navigationControleur.getTabNouveauProduit().getTabPane().getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            //on lance un event spring
            applicationEventPublisher.publishEvent(new TabPaneEvent(newValue));
        });

        // Statistiques
        navigationControleur.getTabStatistique().getTabPane().getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            //on lance un event spring
            applicationEventPublisher.publishEvent(new TabPaneEvent(newValue));
        });

        // Visualisation produit
        navigationControleur.getTabVisualisationProduit().getTabPane().getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            //on lance un event spring
            applicationEventPublisher.publishEvent(new TabPaneEvent(newValue));
        });

        // Compte
        navigationControleur.getTabCompte().getTabPane().getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            //on lance un event spring
            applicationEventPublisher.publishEvent(new TabPaneEvent(newValue));
        });

        // A propos
        navigationControleur.getTabAPropos().getTabPane().getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            //on lance un event spring
            applicationEventPublisher.publishEvent(new TabPaneEvent(newValue));
        });
    }

    public void initBeanUtilisateurConnecte(Utilisateur utilisateur){
        UtilisateurSession utilisateurSessionBean = context.getBean(UtilisateurSession.class);
        utilisateurSessionBean.setIdentifiantUtilisateur(utilisateur.getIdentifiant());
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
