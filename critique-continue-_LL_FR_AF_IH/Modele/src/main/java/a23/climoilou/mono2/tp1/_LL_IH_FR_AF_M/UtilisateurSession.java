package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M;


import lombok.*;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
public class UtilisateurSession {

    private UtilisateurSession isUserConnected;

    private long idUtilisateurConnected;
    private Type droitUtilisateurConnected;
    private String identifiant;


    public UtilisateurSession logIn(long idUtilisateur, Type permission, String identifiant){
        if(isUserConnected == null) {
            isUserConnected =
                    builder().
                            idUtilisateurConnected(idUtilisateur).
                            droitUtilisateurConnected(permission).
                            identifiant(identifiant).build();
        }
        return isUserConnected;
    }

    public boolean logout(){
        this.isUserConnected = null;
        this.idUtilisateurConnected = -1;
        this.droitUtilisateurConnected = null;
        return true;
    }




}
