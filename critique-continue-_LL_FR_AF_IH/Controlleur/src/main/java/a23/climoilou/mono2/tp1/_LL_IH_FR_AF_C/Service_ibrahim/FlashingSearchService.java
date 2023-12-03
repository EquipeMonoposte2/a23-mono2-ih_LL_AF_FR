package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.Service_ibrahim;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.util.Duration;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class FlashingSearchService extends ScheduledService<String> {
    private List<String> listeDecouleurs;


    public void setListeDecouleurs(String couleur) {
        this.listeDecouleurs.add(couleur);
    }

    public int getListeDecouleurs() {
        return listeDecouleurs.size();
    }

    public FlashingSearchService() {
        setPeriod(Duration.seconds(1));
        this.listeDecouleurs = new ArrayList<>();
    }



    @Override
    protected Task<String> createTask() {
        this.setListeDecouleurs(Color.BLUE.toString());
        this.setListeDecouleurs(Color.CYAN.toString());
        this.setListeDecouleurs(Color.ORANGE.toString());
        this.setListeDecouleurs(Color.orange.toString());


        return new Task<String>() {
            @Override
            protected String call() throws Exception {
                Random random = new Random();
                int indexCouleur = random.nextInt(listeDecouleurs.size());
                String retourne = "";

                if(listeDecouleurs.get(indexCouleur) == null) {
                    retourne = Color.PINK.toString();
                    updateValue(retourne);

                } else {
                    retourne = listeDecouleurs.get(indexCouleur);
                    updateValue(retourne);
                }
                return retourne;
            }
        };
    }
}
