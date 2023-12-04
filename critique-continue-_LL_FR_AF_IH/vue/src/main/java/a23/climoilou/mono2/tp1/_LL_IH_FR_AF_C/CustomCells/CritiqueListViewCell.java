package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.CustomCells;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class CritiqueListViewCell extends ListCell<IProduitClassement>
{

    private Label nom;
    private Label difference;
    private Parent cellroot;

    public CritiqueListViewCell() throws IOException
    {
        FXMLLoader loader = new FXMLLoader(CritiqueListViewCell.class.getResource("CritiqueCustomCell.fxml"));
        cellroot = loader.load();
        this.nom = (Label) loader.getNamespace().get("nomJeuLabel");
        this.difference = (Label) loader.getNamespace().get("differenceLabel");
    }

    @Override
    public void updateItem(IProduitClassement produitClassement, boolean empty) {
        super.updateItem(produitClassement, empty);

        if (produitClassement == null || empty) {
            setItem(null);
            setGraphic(null);
        } else {
            nom.setText(produitClassement.getNom() + " " + (produitClassement.estNeutre()? "\t*": ""));
            difference.setText(produitClassement.getDifference());
            //setText(null);
            setGraphic(cellroot);
        }
    }
}