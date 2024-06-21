package gymlife.model;

import java.util.Random;

/**
 * This class create a multiplier.
 */
public final class PlaneGameModel {
    private static final float MAX_BOUND = 5.00f;
    private static final float INCREMENT = 0.001f;
    private boolean flag = true;
    private static final Random R = new Random();
    private float threshold;
    private float multiplier;
    private float multiplierShort;
    private float moneyMultiplied = 1;
    private static final float START_MULTIPLIER = 0.7f;
    private static final float MIN_THRESHOLD_VALUE = 0.70f;
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
        multiplier = START_MULTIPLIER;
        threshold = (float) (Math.round((MIN_THRESHOLD_VALUE + R.nextFloat() * MAX_BOUND) * 1000.0) / 1000.0);
        multiplierShort = 0;
    }

    /**
     * This method checks if the multiplier is equal to the threshold.
     *
     * @return true if the variable threshold is equal to the multiplier variable.
     */
    public boolean boundControl() {
        multiplierShort = (float) (Math.round(multiplier * 1000.0) / 1000.0);
        return Float.compare(threshold, multiplierShort) == 0;
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
        } catch (InterruptedException ignored) {
        }
    }

    /**
     * Generates a new random threshold value within a specified range and resets
     * the multiplier.
     *
     */
    public void randomizeNewThreshold() {
        threshold = (float) (Math.round((MIN_THRESHOLD_VALUE + R.nextFloat() * MAX_BOUND) * 1000.0) / 1000.0);
        multiplier = START_MULTIPLIER;
        flag = true;
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
        return threshold;
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
