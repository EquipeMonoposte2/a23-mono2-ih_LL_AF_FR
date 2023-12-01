package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.events;

import javafx.scene.control.Tab;

public class TabPaneEvent
{
    private String value;

    public TabPaneEvent(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }
}
