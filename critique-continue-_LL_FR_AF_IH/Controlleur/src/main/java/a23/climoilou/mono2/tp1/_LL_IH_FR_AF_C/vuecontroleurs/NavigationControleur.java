package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs;


import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Utilisateur;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * Controleur de la vue de navigation NavigationVue.fxml
 */
@FxmlView("NavigationVue.fxml")
@Component
public class NavigationControleur  {


    private Utilisateur utilisateur;
    private NouveauProduitControleur controleurVueInterne;

    private SuccesCreationCompteControleur succesCreationCompteControleur;

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

    public void setControleurVueInterne(NouveauProduitControleur controleurVueInterne) {
        this.controleurVueInterne = controleurVueInterne;
    }

    public void setTabCompte(Tab tabCompte) {
        this.tabCompte = tabCompte;
    }

    public void setTabNouveauProduit(Tab tabNouveauProduit) {
        this.tabNouveauProduit = tabNouveauProduit;
    }

    public void setTabNouvelleCritique(Tab tabNouvelleCritique) {
        this.tabNouvelleCritique = tabNouvelleCritique;
    }

    public void setTabStatistique(Tab tabStatistique) {
        this.tabStatistique = tabStatistique;
    }

    public void setTabVisualisationProduit(Tab tabVisualisationProduit) {
        this.tabVisualisationProduit = tabVisualisationProduit;
    }

    public NouveauProduitControleur getControleurVueInterne() {
        return controleurVueInterne;
    }

    public Tab getTabCompte() {
        return tabCompte;
    }

    public Tab getTabNouveauProduit() {
        return tabNouveauProduit;
    }

    public Tab getTabNouvelleCritique() {
        return tabNouvelleCritique;
    }

    public Tab getTabStatistique() {
        return tabStatistique;
    }

    public Tab getTabVisualisationProduit() {
        return tabVisualisationProduit;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }


}
