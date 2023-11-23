package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_V.services.ClockService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.util.Duration;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

import static java.awt.Color.*;

@Component
@Scope("prototype")
@FxmlView("ClockView.fxml")
public class ClockControleur implements Initializable {

    @FXML
    private Label date;

    @FXML
    private Label time;

    private ClockService clockService;
    private ApplicationContext context;

    @Autowired
    public void setContext(ApplicationContext context) {
        this.context = context;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //this.compteControleur.setUtilisateur(this.utilisateur);
        System.out.println("test");
        clockService = context.getBean(ClockService.class);
        //refresh à chaque 1 seconde
        clockService.setPeriod(Duration.millis(1000));
        clockService.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue!=null){
                    String[] vals = newValue.split("&");

                    date.setText(vals[0]);
                    time.setText(vals[1]);
                }
            }
        });

        clockService.messageProperty().addListener((ob,o,n)->{
            System.out.println(n);
            if (n!=null){
                if (n.equals("flash")) {
                    time.setTextFill(javafx.scene.paint.Color.RED);
                }
            }
            else {
                time.setTextFill(javafx.scene.paint.Color.BLACK);
            }
        });

        clockService.restart();
    }
}
