package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.TreeViewElements.Items;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Produit;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.Getter;
import lombok.Setter;


public class ProduitTreeItem implements ProduitItemI{

    @Getter
    @Setter
    private Produit innerProduitTree;

    @Getter
    @Setter
    private ImageView image;



    @Getter
    @Setter
    private char lettre;

    public ProduitTreeItem(Produit innerProduitTree) {
        this.innerProduitTree = innerProduitTree;
    }

    public ProduitTreeItem(Produit innerProduitTree, ImageView path) {
        this.image = path;
        this.innerProduitTree = innerProduitTree;
    }

    @Override
    public String toString() {
        return this.image + this.innerProduitTree.getNom();
    }

    @Override
    public String Show() {
        return this.innerProduitTree.getNom();
    }
}
