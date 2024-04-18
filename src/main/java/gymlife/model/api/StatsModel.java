package gymlife.model.api;

/**
 * This interface represents the stats model for a gym life application.
 * It provides methods to retrieve and update various stats related to the user's body.
 */
public interface StatsModel {
    /**
     * Returns the mass of the user.
     * @return the mass of the user
     */
    int getMass();
    /**
     * Returns the mass of the user's back.
     * @return the mass of the user's back
     */
    int getBackMass();
    /**
     * Returns the mass of the user's legs.
     * @return the mass of the user's legs
     */
    int getLegMass();
    /**
     * Returns the mass of the user's chest.
     * @return the mass of the user's chest
     */
    int getChestMass();
    /**
     * Returns the stamina of the user.
     * @return the stamina of the user
     */
    int getStamina();
    /**
     * Returns the humor level of the user.
     * @return the humor level of the user
     */
    int getHumor();
    /**
     * Increases the mass of the user.
     * @return true if the mass was successfully increased, false otherwise
     */
    boolean incMass();
    /**
     * Increases the stamina of the user.
     * @return true if the stamina was successfully increased, false otherwise
     */
    boolean incStamina();
    /**
     * Increases the humor level of the user.
     * @return true if the humor level was successfully increased, false otherwise
     */
    boolean incHumor();
}
