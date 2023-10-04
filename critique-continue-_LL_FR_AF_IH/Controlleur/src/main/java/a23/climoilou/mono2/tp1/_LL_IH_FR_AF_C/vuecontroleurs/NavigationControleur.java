package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs;


import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Utilisateur;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.UtilisateurSession;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import lombok.Getter;
import lombok.Setter;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controleur de la vue de navigation NavigationVue.fxml
 */
@FxmlView("NavigationVue.fxml")
@Component
@Primary
@Getter
@Setter
public class NavigationControleur  implements Initializable {


    private UtilisateurSession session;


    private NouveauProduitControleur controleurVueInterne;
    private ApplicationEventPublisher applicationEventPublisher;

    private SuccesCreationCompteControleur succesCreationCompteControleur;

    private CompteControleur compteControleur;

    @FXML
    private Tab tabCompte;

    @FXML
    private Tab tabNouveauProduit;

    @FXML
    private Tab tabNouvelleCritique;

    @FXML
    private Tab tabStatistique;

    @FXML
    private Tab tabVisualisationProduit;

    @Autowired
    public void setSession(UtilisateurSession session) {
        this.session = session;
    }

    @Autowired
    public void setSuccesCreationCompteControleur(SuccesCreationCompteControleur succesCreationCompteControleur) {
        this.succesCreationCompteControleur = succesCreationCompteControleur;
    }

    @Autowired
    public void setCompteControleur(CompteControleur compteControleur) {
        this.compteControleur = compteControleur;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //this.compteControleur.setUtilisateur(this.utilisateur);
    }
}
