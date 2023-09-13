package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jdk.jshell.execution.Util;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Entity
public class Critique {

    @Id
    private Long id;
    private int note;

    @ManyToOne
    private Utilisateur utilisateur;

    @ManyToOne
    private Produits listeDeProduit;
}
