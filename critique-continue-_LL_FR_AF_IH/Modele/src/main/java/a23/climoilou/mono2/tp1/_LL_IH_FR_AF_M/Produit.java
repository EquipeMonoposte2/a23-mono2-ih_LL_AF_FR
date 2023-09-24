package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//where annotation @EqualsAndHashCode.Include est présente
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Builder
@Table(name = "Produit")
public class Produit {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true )
    private String nom;

    private String description;

    private LocalDateTime dateDeSortie;

    //Pour l'instant c'est un string, pour le path, s'il y a une meilleure facon ont changera.
    private String image;

    @OneToMany(mappedBy = "produit")
    @Builder.Default
    private List<Critique> listeDeCritique = new ArrayList<>();
}
