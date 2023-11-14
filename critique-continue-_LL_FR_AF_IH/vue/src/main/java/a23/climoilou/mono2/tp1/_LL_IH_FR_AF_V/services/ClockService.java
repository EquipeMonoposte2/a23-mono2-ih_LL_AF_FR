package services;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ClockService extends ScheduledService<String> {
    @Override
    protected Task<String> createTask() {
        return null;
    }

    public class ClockTask extends Task<String>{

        @Override
        protected String call() throws Exception {

            LocalDateTime localDateTime = LocalDateTime.now();
            String now = localDateTime.getYear()+"-"+localDateTime.getMonthValue()+"-"+localDateTime.getDayOfMonth()+ "  "+ localDateTime.getHour()+":"+localDateTime.getMinute();
            updateValue(now);

            return now;
        }
    }
}
