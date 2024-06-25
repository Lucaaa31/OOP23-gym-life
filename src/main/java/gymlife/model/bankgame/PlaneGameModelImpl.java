package gymlife.model.bankgame;

import gymlife.model.bankgame.api.PlaneGameModel;

import java.util.List;
import java.util.Random;

/**
 * This class creates a multiplier.
 */
public final class PlaneGameModelImpl implements PlaneGameModel {
    private static final float MAX_BOUND = 5.00f;
    private static final float INCREMENT = 0.001f;
    private boolean flag = true;
    private static final Random R = new Random();
    private float threshold;
    private float multiplier;
    private float multiplierShort;
    private float moneyMultiplied = 1;
    private static final float START_MULTIPLIER = 0.9f;
    private static final float MIN_THRESHOLD_VALUE = 0.90f;
    private final SynchronizerModel mySync, otherSync;
    private final GameStatistics gs = new GameStatistics();

    /**
     * This is the constructor of the PlaneGameModel class.
     *
     * @param mySync the synchronization object used to wait for signals.
     * @param otherSync the synchronization object used to send signals.
     */
    public PlaneGameModelImpl(final SynchronizerModel mySync, final SynchronizerModel otherSync) {
        this.mySync = mySync;
        this.otherSync = otherSync;
        multiplier = START_MULTIPLIER;
        threshold = (float) (Math.round((MIN_THRESHOLD_VALUE + R.nextFloat() * MAX_BOUND) * 1000.0) / 1000.0);
        multiplierShort = 0;
    }

    @Override
    public boolean boundControl() {
        multiplierShort = (float) (Math.round(multiplier * 1000.0) / 1000.0);
        return Float.compare(threshold, multiplierShort) == 0;
    }

    @Override
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

    @Override
    public void randomizeNewThreshold() {
        threshold = (float) (Math.round((MIN_THRESHOLD_VALUE + R.nextFloat() * MAX_BOUND) * 1000.0) / 1000.0);
        multiplier = START_MULTIPLIER;
        flag = true;
    }

    @Override
    public void stopMultiplier() {
        flag = false;
        gs.addThreshold(threshold);
    }

    @Override
    public float getThreshold() {
        return threshold;
    }

    @Override
    public float getMultiplierShort() {
        return multiplierShort;
    }

    @Override
    public float getMoneyMultiplied() {
        return moneyMultiplied;
    }

    /**
     * Returns the list of thresholds stored in the GameStatistics object.
     *
     * @return A list containing the thresholds stored in the GameStatistics object.
     */
    @Override
    public List<Float> getList() {
        return gs.getList();
    }
}

