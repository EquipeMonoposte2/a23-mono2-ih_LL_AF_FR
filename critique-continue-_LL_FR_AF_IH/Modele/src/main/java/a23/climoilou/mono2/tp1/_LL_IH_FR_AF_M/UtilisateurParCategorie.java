package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.List;

@Entity
@Component
@Getter
@Setter
@NoArgsConstructor
public class UtilisateurParCategorie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private UtilisateurParCategorie parent;

    @OneToMany(mappedBy = "parent")
    private List<UtilisateurParCategorie> children;
}
