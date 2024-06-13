package gymlife.model.encounter;

import java.util.Optional;


/**
 * Factory class that generate a random code Optional of Encounter based on the different probability of every encounter
 * type. The probability of each encounter is defined in the EncountersConstants class.
 *.
 */
public final class EncountersFactoryImpl {
    /**
     * Create a random Optional Encounter or Optional empty, depending on if the encounter appened or not.
     * Every Encounter has a different probability of happening, defined in the EncountersConstants class.
     * @return The random encounter.
     */
    public static Optional<Encounter> getRandomEncounter() {
        if (Math.random() < EncountersConstants.encounterChance()) {
            final double e = Math.random();
            if (e < EncountersConstants.moneyBagChance()) {
                return Optional.of(new Encounter("MONEY_BAG", EncountersConstants.moneyBagDescription(),
                        EncountersConstants.moneyBagAccept(), EncountersConstants.moneyBagDeny()));
            } else if (e < EncountersConstants.robberChance()) {
                return Optional.of(new Encounter("ROBBER", EncountersConstants.robberDescription(),
                        EncountersConstants.robberAccept(), EncountersConstants.robberDeny()));
            } else if (e < EncountersConstants.pusherChance()) {
                return Optional.of(new Encounter("PUSHER", EncountersConstants.pusherDescription(),
                        EncountersConstants.pusherAccept(), EncountersConstants.pusherDeny()));
            } else if (e < EncountersConstants.gymBroChance()) {
                return Optional.of(new Encounter("GYM_BRO", EncountersConstants.gymBroDescription(),
                        EncountersConstants.gymBroAccept(), EncountersConstants.gymBroDeny()));
            } else if (e < EncountersConstants.iceCreamChance()) {
                return Optional.of(new Encounter("ICE_CREAM", EncountersConstants.iceCreamDescription(),
                        EncountersConstants.iceCreamAccept(), EncountersConstants.iceCreamDeny()));
            }
        }
        return Optional.empty();
    }
    private EncountersFactoryImpl() {
    }
}
