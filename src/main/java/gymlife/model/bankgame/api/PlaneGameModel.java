package gymlife.model.bankgame.api;

import java.util.List;

/**
 * The PlaneGameModel interface defines methods for managing a multiplier game.
 * It allows starting, stopping, and checking the state of a multiplier, as well
 * as managing thresholds.
 */
public interface PlaneGameModel {

    /**
     * This method checks if the multiplier is equal to the threshold.
     *
     * @return true if the variable threshold is equal to the multiplier variable.
     */
    boolean boundControl();

    /**
     * Starts the multiplier thread with the specified money value.
     * The multiplier continuously increments the multiplier value by a constant
     * increment and calculates the money multiplied value,
     * until the flag is set to false, which means the multiplier has reach his
     * threshold.
     *
     * @param money The money value to be multiplied with the multiplier.
     */
    void runMultiplier(float money);

    /**
     * Generates a new random threshold value within a specified range and resets
     * the multiplier.
     *
     */
    void randomizeNewThreshold();

    /**
     * This method stops the runMultiplier method by setting the flag to false.
     */
    void stopMultiplier();

    /**
     * This method returns the threshold value.
     *
     * @return threshold value.
     */
    float getThreshold();

    /**
     * This method returns the multiplierShort value.
     *
     * @return multiplierShort value.
     */
    float getMultiplierShort();

    /**
     * This method returns the value of moneyMultiplied.
     *
     * @return moneyMultiplied value.
     */
    float getMoneyMultiplied();

    /**
     * Returns the list of thresholds stored in the GameStatistics object.
     *
     * @return A list containing the thresholds stored in the GameStatistics object.
     */
    List<Float> getList();
}
