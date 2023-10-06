package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.events.FiltresEvent;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Critique;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services.DB;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Type;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@FxmlView("critiqueTabView.fxml")
public class CritiqueTabViewControleur {
    private ConfigurableApplicationContext context;

    private DB bd;

    @FXML
    private TableColumn coteTableColumn;

    @FXML
    private TableView critiqueTableView;

    @FXML
    private TableColumn nomTableColumn;

    @FXML
    void initialize() {

    }

    @EventListener()
    private void rafraichirCritique(FiltresEvent event) {
        if ( event.isEstAmateur()) {
            //TODO
            System.out.println("un amateur");
        }
        if (event.isEstExpert()) {
            //TODO
            System.out.println("un expert");
        }
        if (event.isEstInfluenceur()) {
            //TODO
            System.out.println("un influenceur");
        }
        if(event.getDateDebut() != null){
            //TODO
        }
        if(event.getDateFin() != null){
            //TODO
        }
    }

    @Autowired
    public void setContext(ConfigurableApplicationContext context) {
        this.context = context;
    }

    @Autowired

    public void setBd(DB bd) {
        this.bd = bd;
    }
}