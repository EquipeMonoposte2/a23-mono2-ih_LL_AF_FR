package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

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




    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private UtilisateurParType parent;

    @OneToMany(mappedBy = "parent")
    private List<UtilisateurParType> children;

    @Override
    public String toString() {
        return "UtilisateurParType{" +
                "type=" + type +
                ", parent=" + parent +
                ", children=" + children +
                '}';
    }
}
