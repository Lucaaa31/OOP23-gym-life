package gymlife.model;

import gymlife.model.api.StatsModel;
import gymlife.utility.Constants;

public class StatsModelImpl implements StatsModel {
    private static  Integer HUMOR = Constants.STARTING_STATS_LEVEL;
    private static  Integer STAMINA = Constants.STARTING_STATS_LEVEL;
    private static  Integer MASS = Constants.STARTING_STATS_LEVEL;

    public static int getMass(){
        return MASS;
    }

    public static int getStamina(){
        return STAMINA;
    }

    public static int getHumor(){
        return HUMOR;
    }
    
    public static boolean incMass(){
        return (MASS ++ > Constants.MAX_STATS_LEVEL ? false : true);
    }

    public static boolean incStamina(){
        return (STAMINA ++ > Constants.MAX_STATS_LEVEL ? false : true);
    }

    public static boolean incHumor(){
        return (HUMOR ++ > Constants.MAX_STATS_LEVEL ? false : true);
    }

}

