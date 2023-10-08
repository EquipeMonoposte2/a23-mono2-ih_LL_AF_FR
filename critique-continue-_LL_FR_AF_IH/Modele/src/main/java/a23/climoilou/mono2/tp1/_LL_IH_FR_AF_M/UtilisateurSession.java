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

    private Utilisateur utilisateur;

    @Autowired
    public void setBd(DB bd) {
        this.bd = bd;
    }
    /**
     * Connection utilisateur
     * @param identifiantUtilisateur
     */
    public void connection(String identifiantUtilisateur){
        this.setIdentifiantUtilisateur(identifiantUtilisateur);
        this.utilisateur = bd.getUtilisateursService().getUtilisateurRepo().findFirstByIdentifiant(identifiantUtilisateur);
    }

    /**
     * Deconnection utilisateur
     */
    public void deconnection(){
        this.setIdentifiantUtilisateur("");
        this.utilisateur = null;
    }

    /**
     * Modification utilisateur
     * @param utilisateur
     */
    public void modificationSession(Utilisateur utilisateur){
        //update session
        this.getUtilisateur().setType(utilisateur.getType());
        this.getUtilisateur().setNom(utilisateur.getNom());
        this.getUtilisateur().setDateDeNaissance(utilisateur.getDateDeNaissance());
        this.getUtilisateur().setCritiqueList(utilisateur.getCritiqueList());
    }
}
