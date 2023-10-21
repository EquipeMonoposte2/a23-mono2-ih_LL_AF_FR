package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.events;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Type;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDate;
import java.util.Set;


@Builder
@Getter
@Setter
public class FiltresEvent{
    private Set<Type> typesUtilisateurs;
    private LocalDate dateDebut;
    private LocalDate dateFin;
}
