package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs.NavigationControleur;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs.NouveauProduitControleur;
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
    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("javafx init");

        FxWeaver fxWeaver = context.getBean(FxWeaver.class);

        try {
            //main vue (navigation)
            FxControllerAndView<NavigationControleur, TabPane> controllerAndViewNav = fxWeaver.load(NavigationControleur.class);
            Parent root = controllerAndViewNav.getView().get();
            NavigationControleur navigationControleur = controllerAndViewNav.getController();

            //nouveauproduit vue
            FxControllerAndView<NouveauProduitControleur, AnchorPane> controllerAndViewNewProduit = fxWeaver.load(NouveauProduitControleur.class);
            Parent newProduitRoot = controllerAndViewNewProduit.getView().get();
            NouveauProduitControleur nouveauProduitControleur = controllerAndViewNewProduit.getController();

            //ajout
            navigationControleur.getTabNouveauProduit().setContent(newProduitRoot);


            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        }catch (Exception e){
            System.err.println(e.getMessage() + "    -    "+ Arrays.toString(e.getStackTrace()));
        }

    }

    @Override
    public void init() throws Exception {
        String[] args = getParameters().getRaw().toArray(new String[0]);
        //creation explicite du contexte de l'application
        this.context = new SpringApplicationBuilder().sources(CritiqueContinueLlFrAfIhApplication.class).run(args);;
    }

    @Override
    public void stop() throws Exception {
        //destruction du contexte
        this.context.stop();

        //fermeture javafx
        Platform.exit();
    }
}
