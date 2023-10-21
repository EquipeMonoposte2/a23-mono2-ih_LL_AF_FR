package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("AProposVue.fxml")
@PropertySource("classpath:application.properties")
public class AProposControleur implements Initializable {


    @Value("${propriete.nomEquipe}")
    private String nomEquipe;

    @Value("${propriete.nomProfesseur}")
    private String nomProf;

    @Value("${propriete.nomProduit}")
    private String nomProjet;


    @FXML
    private Text textEquipe;

    @FXML
    private Text textNomProf;

    @FXML
    private Text textNomProjet;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textEquipe.setText(nomEquipe);
        textNomProf.setText(nomProf);
        textNomProjet.setText(nomProjet);
    }
}
