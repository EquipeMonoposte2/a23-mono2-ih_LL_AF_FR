package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.events.ApplicationFXEvent;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
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

        //ici on lance l'application
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

        if (applicationFXEvent.isEstConnectionEvent()) {
            //utilisateur
            Utilisateur utilisateur = applicationFXEvent.getUtilisateur();

            //init utilisateur connecté
            initBeanUtilisateurConnecte(utilisateur);

            //main vue (navigation)
            FxControllerAndView<NavigationControleur, TabPane> controllerAndViewNav = fxWeaver.load(NavigationControleur.class);
            Parent root = controllerAndViewNav.getView().get();
            NavigationControleur navigationControleur = controllerAndViewNav.getController();
            //ajout de l'utilisateur dans la navigation
           // navigationControleur.setUtilisateur(utilisateur);

            //ajout du contenu aux tabs
            navigationControleur.getTabNouveauProduit().setContent(fabriquerRoot(NouveauProduitControleur.class, fxWeaver)); //produit vue
            navigationControleur.getTabStatistique(); //
            navigationControleur.getTabVisualisationProduit(); //
            navigationControleur.getTabCompte().setContent(fabriquerRoot(CompteControleur.class,fxWeaver)); //
            navigationControleur.getTabNouvelleCritique().setContent(fabriquerRoot(CritiqueControleur.class, fxWeaver));
            navigationControleur.getTabVisualisationProduit().setContent(fabriquerRoot(VisualisationProduitControleur.class, fxWeaver));

            //ici nous allons pouvoir vérifier le type d'utilisateur et décider les vues à ne pas afficher (plus tard)
            if (utilisateur.getType() != Type.Expert) {
                //navigationControleur.getTabNouvelleCritique().setDisable(true);
                //navigationControleur.getTabNouvelleCritique().setContent(null);
                //navigationControleur.getTabNouvelleCritique().setClosable(false);
            }
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
            SuccesCreationCompteControleur cont = succesCreationCompteControleur.getController();
            cont.setUtilisateur(applicationFXEvent.getUtilisateur());
            primaryStage.setScene(new Scene(succesRoot));
            primaryStage.show();
        }
        else if(applicationFXEvent.isEstDeconnectionEvent()){
            //detruire bean utilisateur actuel
            Utilisateur utilisateurBean = context.getBean(Utilisateur.class);
            utilisateurBean.setIdentifiant(null);
            utilisateurBean.setDateDeNaissance(null);
            utilisateurBean.setCritiqueList(null);
            utilisateurBean.setNom(null);
            utilisateurBean.setType(null);
            //lancement connection
            lancementPageConnection();
        }
    }

    public void initBeanUtilisateurConnecte(Utilisateur utilisateur){
        Utilisateur utilisateurBean = context.getBean(Utilisateur.class);
        utilisateurBean.setIdentifiant(utilisateur.getIdentifiant());
        utilisateurBean.setDateDeNaissance(utilisateur.getDateDeNaissance());
        utilisateurBean.setCritiqueList(utilisateur.getCritiqueList());
        utilisateurBean.setNom(utilisateur.getNom());
        utilisateurBean.setId(utilisateur.getId());
        utilisateurBean.setType(utilisateur.getType());
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
