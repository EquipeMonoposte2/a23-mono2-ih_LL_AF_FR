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
public class CritiqueTabViewControleur{
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
        context.addApplicationListener(event -> rafraichirCritique(event));
    }

    @EventListener
    private void rafraichirCritique(ApplicationEvent event) {
        if (event instanceof FiltresEvent) {
            if (((FiltresEvent) event).isEstAmateur()) {
                //TODO
            }
            if (((FiltresEvent) event).isEstExpert()) {
                //TODO
            }
            if (((FiltresEvent) event).isEstExpert()) {
                //TODO
            }
            if (((FiltresEvent) event).isEstAmateur()) {
                //TODO
            }
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
