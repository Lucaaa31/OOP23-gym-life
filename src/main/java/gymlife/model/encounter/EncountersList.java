package gymlife.model.encounter;

import java.util.Map;
import java.util.Optional;

import gymlife.model.statistics.StatsType;

/**
 * The Encounters enum represents different encounters that can happen in the gym.
 * Each encounter has a description and corresponding cases for accepting or denying the encounter.
 */
public enum EncountersList {
    /** 
     * Case where the player encounters a pusher.
     */
    PUSHER("A pusher offers you some steroids. Do you accept?",
    EncountersCases.pusherAccept(), EncountersCases.pusherDeny()),
    /** 
     * Case where the player encounters an ice cream truck.
     */
    ICE_CREAM("An ice cream truck is parked outside the gym. Do you accept?", 
    EncountersCases.iceCreamAccept(), EncountersCases.iceCreamDeny()),
    /** 
     * Case where the player finds a bag of money.
     */
    MONEY_BAG("You find a bag of money on the ground. Do you take it?", 
    EncountersCases.moneyBagAccept(), EncountersCases.moneyBagDeny()),
    /** 
     * Case where the player encounters a robber.
     */
    ROBBER("A robber tries to steal your wallet. Do you fight back?", 
    EncountersCases.robberAccept(), EncountersCases.robberDeny()),
    /** 
     * Case where the player encounters a gym bro.
     */
    GYM_BRO("A gym bro stop you in the street. Do you accept?", 
    EncountersCases.gymBroAccept(), EncountersCases.gymBroDeny()),;

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
    EncountersList(final String description, final Map<StatsType, Integer> acceptCase,
                   final Map<StatsType, Integer> denyCase) {
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
        return Map.copyOf(this.acceptCase);
    }
    /**
     * Returns the map of stats type and corresponding values for denying the encounter.
     * 
     * @return the map of stats type and corresponding values for denying the encounter
     */
    public Map<StatsType, Integer> getDenyCase() {
        return Map.copyOf(this.denyCase);
    }
    /**
     * Returns a random encounter from the Encounters enum.
     * 
     * @return an Optional containing a random encounter, or an empty Optional if no encounter is chosen
     */
    public static Optional<EncountersList> getRandomEncounter() {
        if (Math.random() < EncountersCases.encounterChance()) {
            return Optional.empty();
        }
        return Optional.of(EncountersList.values()[(int) (Math.random() * (double) (EncountersList.values().length))]);
    }
}
