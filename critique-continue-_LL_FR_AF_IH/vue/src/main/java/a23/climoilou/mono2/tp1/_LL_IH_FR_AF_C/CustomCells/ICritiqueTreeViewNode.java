package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.CustomCells;

import java.time.LocalDate;
import java.util.List;

public interface ICritiqueTreeViewNode
{
    LocalDate getDateCritique();
    List<ICritiqueTreeViewNode> getChildren();
}