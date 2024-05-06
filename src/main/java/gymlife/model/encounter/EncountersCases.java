package gymlife.model.encounter;

import java.util.HashMap;
import java.util.Map;

import gymlife.model.statistics.StatsType;

/**
 * EncountersCases class contains constants and methods related to different encounter cases in the application.
 */
public final class EncountersCases {
    private static Map<StatsType, Integer> returnMap;
    private static final double ENCOUNTER_CHANCE = 0.5;
    /**
     * Returns a map representing the acceptance criteria for the pusher encounter.
     * 
     * @return a map with the acceptance criteria for the pusher encounter
     */
    public static Map<StatsType, Integer> pusherAccept() {
        returnMap = new HashMap<>();
        returnMap.put(StatsType.MASS, 10);
        return Map.copyOf(returnMap);
    }
    /**
     * Returns a map representing the denial criteria for the pusher encounter.
     * 
     * @return a map with the denial criteria for the pusher encounter
     */
    public static Map<StatsType, Integer> pusherDeny() {
        returnMap = new HashMap<>();
        returnMap.put(StatsType.MASS, 10);
        return Map.copyOf(returnMap);
    } 
    /**
     * Returns a map representing the acceptance criteria for the ice cream encounter.
     * 
     * @return a map with the acceptance criteria for the ice cream encounter
     */
    public static Map<StatsType, Integer> iceCreamAccept() {
        returnMap = new HashMap<>();
        returnMap.put(StatsType.MASS, 10);
        return Map.copyOf(returnMap);
    }
    /**
     * Returns a map representing the denial criteria for the ice cream encounter.
     * 
     * @return a map with the denial criteria for the ice cream encounter
     */
    public static Map<StatsType, Integer> iceCreamDeny() {
        returnMap = new HashMap<>();
        returnMap.put(StatsType.MASS, 10);
        return Map.copyOf(returnMap);
    }
    /**
     * Returns a map representing the acceptance criteria for the money bag encounter.
     * 
     * @return a map with the acceptance criteria for the money bag encounter
     */
    public static Map<StatsType, Integer> moneyBagAccept() {
        returnMap = new HashMap<>();
        returnMap.put(StatsType.MASS, 10);
        return Map.copyOf(returnMap);
    }
    /**
     * Returns a map representing the denial criteria for the money bag encounter.
     * 
     * @return a map with the denial criteria for the money bag encounter
     */
    public static Map<StatsType, Integer> moneyBagDeny() {
        returnMap = new HashMap<>();
        returnMap.put(StatsType.MASS, 10);
        return Map.copyOf(returnMap);
    }
    /**
     * Returns a map representing the acceptance criteria for the robber encounter.
     * 
     * @return a map with the acceptance criteria for the robber encounter
     */
    public static Map<StatsType, Integer> robberAccept() {
        returnMap = new HashMap<>();
        returnMap.put(StatsType.MASS, 10);
        return Map.copyOf(returnMap);
    }
    /**
     * Returns a map representing the denial criteria for the robber encounter.
     * 
     * @return a map with the denial criteria for the robber encounter
     */
    public static Map<StatsType, Integer> robberDeny() {
        returnMap = new HashMap<>();
        returnMap.put(StatsType.MASS, 10);
        return Map.copyOf(returnMap);
    } 
    /**
     * Returns a map representing the acceptance criteria for the gym bro encounter.
     * 
     * @return a map with the acceptance criteria for the gym bro encounter
     */
    public static Map<StatsType, Integer> gymBroAccept() {
        returnMap = new HashMap<>();
        returnMap.put(StatsType.MASS, 10);
        return Map.copyOf(returnMap);
    }
    /**
     * Returns a map representing the denial criteria for the gym bro encounter.
     * 
     * @return a map with the denial criteria for the gym bro encounter
     */
    public static Map<StatsType, Integer> gymBroDeny() {
        returnMap = new HashMap<>();
        returnMap.put(StatsType.MASS, 10);
        return Map.copyOf(returnMap);
    }
    /**
     * The chance of encountering an event.
     * @return a double with the denial criteria for the casual encounter.
     */
    public static double encounterChance() {
        return ENCOUNTER_CHANCE;
    }
    private EncountersCases() {
    }
}
