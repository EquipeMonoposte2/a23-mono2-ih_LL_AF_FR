package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Items;

public class InfoSupplementaire implements TreeItemI {

    private String titre;

    private String info;


    public InfoSupplementaire(String titre, String info) {
        this.titre = titre;
        this.info = info;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "InfoSupplementaire{" +
                "titre='" + titre + '\'' +
                ", info='" + info + '\'' +
                '}';
    }

    @Override
    public String affiche() {
        return titre+" : "+info;
    }
}
