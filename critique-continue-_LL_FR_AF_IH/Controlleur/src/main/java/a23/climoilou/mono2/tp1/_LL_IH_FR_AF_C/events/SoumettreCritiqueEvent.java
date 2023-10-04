package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.events;

import org.springframework.context.ApplicationEvent;

public class SoumettreCritiqueEvent extends ApplicationEvent
{
    public SoumettreCritiqueEvent(Object source)
    {
        super(source);
    }
}