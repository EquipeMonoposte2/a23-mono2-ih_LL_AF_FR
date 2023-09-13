package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Component
public class Produits {

    private Long id;
    @Column(name = "nom", unique = true )
    private String nom;
    private String description;

    private LocalDateTime dateDeSortie;

    //Pour l'instant c'est un string, pour le path, s'il y a une meilleure facon ont changera.
    private String image;

    @Id
    public Long getId() {
        return id;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "Critique_id")
    private Critique listeDeCritique;

    
}
