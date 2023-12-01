package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.events.FiltresEvent;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.*;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services.DB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableView<Critique> critiqueTableView;

    @FXML
    private TableColumn<Critique, LocalDate> dateTableColumn;

    @FXML
    private TableColumn<Critique, Integer> numeroTableColumn;

    @FXML
    private TableColumn<Critique, String> produitsCritiqueColumn;

    @FXML
    void initialize() {
        afficherCritiques();
    }

    public void afficherCritiques() {
        List<Critique> critiques = new ArrayList<>();

        bd.getCritiquesService().getCritiqueRepo().findAll().forEach(critiques::add);
        populerTableView(critiques);
    }

    public void populerTableView(List<Critique> critiques) {
        dateTableColumn.setCellValueFactory(new PropertyValueFactory<>("dateCritique"));
        numeroTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        produitsCritiqueColumn.setCellValueFactory(new PropertyValueFactory<>("critiqueLienProduits"));
        ObservableList<Critique> data = FXCollections.observableArrayList(critiques);
        critiqueTableView.setItems(data);
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
