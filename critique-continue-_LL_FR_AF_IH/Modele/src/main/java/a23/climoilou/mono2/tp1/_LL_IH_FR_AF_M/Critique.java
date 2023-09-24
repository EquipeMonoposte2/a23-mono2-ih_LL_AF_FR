package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

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

    private Date dateCritque;

    @ManyToOne
    @JoinColumn(name="utilisateur_id")
    private Utilisateur utilisateur;

    @ManyToMany
    @JoinTable(
            name = "critique_produit",
            joinColumns = @JoinColumn(name = "critique_id"),
            inverseJoinColumns = @JoinColumn(name = "produit_id"))
    private LinkedList<Produit> listeProduits = new LinkedList<>();

}
