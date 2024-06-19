package gymlife.model;

import gymlife.model.encounter.Encounter;
import gymlife.model.encounter.EncountersConstants;
import gymlife.model.encounter.EncountersFactory;
import gymlife.model.statistics.StatsType;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TestEncounters {

    private static final int TEST_ITERATIONS = 1000;

    @Test
    void testGetRandomEncounter() {
        final boolean allValid = IntStream.range(0, TEST_ITERATIONS).allMatch(i -> {
            final Optional<Encounter> encounter = EncountersFactory.getRandomEncounter();
            return encounter.isEmpty()
                    || "PUSHER".equals(encounter.map(Encounter::name).orElse(""))
                    || "ICE_CREAM".equals(encounter.map(Encounter::name).orElse(""))
                    || "MONEY_BAG".equals(encounter.map(Encounter::name).orElse(""))
                    || "ROBBER".equals(encounter.map(Encounter::name).orElse(""))
                    || "GYM_BRO".equals(encounter.map(Encounter::name).orElse(""));
        });
        assertTrue(allValid, "One or more encounters are not valid");
    }

    @Test
    void testGetAcceptCase() {
        final boolean allValid = IntStream.range(0, TEST_ITERATIONS).allMatch(i -> {
            final Optional<Encounter> encounter = EncountersFactory.getRandomEncounter();
            return encounter.map(enc -> {
                final Map<StatsType, Integer> acceptCase = enc.acceptCase();
                return acceptCase.equals(EncountersConstants.gymBroAccept())
                        || acceptCase.equals(EncountersConstants.iceCreamAccept())
                        || acceptCase.equals(EncountersConstants.moneyBagAccept())
                        || acceptCase.equals(EncountersConstants.pusherAccept())
                        || acceptCase.equals(EncountersConstants.robberAccept());
            }).orElse(true); // If encounter is empty, consider it valid
        });
        assertTrue(allValid, "One or more accept cases are not valid");
    }

    @Test
    void testGetDenyCase() {
        final boolean allValid = IntStream.range(0, TEST_ITERATIONS).allMatch(i -> {
            final Optional<Encounter> encounter = EncountersFactory.getRandomEncounter();
            return encounter.map(enc -> {
                final Map<StatsType, Integer> denyCase = enc.getDenyCase();
                return denyCase.equals(EncountersConstants.gymBroDeny())
                        || denyCase.equals(EncountersConstants.iceCreamDeny())
                        || denyCase.equals(EncountersConstants.moneyBagDeny())
                        || denyCase.equals(EncountersConstants.pusherDeny())
                        || denyCase.equals(EncountersConstants.robberDeny());
            }).orElse(true); // If encounter is empty, consider it valid
        });
        assertTrue(allValid, "One or more deny cases are not valid");
    }
}
