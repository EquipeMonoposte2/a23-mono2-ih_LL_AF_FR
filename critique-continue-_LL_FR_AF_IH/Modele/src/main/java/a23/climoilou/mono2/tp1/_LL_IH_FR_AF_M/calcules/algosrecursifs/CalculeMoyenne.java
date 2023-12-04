package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.calcules.algosrecursifs;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Developpeur;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Items.TreeItemI;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.TypeParticipant;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import org.springframework.stereotype.Component;

@Component
public class CalculeMoyenne {


    public int compte(TreeItem<TreeItemI> treeItem){
        int retval = 0;
        if (treeItem!=null){
            if (treeItem.getValue() instanceof Developpeur){
                if (((Developpeur) treeItem.getValue()).getTypeParticipant() == TypeParticipant.etudiant){
                    retval+=1;
                }
            }

            retval +=  compte(treeItem.getChildren().stream().findFirst().orElse(null));

            retval += compte(treeItem.nextSibling());
        }

        return retval;
    }

    public double moyenne(TreeItem<TreeItemI> treeItem){

        double moyenne = 0;

        if (treeItem!=null){
            if (treeItem.getValue() instanceof Developpeur){
                if (((Developpeur) treeItem.getValue()).getTypeParticipant() == TypeParticipant.etudiant){
                    String val = ((Developpeur) treeItem.getValue()).getNote();
                    moyenne+=Double.valueOf(val);
                }
            }

            moyenne +=  moyenne(treeItem.getChildren().stream().findFirst().orElse(null));

            moyenne += moyenne(treeItem.nextSibling());
        }


        return moyenne;
    }

}
