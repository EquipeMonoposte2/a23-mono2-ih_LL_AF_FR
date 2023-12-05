package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.services;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ConnecteService extends ScheduledService<Void> {
    private Stage stage;
    private boolean flash = false;
    private double largeur;
    private double hauteur;

    public ConnecteService(Stage stage) {
        this.stage = stage;
        this.largeur = stage.getWidth();
        this.hauteur = stage.getHeight();
    }

    private void centerStage() {
        double newX = stage.getX() + (largeur - stage.getWidth()) / 2;
        double newY = stage.getY() + (hauteur - stage.getHeight()) / 2;
        stage.setX(newX);
        stage.setY(newY);
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<>() {
            @Override
            protected Void call() {
                SlowHelper.slow(1000);
                if (flash) {
                    stage.setWidth(largeur);
                    stage.setHeight(hauteur);
                } else {
                    stage.setWidth(largeur + 25);
                    stage.setHeight(hauteur + 25);
                }

                centrerFenetre();

                flash = !flash;
                return null;
            }
        };
    }

    public void centrerFenetre() {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        double newX = screenBounds.getMinX() + (screenBounds.getWidth() - stage.getWidth()) / 2;
        double newY = screenBounds.getMinY() + (screenBounds.getHeight() - stage.getHeight()) / 2;
        stage.setX(newX);
        stage.setY(newY);
    }
}
