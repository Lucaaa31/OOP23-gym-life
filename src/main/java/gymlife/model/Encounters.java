package gymlife.model;

import java.util.Map;
import java.util.Optional;
import java.util.Random;

import gymlife.utility.StatsType;
import gymlife.utility.EncountersConstants;

/**
 * The Encounters enum represents different encounters that can happen in the gym.
 * Each encounter has a description and corresponding cases for accepting or denying the encounter.
 */
public enum Encounters {
    /** 
     * Case where the player encounters a pusher.
     */
    PUSHER ("A pusher offers you some steroids. Do you accept?",
    EncountersConstants.PUSHER_ACCEPT, EncountersConstants.PUSHER_DENY),
    /** 
     * Case where the player encounters an ice cream truck.
     */
    ICE_CREAM("An ice cream truck is parked outside the gym. Do you accept?", 
    EncountersConstants.ICE_CREAM_ACCEPT, EncountersConstants.ICE_CREAM_DENY),
    /** 
     * Case where the player finds a bag of money.
     */
    MONEY_BAG("You find a bag of money on the ground. Do you take it?", 
    EncountersConstants.MONEY_BAG_ACCEPT, EncountersConstants.MONEY_BAG_DENY),
    /** 
     * Case where the player encounters a robber.
     */
    ROBBER("A robber tries to steal your wallet. Do you fight back?", 
    EncountersConstants.ROBBER_ACCEPT, EncountersConstants.ROBBER_DENY),
    /** 
     * Case where the player encounters a gym bro.
     */
    GYM_BRO("A gym bro stop you in the street. Do you accept?", 
    EncountersConstants.GYM_BRO_ACCEPT, EncountersConstants.GYM_BRO_DENY),;

    private String description;
    private Map<StatsType, Integer> acceptCase;
    private Map<StatsType, Integer> denyCase;

    /**
     * Constructs an Encounters enum with the given description, accept case, and deny case.
     * 
     * @param description the description of the encounter
     * @param acceptCase the map of stats type and corresponding values for accepting the encounter
     * @param denyCase the map of stats type and corresponding values for denying the encounter
     */
    private Encounters(final String description, final Map<StatsType, Integer> acceptCase, final Map<StatsType, Integer> denyCase) {
        this.description = description;
        this.acceptCase = acceptCase;
        this.denyCase = denyCase;
    }
    /**
     * Returns the description of the encounter.
     * 
     * @return the description of the encounter
     */
    public String getDescription() {
        return description;
    }
    /**
     * Returns the map of stats type and corresponding values for accepting the encounter.
     * 
     * @return the map of stats type and corresponding values for accepting the encounter
     */
    public Map<StatsType, Integer> getAcceptCase() {
        return this.acceptCase;
    }
    /**
     * Returns the map of stats type and corresponding values for denying the encounter.
     * 
     * @return the map of stats type and corresponding values for denying the encounter
     */
    public Map<StatsType, Integer> getDenyCase() {
        return this.denyCase;
    }

    public static Optional<Encounters> getRandomEncounter() {
    if (Math.random() < EncountersConstants.ENCOUNTER_CHANCE) {
        int pick = new Random().nextInt(Encounters.values().length);
        return Optional.of(Encounters.values()[pick]);
    }
    return Optional.empty();
    }
}
    

