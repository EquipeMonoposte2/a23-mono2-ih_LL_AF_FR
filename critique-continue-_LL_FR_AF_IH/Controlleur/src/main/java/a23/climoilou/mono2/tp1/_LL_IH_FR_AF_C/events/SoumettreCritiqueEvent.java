package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.events;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Critique;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SoumettreCritiqueEvent
{
    private Critique nouvelleCritique;
}