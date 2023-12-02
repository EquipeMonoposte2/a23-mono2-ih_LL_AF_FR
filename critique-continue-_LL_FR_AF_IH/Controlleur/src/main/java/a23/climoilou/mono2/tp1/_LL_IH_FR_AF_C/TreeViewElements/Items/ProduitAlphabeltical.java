package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.TreeViewElements.Items;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Produit;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.Getter;
import lombok.Setter;

public class ProduitAlphabeltical implements ProduitItemI{
    @Getter
    @Setter
    private ProduitTreeItem innerProduitTree;

    @Getter
    @Setter
    private Image image;



    @Getter
    @Setter
    private char lettre;

    @Getter
    @Setter
    private String AfficheRoot;

    @Getter
    @Setter
    private boolean isRoot;

    /**
     * Pour les childs
     * @param innerProduitTree
     * @param lettre
     */
    public ProduitAlphabeltical(ProduitTreeItem innerProduitTree, char lettre) {
        this.innerProduitTree = innerProduitTree;
        this.lettre = lettre;
        this.isRoot = false;

    }


    /**
     * Pour le root element
     * @param afficheRoot
     */
    public ProduitAlphabeltical(String afficheRoot) {
        AfficheRoot = afficheRoot;
        this.isRoot = true;
    }

    public ProduitAlphabeltical(String path, char lettre) {
        this.image = new Image(path);
        this.lettre = lettre;
        this.isRoot = false;
//        this.image.setImage(path);
    }

//    public ProduitAlphabeltical(ProduitTreeItem innerProduitTree, char lettre) {
//        this.innerProduitTree = innerProduitTree;
//        this.lettre = lettre;
//        this.isRoot = false;
//    }

    @Override
    public String toString() {
        return this.isRoot ? image +  this.AfficheRoot :  image + " " + this.getLettre();
    }


    @Override
    public String Show() {
        return "" + this.lettre;
    }
}
