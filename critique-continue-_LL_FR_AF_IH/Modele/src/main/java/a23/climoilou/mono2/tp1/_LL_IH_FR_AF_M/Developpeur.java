package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M;



import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Items.TreeItemI;
import jakarta.persistence.*;
import javafx.scene.image.ImageView;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Builder
public class Developpeur implements TreeItemI {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String information;

    @Enumerated(EnumType.STRING)
    private TypeParticipant typeParticipant;

    private String note;


    public Developpeur(String information, TypeParticipant typeParticipant, String note, ImageView icon) {
        this.information = information;
        this.typeParticipant = typeParticipant;
        this.note = note;
    }


    @Override
    public String affiche() {
        return this.typeParticipant == TypeParticipant.etudiant ? "nom : "+ information + "  type : "+typeParticipant.toString() +"  note : "+note : "nom : "+ information+ "  type : "+typeParticipant.toString() ;
    }
}
