package gymlife.model;

import java.util.Random;

/**
 * This class create a multiplier.
 */
public final class PlaneGameModel {
    private static final float MAX_BOUND = 9.00f;
    private static final int THREAD_WAIT = 8;
    private static final float INCREMENT = 0.001f;
    private boolean flag = true;
    private static final Random R = new Random();
    private final float treshold;
    private float multiplier;
    private float multiplierShort;
    float moneyMultiplied = 1;

    /**
     * This is the constructor of the PlaneGameModel class.
     */
    public PlaneGameModel() {
        multiplier = 1.0f;
        treshold = (float) (Math.round((1.00 + R.nextFloat() * MAX_BOUND) * 1000.0) / 1000.0);
        multiplierShort = 0;
    }

    /**
     * This method checks if the multiplier is equal to the treshold.
     *
     * @return true if the variable treshold is equal to the multiplier variable.
     */
    public boolean boundControl() {
        multiplierShort = (float) (Math.round(multiplier * 1000.0) / 1000.0);
        return Float.compare(treshold, multiplierShort) == 0;
    }

    public void runMultiplier(float money) {
            while (flag) {
                multiplier += INCREMENT;
                moneyMultiplied = multiplier * money;
                if (boundControl()) {
                    flag = false;
                }
                try {
                    Thread.sleep(THREAD_WAIT);
                } catch (InterruptedException e) {
                }
            }
    }

    public void stopMultiplier() {
        flag = false;
    }

    /**
     * This method returns the treshold value.
     * 
     * @return treshold value.
     */
    public float getTreshold() {
        return treshold;
    }

    /**
     * This method returns the multiplierShort value.
     * 
     * @return multiplierShort value.
     */
    public float getMultiplierShort() {
        return multiplierShort;
    }

    public float getMoneyMultiplied() {
        return moneyMultiplied;
    }
}
