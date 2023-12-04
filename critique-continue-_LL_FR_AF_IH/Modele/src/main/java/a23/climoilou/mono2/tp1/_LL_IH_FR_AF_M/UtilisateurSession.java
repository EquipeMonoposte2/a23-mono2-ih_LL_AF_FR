package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services.DB;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * La session sert à garder l'identifiant unique de l'utilisateur actuel
 */
@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
public class UtilisateurSession {

    private DB bd;


    private String identifiantUtilisateur;

    private Type permission;

    private UtilisateurSession session;

    @Autowired
    public void setBd(DB bd) {
        this.bd = bd;
    }

    /**
     * Connection utilisateur
     * @param identifiantUtilisateur
     */
    public UtilisateurSession connection(String identifiantUtilisateur, Type permission){
        if(this.session == null) {
            this.session = UtilisateurSession.
                    builder().
                            identifiantUtilisateur(identifiantUtilisateur)
                            .permission(permission).build();
        }
        return session;

    }

    /**
     * Deconnection utilisateur
     */
    public boolean deconnection(){
        this.setIdentifiantUtilisateur("");
        this.setPermission(null);
        this.session = null;
        return true;
    }

    /**
     * Recupère l'utilisateur associé a l'identifiant connecté.
     * @return
     */
    public Utilisateur TrouverUtilisateurConnecter(){
        if(this.session != null) {
            return bd.getUtilisateursService().getUtilisateurRepo().findFirstByIdentifiant(session.getIdentifiantUtilisateur());
        }else {
            return null;
        }

    }

}
