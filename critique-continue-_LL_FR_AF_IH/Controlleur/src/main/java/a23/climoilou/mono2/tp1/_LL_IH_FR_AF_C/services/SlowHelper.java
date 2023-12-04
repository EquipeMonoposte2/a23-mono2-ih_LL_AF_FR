package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.services;

import java.util.Random;

public interface SlowHelper {

    public static void slow(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {
        }
    }

    public static void slowRandom(int minMillis, int maxMillis) {
        SlowHelper.slow(new Random().nextInt(maxMillis - minMillis) + minMillis);
    }
}