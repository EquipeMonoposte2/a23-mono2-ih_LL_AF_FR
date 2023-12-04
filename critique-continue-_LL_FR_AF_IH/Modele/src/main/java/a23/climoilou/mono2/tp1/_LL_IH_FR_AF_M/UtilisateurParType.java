package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Entity
@Component
@Getter
@Setter
@NoArgsConstructor
@Scope("prototype")
public class UtilisateurParType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Enumerated(EnumType.STRING)
    private Type type;

    public UtilisateurParType(Type type) {
        this.type = type;
    }

    public boolean RajouterChild(UtilisateurParType child){
        return this.children.add(child);
    }


//    @ManyToOne(fetch = FetchType.LAZY)
@ManyToOne
@JoinColumn(name = "parent_id")
    private UtilisateurParType parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE)
    private Set<UtilisateurParType> children;

    @Override
    public String toString() {
        return this.type.toString();
    }
}
