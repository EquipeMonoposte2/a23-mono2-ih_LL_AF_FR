package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.events;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Categorie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class CategorieEvent {
    private Categorie choix;
    private Categorie nouvelleCategorie;
}
