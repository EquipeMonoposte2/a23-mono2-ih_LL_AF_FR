package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.events;


import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Utilisateur;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.UtilisateurSession;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;


/**
 * Event custom qui est utilisé pour la connexion à l'application main (Navigation)
 */
public class ConnectionEvent extends ApplicationEvent {


    private String message ;

    private UtilisateurSession utilisateur;

    /**
     * Constructeur recoit la source de l'événement, un message custom (test) et un utilisateur
     * @param source
     * @param message
     * @param utilisateur
     */
    public ConnectionEvent(Object source, String message,UtilisateurSession utilisateur) {
        super(source);
        this.message = message;
        this.utilisateur = utilisateur;
    }

    public String getMessage() {
        return message;
    }

    public UtilisateurSession getUtilisateur() {
        return utilisateur;
    }
}
