package gymlife.model;

import gymlife.model.api.StatsModel;
import gymlife.utility.Constants;
/**
* Stats Class that .
* @return KeyListener
*/
public final class StatsModelImpl implements StatsModel {
    private static  Integer humor = Constants.STARTING_STATS_LEVEL;
    private static  Integer stamina = Constants.STARTING_STATS_LEVEL;
    private static  Integer mass = Constants.STARTING_STATS_LEVEL;
    /**
     *  Return mass value.
     * @return mass
     */
    public static int getMass() {
        return mass;
    }
    /**
     *  Return stamina value.
     * @return stamina
     */
    public static int getStamina() {
        return stamina;
    }
    /**
     *  Return humor value.
     * @return humor
     */
    public static int getHumor() {
        return humor;
    }
    /**
     *  Return True if the Mass increment hasn't reaced his max.
     * @return boolean
     */
    public static boolean incMass() {
        return mass++ > Constants.MAX_STATS_LEVEL;
    }
    /**
     *  Return True if the Stamina increment hasn't reaced his max. 
     * @return boolean
     */
    public static boolean incStamina() {
        return stamina++ > Constants.MAX_STATS_LEVEL;
    }
    /**
     *  Return True if the Humor increment hasn't reaced his max.
     * @return boolean
     */
    public static boolean inchumor() {
        return humor++ > Constants.MAX_STATS_LEVEL;
    }
    private StatsModelImpl() {
    }
}
