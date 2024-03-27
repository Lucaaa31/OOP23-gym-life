package gymlife.model;

import java.util.Random;

/**
 * This class create a multiplier.
 */
public final class PlaneGameModel implements Runnable {
    private static final float MAX_BOUND = 12.00f;
    private static final int THREAD_WAIT = 5;
    private static final float INCREMENT = 0.001f;
    private boolean flag = true;
    private static final Random R = new Random();
    private final float treshold;
    private float multiplier;

    /**
     * This is the constructor of the PlaneGameModel class.
     */
    public PlaneGameModel() {
        multiplier = 1.0f;
        treshold = (float) (Math.round((1.00 + R.nextFloat() * MAX_BOUND) * 1000.0) / 1000.0);
    }

    /**
     * This method checks if the multiplier is equal to the treshold.
     *
     * @return true if the variable treshold is equal to the multiplier variable.
     */
    public boolean boundControl() {
        final float n = (float) (Math.round(multiplier * 1000.0) / 1000.0);
        return Float.compare(treshold, n) == 0;
    }

    @Override
    public void run() {
        while (flag) {
            multiplier += INCREMENT;
            if (boundControl()) {
                flag = false;
            }
            try {
                Thread.sleep(THREAD_WAIT);
            } catch (InterruptedException e) {
            }
        }
    }

}
