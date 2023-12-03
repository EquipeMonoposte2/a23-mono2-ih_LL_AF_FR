package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CritiqueTreeViewNode {
    private Long id;
    private LocalDate dateCritique;
    private List<CritiqueTreeViewNode> children;

    public CritiqueTreeViewNode(Long id, LocalDate dateCritique) {
        this.id = id;
        this.dateCritique = dateCritique;
        this.children = new ArrayList<>();
    }

    public void addChild(CritiqueTreeViewNode child) {
        this.children.add(child);
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public LocalDate getDateCritique()
    {
        return dateCritique;
    }

    public void setDateCritique(LocalDate dateCritique)
    {
        this.dateCritique = dateCritique;
    }

    public List<CritiqueTreeViewNode> getChildren()
    {
        return children;
    }

    public void setChildren(List<CritiqueTreeViewNode> children)
    {
        this.children = children;
    }
}