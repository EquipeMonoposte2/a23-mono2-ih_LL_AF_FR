package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.TreeViewElements;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.TreeViewElements.Items.ProduitAlphabeltical;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.TreeViewElements.Items.ProduitItemI;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.paint.Color;



public class CustomTreeCellProduit extends TextFieldTreeCell<ProduitItemI> {
    private ContextMenu leafMenu;

    public CustomTreeCellProduit() {
        this.leafMenu = new ContextMenu();
        MenuItem menuItem = new MenuItem("Je suis tous les jeux correspondant a ma lettre");

        menuItem.setOnAction(x->{
            System.out.println("why");
        });

        leafMenu.getItems().add(menuItem);
    }


    @Override
    public void updateItem(ProduitItemI produit, boolean empty){
        super.updateItem(produit, empty);
        if(!empty && produit!=null){
            if(produit instanceof ProduitAlphabeltical){

                if(((ProduitAlphabeltical) produit).isRoot()){
                    setTextFill(Color.BROWN);
                }

                setContextMenu(leafMenu);
                setTextFill(Color.BROWN);
            }else{
                setTextFill(Color.BLUE);
            }

            setText(produit.Show());
        }

    }
}
