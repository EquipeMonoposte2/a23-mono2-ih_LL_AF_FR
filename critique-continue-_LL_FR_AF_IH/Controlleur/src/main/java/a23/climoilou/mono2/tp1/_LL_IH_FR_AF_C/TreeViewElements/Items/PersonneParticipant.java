package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.TreeViewElements.Items;

import javafx.scene.image.ImageView;

public class PersonneParticipant implements TreeItemI{

    private String information;

    private TypeParticipant typeParticipant;

    private String note;

    private ImageView icon;

    public PersonneParticipant(String information, TypeParticipant typeParticipant, String note, ImageView icon) {
        this.information = information;
        this.typeParticipant = typeParticipant;
        this.note = note;
        this.icon = icon;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public TypeParticipant getTypeParticipant() {
        return typeParticipant;
    }

    public void setTypeParticipant(TypeParticipant typeParticipant) {
        this.typeParticipant = typeParticipant;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public ImageView getIcon() {
        return icon;
    }

    public void setIcon(ImageView icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "PersonneParticipant{" +
                "information='" + information + '\'' +
                ", typeParticipant=" + typeParticipant +
                ", note='" + note + '\'' +
                ", icon=" + icon +
                '}';
    }

    @Override
    public String affiche() {
        return this.typeParticipant == TypeParticipant.etudiant ? "nom : "+ information +"  note : "+note : "nom : "+ information;
    }
}
