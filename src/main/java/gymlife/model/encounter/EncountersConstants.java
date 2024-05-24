package gymlife.model.encounter;

import java.util.HashMap;
import java.util.Map;

import gymlife.model.statistics.StatsType;

/**
 * EncountersConstants class contains constants and methods related to different encounter cases in the application.
 */
public final class EncountersConstants {
    private static Map<StatsType, Integer> returnMap;
    private static final double ENCOUNTER_CHANCE = 1;
    private static final double ICE_CREAM_CHANCE = 1;
    private static final double GYM_BRO_CHANCE = 0.8;
    private static final double PUSHER_CHANCE = 0.6;
    private static final double ROBBER_CHANCE = 0.4;
    private static final double MONEY_BAG_CHANCE = 0.2;
    private static final int VERY_HIGH = 30;
    private static final int HIGH = 20;
    private static final int MEDIUM = 10;
    private static final int LOW = 5;
    private static final String PUSHER_DESCRIPTION = "A pusher offers you some steroids. Do you accept?";
    private static final String ICE_CREAM_DESCRIPTION = "An ice cream truck is parked outside the gym. Do you buy one?";
    private static final String MONEY_BAG_DESCRIPTION = "You find a bag of money on the ground. Do you take it?";
    private static final String ROBBER_DESCRIPTION = "A robber tries to steal your wallet. Do you fight back?";
    private static final String GYM_BRO_DESCRIPTION = "You see your gym bro walking by. You owe him some money."
            + " Do you stop to say hi?";

