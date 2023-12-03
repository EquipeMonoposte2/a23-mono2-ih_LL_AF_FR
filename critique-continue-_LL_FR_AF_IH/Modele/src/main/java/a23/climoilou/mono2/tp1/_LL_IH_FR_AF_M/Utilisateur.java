package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//where annotation @EqualsAndHashCode.Include est présente
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Builder
@Component
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;

    @Column(unique = true)
    private String identifiant;

    private LocalDate dateDeNaissance;

    @Enumerated(EnumType.STRING)
    private Type type;

    @OneToMany(mappedBy = "utilisateur")
    @Builder.Default
    @ToString.Exclude
    private List<Critique> critiqueList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "utilisateurParCategorie_id")
    private UtilisateurParCategorie utilisateurParCategorie;



}
