package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Items;

import javafx.scene.image.ImageView;

public class Dossier implements TreeItemI {

    private String Name;

    private ImageView imageIcon;

    public Dossier(String name, ImageView imageIcon) {
        Name = name;
        this.imageIcon = imageIcon;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public ImageView getImageIcon() {
        return imageIcon;
    }

    @Override
    public String toString() {
        return "Dossier{" +
                "Name='" + Name + '\'' +
                ", imageIcon=" + imageIcon +
                '}';
    }

    public void setImageIcon(ImageView imageIcon) {
        this.imageIcon = imageIcon;
    }

    @Override
    public String affiche() {
        return Name;
    }
}
