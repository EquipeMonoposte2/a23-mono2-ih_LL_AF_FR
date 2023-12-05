package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.TreeViewElements;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.CritiqueTreeViewNode;
import org.springframework.stereotype.Component;

@Component
public class CritiqueTreeViewNodeCompatibleFactory
{

    public CritiqueTreeViewNodeCompatible createCritiqueTreeViewNodeCompatible(CritiqueTreeViewNode critiqueTreeViewNode)
    {
        return new CritiqueTreeViewNodeCompatible(critiqueTreeViewNode.getId(), critiqueTreeViewNode.getDateCritique(), critiqueTreeViewNode.getChildren());
    }

}