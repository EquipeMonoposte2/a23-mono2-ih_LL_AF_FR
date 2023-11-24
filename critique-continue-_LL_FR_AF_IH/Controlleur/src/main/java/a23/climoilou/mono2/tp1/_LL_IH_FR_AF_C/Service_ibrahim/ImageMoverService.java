package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.Service_ibrahim;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class ImageMoverService extends ScheduledService<Double> {


    @Getter
    @Setter
    private double xMax;

    @Setter
    @Getter
    private double xMin;

    @Setter
    @Getter
    private double currentX;



    @Setter
    @Getter
    private boolean versLeMax = true;





    @Override
    protected Task<Double> createTask() {
        return new Task<Double>() {
            @Override
            protected Double call() throws Exception {

                while (true){

                    if(versLeMax){
                        setCurrentX(getCurrentX() + 1.0);
                        if(getCurrentX() >= getXMax()){
                            versLeMax = false;
                        }

                    }else {
                        setCurrentX(getCurrentX() - 1.0);
                        if(getCurrentX() <= getXMin()) {
                            versLeMax = true;
                        }

                    }
                        Thread.sleep(100);
                        updateValue(getCurrentX());

                }
            }

        };
    }
}
