package gymlife.utility;

import java.util.HashMap;
import java.util.Map;

/**
 * Constants class contains various constants used in the application.
 */
public class EncountersConstants {
    /**
     * The movement constant used for character movement.
     */
    public static final Map<StatsType, Integer> PUSHER_ACCEPT = new HashMap<StatsType, Integer>() {{
        put(StatsType.MASS, 10);
    }};
    /**
     * The movement constant used for character movement.
     */
    public static final Map<StatsType, Integer> PUSHER_DENY = new HashMap<StatsType, Integer>() {{
        put(StatsType.MASS, 10);
    }};
    /**
     * The movement constant used for character movement.
     */
    public static final Map<StatsType, Integer> ICE_CREAM_ACCEPT = new HashMap<StatsType, Integer>() {{
        put(StatsType.MASS, 10);
    }};
    /**
     * The movement constant used for character movement.
     */
    public static final Map<StatsType, Integer> ICE_CREAM_DENY = new HashMap<StatsType, Integer>() {{
        put(StatsType.MASS, 10);
    }};
    /**
     * The movement constant used for character movement.
     */
    public static final Map<StatsType, Integer> MONEY_BAG_ACCEPT = new HashMap<StatsType, Integer>() {{
        put(StatsType.MASS, 10);
    }};
    /**
     * The movement constant used for character movement.
     */
    public static final Map<StatsType, Integer> MONEY_BAG_DENY = new HashMap<StatsType, Integer>() {{
        put(StatsType.MASS, 10);
    }};
    /**
     * The movement constant used for character movement.
     */
    public static final Map<StatsType, Integer> ROBBER_ACCEPT = new HashMap<StatsType, Integer>() {{
        put(StatsType.MASS, 10);
    }};
    /**
     * The movement constant used for character movement.
     */
    public static final Map<StatsType, Integer> ROBBER_DENY = new HashMap<StatsType, Integer>() {{
        put(StatsType.MASS, 10);
    }};
    /**
     * The movement constant used for character movement.
     */
    public static final Map<StatsType, Integer> GYM_BRO_ACCEPT = new HashMap<StatsType, Integer>() {{
        put(StatsType.MASS, 10);
    }};
    /**
     * The movement constant used for character movement.
     */
    public static final Map<StatsType, Integer> GYM_BRO_DENY = new HashMap<StatsType, Integer>() {{
        put(StatsType.MASS, 10);
    }};
    public static final double ENCOUNTER_CHANCE = 0.5;
    private EncountersConstants() {
    }
}