    /**
     *
     * Returns a map representing the acceptance criteria for the pusher encounter.
     * 
     * @return a map with the acceptance criteria for the pusher encounter
     */
    public static Map<StatsType, Integer> pusherAccept() {
        returnMap = new HashMap<>();
        returnMap.put(StatsType.MASS, MEDIUM);
        returnMap.put(StatsType.HAPPINESS, -MEDIUM);
        returnMap.put(StatsType.MONEY, -HIGH);
        return Map.copyOf(returnMap);
    }
    /**
     * Returns a map representing the denial criteria for the pusher encounter.
     * 
     * @return a map with the denial criteria for the pusher encounter
     */
    public static Map<StatsType, Integer> pusherDeny() {
        returnMap = new HashMap<>();
        returnMap.put(StatsType.HAPPINESS, LOW);
        return Map.copyOf(returnMap);
    } 
    /**
     * Returns a map representing the acceptance criteria for the ice cream encounter.
     * 
     * @return a map with the acceptance criteria for the ice cream encounter
     */
    public static Map<StatsType, Integer> iceCreamAccept() {
        returnMap = new HashMap<>();
        returnMap.put(StatsType.MASS, -LOW);
        returnMap.put(StatsType.HAPPINESS, HIGH);
        return Map.copyOf(returnMap);
    }
    /**
     * Returns a map representing the denial criteria for the ice cream encounter.
     * 
     * @return a map with the denial criteria for the ice cream encounter
     */
    public static Map<StatsType, Integer> iceCreamDeny() {
        returnMap = new HashMap<>();
        returnMap.put(StatsType.HAPPINESS, -LOW);
        returnMap.put(StatsType.STAMINA, MEDIUM);
        return Map.copyOf(returnMap);
    }
    /**
     * Returns a map representing the acceptance criteria for the money bag encounter.
     * 
     * @return a map with the acceptance criteria for the money bag encounter
     */
    public static Map<StatsType, Integer> moneyBagAccept() {
        returnMap = new HashMap<>();
        returnMap.put(StatsType.MONEY, VERY_HIGH);
        returnMap.put(StatsType.HAPPINESS, LOW);
        returnMap.put(StatsType.STAMINA, -LOW);
        return Map.copyOf(returnMap);
    }
    /**
     * Returns a map representing the denial criteria for the money bag encounter.
     * 
     * @return a map with the denial criteria for the money bag encounter
     */
    public static Map<StatsType, Integer> moneyBagDeny() {
        returnMap = new HashMap<>();
        returnMap.put(StatsType.HAPPINESS, -MEDIUM);
        return Map.copyOf(returnMap);
    }
    /**
     * Returns a map representing the acceptance criteria for the robber encounter.
     * 
     * @return a map with the acceptance criteria for the robber encounter
     */
    public static Map<StatsType, Integer> robberAccept() {
        returnMap = new HashMap<>();
        returnMap.put(StatsType.MASS, LOW);
        returnMap.put(StatsType.STAMINA, -HIGH);
        return Map.copyOf(returnMap);
    }
    /**
     * Returns a map representing the denial criteria for the robber encounter.
     * 
     * @return a map with the denial criteria for the robber encounter
     */
    public static Map<StatsType, Integer> robberDeny() {
        returnMap = new HashMap<>();
        returnMap.put(StatsType.MONEY, -HIGH);
        returnMap.put(StatsType.HAPPINESS, -MEDIUM);
        return Map.copyOf(returnMap);
    } 
    /**
     * Returns a map representing the acceptance criteria for the gym bro encounter.
     * 
     * @return a map with the acceptance criteria for the gym bro encounter
     */
    public static Map<StatsType, Integer> gymBroAccept() {
        returnMap = new HashMap<>();
        returnMap.put(StatsType.HAPPINESS, LOW);
        returnMap.put(StatsType.STAMINA, MEDIUM);
        returnMap.put(StatsType.MONEY, -LOW);
        return Map.copyOf(returnMap);
    }
    /**
     * Returns a map representing the denial criteria for the gym bro encounter.
     * 
     * @return a map with the denial criteria for the gym bro encounter
     */
    public static Map<StatsType, Integer> gymBroDeny() {
        returnMap = new HashMap<>();
        returnMap.put(StatsType.HAPPINESS, -MEDIUM);
        return Map.copyOf(returnMap);
    }
    /**
     * The chance of encountering an event.
     * 
     * @return a double representing the chance of encountering an event
     */
    public static double encounterChance() {
        return ENCOUNTER_CHANCE;
    }
    /**
     * The chance of encountering a robber.
     * 
     * @return a double representing the chance of encountering a robber
     */
    public static double robberChance() {
        return ROBBER_CHANCE;
    }
    /**
     * The chance of encountering an ice cream truck.
     * 
     * @return a double representing the chance of encountering an ice cream truck
     */
    public static double iceCreamChance() {
        return ICE_CREAM_CHANCE;
    }
    /**
     * The chance of encountering a gym bro.
     * 
     * @return a double representing the chance of encountering a gym bro
     */
    public static double gymBroChance() {
        return GYM_BRO_CHANCE;
    }
    /**
     * The chance of encountering a money bag.
     * 
     * @return a double representing the chance of encountering a money bag
     */
    public static double moneyBagChance() {
        return MONEY_BAG_CHANCE;
    }
    /**
     * The chance of encountering a pusher.
     * 
     * @return a double representing the chance of encountering a pusher
     */
    public static double pusherChance() {
        return PUSHER_CHANCE;
    }
    /**
     * The description of a pusher encounter.
     * 
     * @return a string representing the description of a pusher encounter
     */
    public static String pusherDescription() {
        return PUSHER_DESCRIPTION;
    }
    /**
     * The description of an ice cream encounter.
     * 
     * @return a string representing the description of an ice cream encounter
     */
    public static String iceCreamDescription() {
        return ICE_CREAM_DESCRIPTION;
    }
    /**
     * The description of a money bag encounter.
     * 
     * @return a string representing the description of a money bag encounter
     */
    public static String moneyBagDescription() {
        return MONEY_BAG_DESCRIPTION;
    }
    /**
     * The description of a robber encounter.
     * 
     * @return a string representing the description of a robber encounter
     */
    public static String robberDescription() {
        return ROBBER_DESCRIPTION;
    }
    /**
     * The description of a gym bro encounter.
     * 
     * @return a string representing the description of a gym bro encounter
     */
    public static String gymBroDescription() {
        return GYM_BRO_DESCRIPTION;
    }

    private EncountersConstants() {
    }
}
