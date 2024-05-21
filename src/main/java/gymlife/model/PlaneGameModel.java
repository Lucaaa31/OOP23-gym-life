package gymlife.model;

import java.util.Random;

import gymlife.model.statistics.api.SynchronizerModel;

/**
 * This class create a multiplier.
 */
public final class PlaneGameModel {
    private static final float MAX_BOUND = 9.00f;
    private static final float INCREMENT = 0.001f;
    private boolean flag = true;
    private static final Random R = new Random();
    private float treshold;
    private float multiplier;
    private float multiplierShort;
    private float moneyMultiplied = 1;
    private final SynchronizerModel mySync, otherSync;

    /**
     * This is the constructor of the PlaneGameModel class.
     * 
     * @param mySync the synchronization object used to wait for signals.
     * @param otherSync the synchronization object used to send signals.
     */
    public PlaneGameModel(final SynchronizerModel mySync, final SynchronizerModel otherSync) {
        this.mySync = mySync;
        this.otherSync = otherSync;
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

    /**
     * Starts the multiplier thread with the specified money value.
     * The multiplier continuously increments the multiplier value by a constant
     * increment and calculates the money multiplied value,
     * until the flag is set to false, which means the multiplier has reach his
     * threshold.
     * 
     * @param money The money value to be multiplied with the multiplier.
     */
    public void runMultiplier(final float money) {
        try {
            while (flag) {
                mySync.waitForSignal();
                multiplier += INCREMENT;
                moneyMultiplied = multiplier * money;
                if (boundControl()) {
                    flag = false;
                }
                otherSync.signal();
            }
        } catch (InterruptedException e) {
        }
    }

    /**
     * Generates a new random threshold value within a specified range and resets
     * the multiplier.
     * 
     * @return The newly generated threshold value.
     */
    public float randomizeNewThreshold() {
        treshold = (float) (Math.round((1.00 + R.nextFloat() * MAX_BOUND) * 1000.0) / 1000.0);
        multiplier = 1;
        flag = true;
        return treshold;
    }

    /**
     * This method stops the runMultiplier method by setting the flag to false.
     */
    public void stopMultiplier() {
        flag = false;
    }

    /**
     * This method returns the threshold value.
     * 
     * @return threshold value.
     */
    public float getThreshold() {
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

    /**
     * This method returns the value of moneyMultiplied.
     * 
     * @return moneyMultiplied value.
     */
    public float getMoneyMultiplied() {
        return moneyMultiplied;
    }
}
