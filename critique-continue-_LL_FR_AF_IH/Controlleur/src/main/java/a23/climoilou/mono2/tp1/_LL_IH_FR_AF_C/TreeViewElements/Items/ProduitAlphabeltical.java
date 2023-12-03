package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.TreeViewElements.Items;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Produit;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class ProduitAlphabeltical implements ProduitItemI{


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
     * Pour le root element
     * @param afficheRoot
     */
    public ProduitAlphabeltical(String afficheRoot) {
        AfficheRoot = afficheRoot;
        this.isRoot = true;
    }

    public ProduitAlphabeltical( char lettre) {
        this.lettre = lettre;
        this.isRoot = false;
    }


    public ProduitAlphabeltical() {
    }

    @Override
    public String toString() {
        return this.isRoot ? image +  this.AfficheRoot :  image + " " + this.getLettre();
    }


    @Override
    public String Show() {
        return "" + this.lettre;
    }
}
