package a23.climoilou.mono2.tp1._LL_IH_FR_AF;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * Main de l'application lance l'application
 */
@SpringBootApplication
@ComponentScan(basePackages = {"a23.climoilou.mono2.tp1._LL_IH_FR_AF.vuecontroleurs","a23.climoilou.mono2.tp1._LL_IH_FR_AF","a23.climoilou.mono2.tp1._LL_IH_FR_AF","net.rgielen.fxweaver.spring.boot.autoconfigure"})
public class CritiqueContinueLlFrAfIhApplication {

    public static void main(String[] args) {
        //SpringApplication.run(CritiqueContinueLlFrAfIhApplication.class, args);
        Application.launch(ApplicationFX.class);
    }

}