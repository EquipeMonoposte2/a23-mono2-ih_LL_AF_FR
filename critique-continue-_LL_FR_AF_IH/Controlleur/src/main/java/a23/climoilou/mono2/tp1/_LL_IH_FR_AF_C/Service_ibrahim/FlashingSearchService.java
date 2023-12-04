package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.Service_ibrahim;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class FlashingSearchService extends ScheduledService<Color> {
    private List<Color> listeDecouleurs;


    public void setListeDecouleurs(Color couleur) {
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
    protected Task<Color> createTask() {
        this.setListeDecouleurs(Color.BLUE);
        this.setListeDecouleurs(Color.CYAN);
        this.setListeDecouleurs(Color.ORANGE);
        this.setListeDecouleurs(Color.ORANGE);


        return new Task<Color>() {
            @Override
            protected Color call() throws Exception {
                Random random = new Random();
                int indexCouleur = random.nextInt(listeDecouleurs.size());
                Color retourne = null;

                if(listeDecouleurs.get(indexCouleur) == null) {
                    retourne = Color.PINK;
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
