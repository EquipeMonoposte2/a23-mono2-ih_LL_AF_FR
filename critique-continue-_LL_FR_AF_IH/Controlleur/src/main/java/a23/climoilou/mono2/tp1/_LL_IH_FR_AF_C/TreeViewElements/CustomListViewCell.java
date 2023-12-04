package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.TreeViewElements;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Items.ListItemI;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class CustomListViewCell extends ListCell<ListItemI> {
    private Label nom;
    private Label nomUtilisateur;

    private ImageView imageUtilisateur;

    public CustomListViewCell() {
        super();
        nom = new Label();
        nomUtilisateur = new Label();
        imageUtilisateur = new ImageView("iconeUtilisateur.png");
        imageUtilisateur.maxWidth(10);
        imageUtilisateur.maxHeight(10);
        imageUtilisateur.setFitHeight(50);
        imageUtilisateur.setFitWidth(50);

        VBox vBox = new VBox(nom, nomUtilisateur, imageUtilisateur);
        setGraphic(vBox);
    }

    @Override
    protected void updateItem(ListItemI item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty && item != null) {
            setText(item.affiche());
        }
    }
}
