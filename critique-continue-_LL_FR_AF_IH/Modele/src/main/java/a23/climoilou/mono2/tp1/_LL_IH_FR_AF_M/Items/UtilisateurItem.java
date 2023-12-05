package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Items;

import javafx.scene.image.ImageView;

public class UtilisateurItem implements ListItemI{
    private String nom;
    private String nomUtilisateur;

    private ImageView imageUtilisateur;

    public UtilisateurItem(String nom, String nomUtilisateur, ImageView imageUtilisateur) {
        this.nom = nom;
        this.nomUtilisateur = nomUtilisateur;
        this.imageUtilisateur = imageUtilisateur;
    }

    @Override
    public String affiche() {
        return nomUtilisateur + "(" + nom + ")";
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public ImageView getImageUtilisateur() {
        return imageUtilisateur;
    }

    public void setImageUtilisateur(ImageView imageUtilisateur) {
        this.imageUtilisateur = imageUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }
}
