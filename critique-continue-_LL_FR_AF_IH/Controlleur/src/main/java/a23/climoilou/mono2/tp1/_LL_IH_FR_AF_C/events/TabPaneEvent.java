package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.events;

import javafx.scene.control.Tab;

public class TabPaneEvent
{
    private Tab value;

    public TabPaneEvent(Tab value)
    {
        this.value = value;
    }

    public Tab getValue()
    {
        return value;
    }

    public void setValue(Tab value)
    {
        this.value = value;
    }
}
