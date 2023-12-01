package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.TreeViewElements;


import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Developpeur;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Items.Dossier;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Items.InfoSupplementaire;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Items.TreeItemI;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services.DB;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.TypeParticipant;
import jakarta.transaction.Transactional;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.paint.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Scope("prototype")
public class CustomTreeCell extends TextFieldTreeCell<TreeItemI> {

    private DB db;
    private ContextMenu leafMenu;



    @Autowired
    public CustomTreeCell(DB db) {
        this.db =db;
        this.leafMenu = new ContextMenu();
        leafMenu.getItems().add(retMenu());

    }

    @Transactional
    public MenuItem retMenu(){
        MenuItem prendreNotesEtudiant = new MenuItem("Modifier note");
        prendreNotesEtudiant.setOnAction((ae)->{
            boolean isNote = false;
            int val = 0;
            do{
                TextInputDialog inputDialog =new TextInputDialog();
                Optional optional = inputDialog.showAndWait();

                String st = String.valueOf(optional.get());


                try {
                    val = Integer.parseInt(st);
                }catch (NumberFormatException e){

                }

                if (val>-1 && val<101){
                    isNote= true;
                }

            }while(!isNote);

            update(String.valueOf(val));

            ((Developpeur) super.getItem()).setNote(String.valueOf(val));
        });
        return prendreNotesEtudiant;
    }

    @Transactional
    public void update(String val){
        db.getDevService().updateWithID(String.valueOf(val),((Developpeur) super.getItem()).getId());
    }


    @Override
    public void updateItem(TreeItemI item, boolean empty) {
        super.updateItem(item, empty);

        if (!empty && item!=null){
            if(item instanceof Developpeur){
                if (((Developpeur) item).getTypeParticipant()== TypeParticipant.etudiant){
                    setContextMenu(leafMenu);
                    setTextFill(Color.GREEN);
                }

                if (((Developpeur) item).getTypeParticipant()==TypeParticipant.professeur){
                    setTextFill(Color.BLUE);
                }
            }

            if (item instanceof InfoSupplementaire){
                    setTextFill(Color.BLACK);

            }

            if (item instanceof Dossier){
                setTextFill(Color.BLACK);

            }

            setText(item.affiche());

        }
    }
}
