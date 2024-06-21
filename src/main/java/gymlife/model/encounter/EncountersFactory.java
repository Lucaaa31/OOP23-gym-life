package gymlife.model.encounter;

import java.util.Optional;
import java.util.Random;

/**
 * Factory class that generates a random Optional of Encounter based on the different probability of every
 * encounter type.
 * The probability of each encounter is defined in the EncountersConstants class.
 */
public final class EncountersFactory {
    private static final Random RANDOM = new Random();

    private EncountersFactory() {
    }
    /**
     * Returns a random optional encounter based on the probability of each encounter type.
     *
     * @return a random encounter
     */
    public static Optional<Encounter> getRandomEncounter() {
        if (RANDOM.nextDouble() < EncountersConstants.encounterChance()) {
            final double randomValue = RANDOM.nextDouble();
            return createEncounter(randomValue);
        }
        return Optional.empty();
    }

    private static Optional<Encounter> createEncounter(final double randomValue) {
        if (randomValue < EncountersConstants.moneyBagChance()) {
            return Optional.of(createMoneyBagEncounter());
        } else if (randomValue < EncountersConstants.robberChance()) {
            return Optional.of(createRobberEncounter());
        } else if (randomValue < EncountersConstants.pusherChance()) {
            return Optional.of(createPusherEncounter());
        } else if (randomValue < EncountersConstants.gymBroChance()) {
            return Optional.of(createGymBroEncounter());
        }
        return Optional.of(createIceCreamEncounter());
    }

    private static Encounter createMoneyBagEncounter() {
        return new Encounter("MONEY_BAG", EncountersConstants.moneyBagDescription(),
                EncountersConstants.moneyBagAccept(), EncountersConstants.moneyBagDeny());
    }

    private static Encounter createRobberEncounter() {
        return new Encounter("ROBBER", EncountersConstants.robberDescription(),
                EncountersConstants.robberAccept(), EncountersConstants.robberDeny());
    }

    private static Encounter createPusherEncounter() {
        return new Encounter("PUSHER", EncountersConstants.pusherDescription(),
                EncountersConstants.pusherAccept(), EncountersConstants.pusherDeny());
    }

    private static Encounter createGymBroEncounter() {
        return new Encounter("GYM_BRO", EncountersConstants.gymBroDescription(),
                EncountersConstants.gymBroAccept(), EncountersConstants.gymBroDeny());
    }

    private static Encounter createIceCreamEncounter() {
        return new Encounter("ICE_CREAM", EncountersConstants.iceCreamDescription(),
                EncountersConstants.iceCreamAccept(), EncountersConstants.iceCreamDeny());
    }
}
