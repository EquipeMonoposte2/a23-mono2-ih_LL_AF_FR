package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs;

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
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@FxmlView("ProduitsTableView.fxml")
public class ProduitsTableViewControleur {

    private DB bd;

    @FXML
    private TableColumn<Produit, Integer> coteTableColumn;

    @FXML
    private TableView<Produit> produitsTableView;

    @FXML
    private TableColumn<Produit, String> nomTableColumn;

    @EventListener
    public void afficherProduits(FiltresEvent event) {
        List<Produit> produits = new ArrayList<>();

        bd.getProduitsService().getProduitRepository().findAll().forEach(produits::add);
        Filtre filtre = Filtre.builder().build();
        produits = filtre.filtrerProduits(event.getTypesUtilisateurs(), event.getDateDebut(), event.getDateFin(), produits);
        populerTableView(produits);
    }
    public void populerTableView(List<Produit> produits) {
        nomTableColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        coteTableColumn.setCellValueFactory(new PropertyValueFactory<>("cote"));
        ObservableList<Produit> data = FXCollections.observableArrayList(produits);
        produitsTableView.setItems(data);
    }

    @Autowired
    public void setBd(DB bd) {
        this.bd = bd;
    }
}
