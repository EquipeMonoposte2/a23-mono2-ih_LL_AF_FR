package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.events;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Utilisateur;
import org.springframework.context.ApplicationEvent;


public class CreationCompteEvent extends ApplicationEvent {

    private Utilisateur utilisateur;

    /**
     * Constructeur
     * @param source
     * @param utilisateur
     */
    public CreationCompteEvent(Object source, Utilisateur utilisateur) {
        super(source);
        this.utilisateur = utilisateur;
    }

    public CreationCompteEvent(Object source) {
        super(source);
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
