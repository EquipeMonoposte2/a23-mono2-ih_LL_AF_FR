package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.TreeViewElements;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.CustomCells.ICritiqueTreeViewNode;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.CritiqueTreeViewNode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CritiqueTreeViewNodeCompatible implements ICritiqueTreeViewNode
{

    private LocalDate dateCritique;
    private Long id;
    private List<ICritiqueTreeViewNode> children;

    public CritiqueTreeViewNodeCompatible(Long id, LocalDate dateCritique, List<CritiqueTreeViewNode> children) {
        this.id = id;
        this.dateCritique = dateCritique;
        this.children = new ArrayList<>();
        if(children != null)
        {
            // TODO algo recursif ici?
            for(CritiqueTreeViewNode child : children)
            {
                this.children.add(new CritiqueTreeViewNodeCompatible(child.getId(), child.getDateCritique(), child.getChildren()));
            }
        }
    }

    @Override
    public LocalDate getDateCritique()
    {
        return dateCritique;
    }

    @Override
    public List<ICritiqueTreeViewNode> getChildren()
    {
        return children;
    }
}