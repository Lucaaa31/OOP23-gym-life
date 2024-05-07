package gymlife.model.encounter;

import java.util.Optional;


/**
 * Implementation of the EncountersManager interface.
 */
public final class EncountersFactoryImpl {
    /**
     * Retrieves a random encounter .
     * 
     * @return The random encounter.
     */
    public static Optional<Encounter> getRandomEncounter() {
        if (Math.random() < EncountersConstants.encounterChance()) {
            final double e = Math.random();
            if (e < EncountersConstants.iceCreamChance()) {
                return Optional.of(new Encounter("ICE_CREAM", EncountersConstants.iceCreamDescription(),
                        EncountersConstants.iceCreamAccept(), EncountersConstants.iceCreamDeny()));
            } else if (e < EncountersConstants.robberChance()) {
                return Optional.of(new Encounter("ROBBER", EncountersConstants.robberDescription(),
                        EncountersConstants.robberAccept(), EncountersConstants.robberDeny()));
            } else if (e < EncountersConstants.gymBroChance()) {
                return Optional.of(new Encounter("GYM_BRO", EncountersConstants.gymBroDescription(),
                        EncountersConstants.gymBroAccept(), EncountersConstants.gymBroDeny()));
            } else if (e < EncountersConstants.moneyBagChance()) {
                return Optional.of(new Encounter("MONEY_BAG", EncountersConstants.moneyBagDescription(),
                        EncountersConstants.moneyBagAccept(), EncountersConstants.moneyBagDeny()));
            } else if (e < EncountersConstants.pusherChance()) {
                return Optional.of(new Encounter("PUSHER", EncountersConstants.pusherDescription(),
                        EncountersConstants.pusherAccept(), EncountersConstants.pusherDeny()));
            }
        }
        return Optional.empty();
    }
    private EncountersFactoryImpl() {
    }
}
