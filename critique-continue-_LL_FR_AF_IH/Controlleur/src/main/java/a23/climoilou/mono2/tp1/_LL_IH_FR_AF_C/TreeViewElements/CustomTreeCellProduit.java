package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.TreeViewElements;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.TreeViewElements.Items.ProduitAlphabeltical;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.TreeViewElements.Items.ProduitItemI;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomTreeCellProduit extends TextFieldTreeCell<ProduitItemI> {
    private ContextMenu leafMenu;
    private String colorChanged;

    public CustomTreeCellProduit() {
        this.leafMenu = new ContextMenu();
        MenuItem menuItem = new MenuItem("Changer La couleur");

        menuItem.setOnAction(x->{
            Dialog<Color> dialog = new Dialog<>();
            dialog.setTitle("Changer la couleur :o)");
            dialog.setHeaderText("Nos Super Jeux");

            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE, ButtonType.OK);

            ColorPicker colorPicker = new ColorPicker();

            dialog.getDialogPane().setContent(colorPicker);

            dialog.setResultConverter(reponse -> {

                if(reponse == ButtonType.OK){
                    return colorPicker.getValue();
                }


                return null;
            });

           Optional<Color> reponse = dialog.showAndWait();

           reponse.ifPresent(couleur -> {
               colorChanged = couleur.toString();
               updateItem(getItem(), isEmpty());
           });
            this.colorChanged = reponse.get().toString();


        });

        leafMenu.getItems().add(menuItem);
    }


    @Override
    public void updateItem(ProduitItemI produit, boolean empty){
        super.updateItem(produit, empty);
        if(empty ||  produit==null){
            setText(null);
            setGraphic(null);
        } else {
            if (produit instanceof ProduitAlphabeltical) {
                setContextMenu(leafMenu);

                setTextFill(this.colorChanged != null ? Paint.valueOf(this.colorChanged): Color.BROWN);
            } else {
                setTextFill(Color.BLUE);
            }

            setText(produit.Show());
        }

    }
}
