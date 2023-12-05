package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.events.ApplicationFXEvent;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.events.TabPaneEvent;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.services.ConnecteService;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.services.RedimensionnementService;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs.*;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Type;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Utilisateur;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.UtilisateurSession;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
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
public class ApplicationFX extends Application {

    @Autowired
    private ConfigurableApplicationContext context;
    private static Stage primaryStage;
    private FxWeaver fxWeaver;
    private ApplicationEventPublisher applicationEventPublisher;
    private ConnecteService connecteService;

    @Autowired
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * Lancement de l'application
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        ApplicationFX.primaryStage = primaryStage;
        fxWeaver = context.getBean(FxWeaver.class);

        lancementPageConnection();
    }

    public void lancementPageConnection() {
        try {
            FxControllerAndView<ConnectionControleur, TabPane> connectionControleurTabPaneFxControllerAndView = fxWeaver.load(ConnectionControleur.class);
            Parent rootConn = connectionControleurTabPaneFxControllerAndView.getView().get();
            ConnectionControleur controleur = connectionControleurTabPaneFxControllerAndView.getController();


            primaryStage.setScene(new Scene(rootConn));
            primaryStage.show();
        } catch (Exception e) {
            System.err.println(e.getMessage() + "    -    " + Arrays.toString(e.getStackTrace()));
        }
    }

    /**
     * ApplicationFx event handler pour tous les évenement d'applications fx
     *
     * @param applicationFXEvent
     */
    @EventListener(ApplicationFXEvent.class)
    public void filtreEvenementsApplication(ApplicationFXEvent applicationFXEvent) {
        fxWeaver = context.getBean(FxWeaver.class);
        UtilisateurSession session = context.getBean(UtilisateurSession.class);

        if (applicationFXEvent.isEstConnectionEvent()) {

            session.connection(applicationFXEvent.getUtilisateur().getIdentifiantUtilisateur(), applicationFXEvent.getUtilisateur().getPermission());


            FxControllerAndView<NavigationControleur, TabPane> controllerAndViewNav = fxWeaver.load(NavigationControleur.class);
            Parent root = controllerAndViewNav.getView().get();
            NavigationControleur navigationControleur = controllerAndViewNav.getController();

            navigationControleur.getTabNouveauProduit().setContent(fabriquerRoot(NouveauProduitControleur.class, fxWeaver));
            navigationControleur.getTabStatistique().setContent(fabriquerRoot(StatistiquesControleur.class, fxWeaver));
            navigationControleur.getTabVisualisationProduit().setContent(fabriquerRoot(VisualisationProduitControleur.class, fxWeaver));
            navigationControleur.getTabCompte().setContent(fabriquerRoot(CompteControleur.class, fxWeaver));
            navigationControleur.getTabNouvelleCritique().setContent(fabriquerRoot(CritiqueControleur.class, fxWeaver));
            navigationControleur.getTabVisualisationProduit().setContent(fabriquerRoot(VisualisationProduitControleur.class, fxWeaver));
            navigationControleur.getTabAPropos().setContent(fabriquerRoot(AProposControleur.class, fxWeaver));

            if (session.getSession().getPermission() != Type.Expert) {
                System.out.println(session.getSession().getPermission());
                navigationControleur.getTabNouvelleCritique().setDisable(true);
                navigationControleur.getTabNouvelleCritique().setContent(null);
                navigationControleur.getTabNouvelleCritique().setClosable(false);
            }

            ajoutListenerTabPane(navigationControleur);

            primaryStage.setScene(new Scene(root));
            primaryStage.show();
            appelerConnecteService();
        }
        else if (applicationFXEvent.isEstNouveauCompteEvent()) {
            primaryStage.setScene(new Scene(fabriquerRoot(CreationCompteControleur.class, fxWeaver)));
            primaryStage.setOnCloseRequest(closeEvent -> {
            });
            primaryStage.show();
        }
        else if (applicationFXEvent.isEstCreationCompteEvent()) {
            FxControllerAndView<SuccesCreationCompteControleur, TabPane> succesCreationCompteControleur = fxWeaver.load(SuccesCreationCompteControleur.class);
            Parent succesRoot = succesCreationCompteControleur.getView().get();
            session.connection(applicationFXEvent.getUtilisateur().getIdentifiantUtilisateur(), applicationFXEvent.getUtilisateur().getPermission());

            primaryStage.setScene(new Scene(succesRoot));
            primaryStage.show();
        } else if (applicationFXEvent.isEstDeconnectionEvent()) {
            session.deconnection();
            lancementPageConnection();
        }
    }

    private void appelerConnecteService() {
        connecteService = new ConnecteService(primaryStage);
        final EventHandler<MouseEvent>[] clickHandler = new EventHandler[]{null};

        clickHandler[0] = event -> {
            Node node = (Node) event.getSource();

            connecteService.cancel();
            node.setOnMouseClicked(null);
        };

        primaryStage.getScene().getRoot().getChildrenUnmodifiable().forEach(node -> node.setOnMouseClicked(clickHandler[0]));



        connecteService.restart();
    }

    private void stopService(javafx.scene.input.MouseEvent event) {
        connecteService.cancel();
        ((Stage) event.getSource()).removeEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, this::stopService);
    }

    private void ajoutListenerTabPane(NavigationControleur navigationControleur) {
        navigationControleur.getTabNouvelleCritique().getTabPane().getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            applicationEventPublisher.publishEvent(new TabPaneEvent(newValue.getText()));
        });
    }

    public void initBeanUtilisateurConnecte(Utilisateur utilisateur) {
        UtilisateurSession utilisateurSessionBean = context.getBean(UtilisateurSession.class);
        utilisateurSessionBean.setIdentifiantUtilisateur(utilisateur.getIdentifiant());
    }

    /**
     * Fabrique un Node qui aura le role d'un root.
     *
     * @param controleurClass controleur du root à créer
     * @param fxweaver        FxWeaver à utiliser pour le chargement des vues
     * @return root root avec le controleur commandé en paramètre
     */
    private <T> Parent fabriquerRoot(Class<T> controleurClass, FxWeaver fxweaver) {
        FxControllerAndView<T, AnchorPane> controllerAndView = fxweaver.load(controleurClass);
        Parent root = controllerAndView.getView().get();
        controllerAndView.getController();
        return root;
    }

    /**
     * Event spring pour le changement de tab
     * Redimensionner la fenetre en fonction du tab
     *
     * @param event
     */
    @EventListener
    public void onTabChangedEvent(TabPaneEvent event) {

        double width = 620;
        double height = 420;

        switch (event.getValue()) {
            case "NouvelleCritique":
                width = 1280;
                height = 800;
                break;
            case "NouveauProduit":
                height = 450;
                break;
            case "VisualisationProduit":
                width = 600;
                height = 470;
                break;
            default:
                break;
        }

        double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
        double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();

        double newX = (screenWidth - width) / 2;
        double newY = (screenHeight - height) / 2;

        RedimensionnementService redimensionnementService = new RedimensionnementService(new RedimensionnementService.LocationTaille(primaryStage.getX(), primaryStage.getY(), primaryStage.getWidth(), primaryStage.getHeight()), new RedimensionnementService.LocationTaille(newX, newY, width, height));
        redimensionnementService.setPeriod(Duration.millis(2));

        redimensionnementService.lastValueProperty().addListener((c, o, n) -> {
            if (n != null) {
                primaryStage.setX(n.getX());
                primaryStage.setY(n.getY());
                primaryStage.setWidth(n.getLargeur());
                primaryStage.setHeight(n.getLongueur());
            }
        });
        redimensionnementService.restart();
    }

    @Override
    public void init() throws Exception {
        String[] args = getParameters().getRaw().toArray(new String[0]);
        this.context = new SpringApplicationBuilder().sources(CritiqueContinueLlFrAfIhApplication.class).run(args);
    }

    @Override
    public void stop() throws Exception {
        this.context.stop();

        Platform.exit();
    }
}
