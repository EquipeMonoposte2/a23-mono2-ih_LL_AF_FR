package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.services;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;

import java.util.Objects;

public class RedimensionnementService extends ScheduledService<RedimensionnementService.LocationTaille>
{

    private LocationTaille souhaitee;
    private LocationTaille actuelle;
    private double tailleIncrement = 5;
    private double locationIncrement = 5;

    public RedimensionnementService(LocationTaille actuel, LocationTaille souhaitee)
    {
        this.actuelle = actuel;
        this.souhaitee = souhaitee;
    }

    @Override
    protected Task<LocationTaille> createTask()
    {
        return new Task<>()
        {
            @Override
            protected LocationTaille call() throws Exception
            {
                actuelle.x = intrapole(souhaitee.x, actuelle.x);
                actuelle.y = intrapole(souhaitee.y, actuelle.y);
                actuelle.largeur = intrapole(souhaitee.largeur, actuelle.largeur);
                actuelle.longueur = intrapole(souhaitee.longueur, actuelle.longueur);

                // condition d'arrêt
                if (actuelle.equals(souhaitee)) {
                    this.cancel();
                }
                return actuelle.clone();
            }

            private double intrapole(double cible, double valeur) {
                if (Math.abs(cible - valeur) < locationIncrement) {
                    valeur = cible;
                } else {
                    if (valeur > cible) {
                        valeur -= locationIncrement;
                    } else {
                        valeur += locationIncrement;
                    }
                }
                return valeur;
            }
        };

    }

    public static class  LocationTaille implements Cloneable{
        private double x;
        private double y;
        private double largeur;
        private double longueur;

        public LocationTaille(double x, double y, double largeur, double longueur) {
            this.x = x;
            this.y = y;
            this.largeur = largeur;
            this.longueur = longueur;
        }

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }

        public double getLargeur() {
            return largeur;
        }

        public void setLargeur(double largeur) {
            this.largeur = largeur;
        }

        public double getLongueur() {
            return longueur;
        }

        public void setLongueur(double longueur) {
            this.longueur = longueur;
        }

        @Override
        public LocationTaille clone() throws CloneNotSupportedException {
            return (LocationTaille) super.clone();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof LocationTaille that)) return false;
            return Double.compare(that.x, x) == 0 && Double.compare(that.y, y) == 0 && Double.compare(that.largeur, largeur) == 0 && Double.compare(that.longueur, longueur) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, largeur, longueur);
        }
    }
}
