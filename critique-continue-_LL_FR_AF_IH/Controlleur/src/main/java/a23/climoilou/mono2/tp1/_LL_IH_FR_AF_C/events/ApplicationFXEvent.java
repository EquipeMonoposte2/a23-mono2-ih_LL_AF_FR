package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.events;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Utilisateur;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.UtilisateurSession;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Getter
@Setter
@Builder
public class ApplicationFXEvent {

    private boolean estConnectionEvent;
    private boolean estCreationCompteEvent;
    private boolean estNouveauCompteEvent;
    private boolean estDeconnectionEvent;
    private UtilisateurSession utilisateur;

    public ApplicationFXEvent(boolean estConnectionEvent, boolean estCreationCompteEvent, boolean estNouveauCompteEvent, boolean estDeconnectionEvent, UtilisateurSession utilisateur) {
        this.estConnectionEvent = estConnectionEvent;
        this.estCreationCompteEvent = estCreationCompteEvent;
        this.estNouveauCompteEvent = estNouveauCompteEvent;
        this.estDeconnectionEvent = estDeconnectionEvent;
        this.utilisateur = utilisateur;
    }
}
