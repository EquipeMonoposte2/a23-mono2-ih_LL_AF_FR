package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.events;

import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDate;


@Builder
@Getter
public class FiltresEvent extends ApplicationEvent {
    private boolean estAmateur;
    private boolean estInfluenceur;
    private boolean estExpert;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    public FiltresEvent(boolean estAmateur, boolean estInfluenceur, boolean estExpert, LocalDate dateDebut, LocalDate dateFin) {
        super(new Object());
        this.estAmateur = estAmateur;
        this.estInfluenceur = estInfluenceur;
        this.estExpert = estExpert;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }


    public boolean isEstAmateur() {
        return estAmateur;
    }

    public boolean isEstInfluenceur() {
        return estInfluenceur;
    }

    public boolean isEstExpert() {
        return estExpert;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }
}
