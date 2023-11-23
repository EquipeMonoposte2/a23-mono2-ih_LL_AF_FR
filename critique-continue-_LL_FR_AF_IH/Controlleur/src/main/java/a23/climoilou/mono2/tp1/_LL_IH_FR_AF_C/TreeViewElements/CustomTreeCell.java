package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.TreeViewElements;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.TreeViewElements.Items.InfoSupplementaire;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.TreeViewElements.Items.PersonneParticipant;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.TreeViewElements.Items.TreeItemI;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.TreeViewElements.Items.TypeParticipant;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.paint.Color;

import java.util.Optional;

public class CustomTreeCell extends TextFieldTreeCell<TreeItemI> {

    private ContextMenu leafMenu;

    public CustomTreeCell() {
        this.leafMenu = new ContextMenu();

        MenuItem prendreNotesEtudiant = new MenuItem("Prendre notes");
        prendreNotesEtudiant.setOnAction((ae)->{
            TextInputDialog inputDialog =new TextInputDialog();
            Optional optional = inputDialog.showAndWait();
            String st = String.valueOf(optional.get());
            ((PersonneParticipant) super.getItem()).setNote(((PersonneParticipant) super.getItem()).getNote()+" -"+st);
        });
        leafMenu.getItems().add(prendreNotesEtudiant);

    }

    @Override
    public void updateItem(TreeItemI item, boolean empty) {
        super.updateItem(item, empty);

        if (!empty && item!=null){
            if(item instanceof PersonneParticipant){
                if (((PersonneParticipant) item).getTypeParticipant()== TypeParticipant.etudiant){
                    setContextMenu(leafMenu);
                    setTextFill(Color.GREEN);
                }

                if (((PersonneParticipant) item).getTypeParticipant()==TypeParticipant.professeur){
                    setTextFill(Color.BLUE);
                }
            }

            if (item instanceof InfoSupplementaire){
                    setTextFill(Color.BLACK);

            }



            setText(item.affiche());


        }
    }
}
