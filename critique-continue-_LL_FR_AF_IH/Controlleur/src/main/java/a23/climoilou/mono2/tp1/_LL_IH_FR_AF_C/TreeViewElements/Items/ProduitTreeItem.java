package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.TreeViewElements.Items;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Produit;
import javafx.scene.image.ImageView;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public class ProduitTreeItem implements ProduitItemI{

    @Getter
    @Setter
    private Produit innerProduit;

    @Getter
    @Setter
    private ImageView image;

    @Getter
    @Setter
    private String nom;

    public ProduitTreeItem(Produit innerProduit) {
        this.innerProduit = innerProduit;
        this.nom = this.innerProduit.getNom();
    }

    public ProduitTreeItem(Produit innerProduit, ImageView image, String nom) {
        this.innerProduit = innerProduit;
        this.image = image;
        this.nom = nom;
    }


}
