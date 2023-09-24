package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView("critiqueTabView.fxml")
public class CritiqueTabViewControleur {

    @FXML
    private TableColumn<?, ?> coteTableColumn;

    @FXML
    private TableView<?> critiqueTableView;

    @FXML
    private TableColumn<?, ?> nomTableColumn;

    @FXML
    void initialize() {

    }

}
