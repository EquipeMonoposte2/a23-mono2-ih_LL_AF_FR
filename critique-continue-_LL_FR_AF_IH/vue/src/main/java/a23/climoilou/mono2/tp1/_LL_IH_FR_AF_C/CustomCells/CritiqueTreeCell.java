package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.CustomCells;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.cell.TextFieldTreeCell;

public class CritiqueTreeCell extends TextFieldTreeCell<ICritiqueTreeViewNode>
{

    private ContextMenu menuContextuel;

    public CritiqueTreeCell() {
        MenuItem supprimer_critiqueItem = new MenuItem("Supprimer critique");

        supprimer_critiqueItem.setOnAction(event -> {
            getTreeItem().getParent().getChildren().remove(getTreeItem());
            this.getTreeView().refresh();
        });

        menuContextuel = new ContextMenu();
        menuContextuel.getItems().add(supprimer_critiqueItem);
    }

    @Override
    public void updateItem(ICritiqueTreeViewNode item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setContextMenu(menuContextuel);
            setText(null);
        } else {
            setText(formatCritiqueNode(item));
        }
    }

    private String formatCritiqueNode(ICritiqueTreeViewNode node) {
        if (node.getDateCritique() != null) {
            return node.getDateCritique().getYear() + " - " + node.getDateCritique().getMonth() + " - " + node.getDateCritique().getDayOfMonth();
        }
        return "Vos Critiques";
    }
}