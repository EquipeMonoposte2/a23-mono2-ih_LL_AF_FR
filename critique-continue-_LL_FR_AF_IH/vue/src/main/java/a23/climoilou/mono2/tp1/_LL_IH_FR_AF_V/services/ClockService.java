package a23.climoilou.mono2.tp1._LL_IH_FR_AF_V.services;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ClockService extends ScheduledService<String> {
    @Override
    protected Task<String> createTask() {
        return new ClockTask();
    }

    public class ClockTask extends Task<String>{

        @Override
        protected String call() throws Exception {

            LocalDateTime localDateTime = LocalDateTime.now();
            String now = localDateTime.getYear()+"-"+localDateTime.getMonthValue()+"-"+localDateTime.getDayOfMonth()+ " & "+ localDateTime.getHour()+":"+localDateTime.getMinute();
            updateValue(now);

            if (localDateTime.getSecond()==0||localDateTime.getSecond()==1|| localDateTime.getSecond()==2 || localDateTime.getSecond()==3){
                updateMessage("flash");
            }else {
                updateMessage(null);
            }

            return now;
        }
    }
}
