package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//where annotation @EqualsAndHashCode.Include est présente
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Builder
public class Critique {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int note;

    @ManyToOne
    @JoinColumn(name="utilisateur_id")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;


}
