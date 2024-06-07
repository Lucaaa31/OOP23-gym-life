package gymlife.model;

import gymlife.model.encounter.Encounter;
import gymlife.model.encounter.EncountersConstants;
import gymlife.model.encounter.EncountersFactoryImpl;
import gymlife.model.statistics.StatsType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import java.util.Optional;

class TestEncounters {
    @Test
    void testGetRandomEncounter() {
        for (int i = 0; i < 1000; i++) {
            boolean isValid = false;
            final Optional<Encounter> encounters = EncountersFactoryImpl.getRandomEncounter();
            if (encounters.isEmpty() 
                || "PUSHER".equals(encounters.get().name())
                || "ICE_CREAM".equals(encounters.get().name())
                || "MONEY_BAG".equals(encounters.get().name())
                || "ROBBER".equals(encounters.get().name())
                || "GYM_BRO".equals(encounters.get().name())
            ) {
                isValid = true;
            }
            assertTrue(isValid);
        }
    }

    @Test
    void testGetAcceptCase() {
        for (int i = 0; i < 1000; i++) {
            final Optional<Encounter> encounter = EncountersFactoryImpl.getRandomEncounter();
            boolean isValid = false;
            if (encounter.isPresent()) {
                final Map<StatsType, Integer> acceptCase = encounter.get().acceptCase();
                if (acceptCase.equals(EncountersConstants.gymBroAccept())
                        || acceptCase.equals(EncountersConstants.iceCreamAccept())
                        || acceptCase.equals(EncountersConstants.moneyBagAccept())
                        || acceptCase.equals(EncountersConstants.pusherAccept())
                        || acceptCase.equals(EncountersConstants.robberAccept())) {
                    isValid = true;
                }
                assertTrue(isValid);
            }
        }
    }
    @Test
    void testGetDenyCase() {
        for (int i = 0; i < 1000; i++) {
            final Optional<Encounter> encounter = EncountersFactoryImpl.getRandomEncounter();
            boolean isValid = false;
            if (encounter.isPresent()) {
                final Map<StatsType, Integer> denyCase = encounter.get().denyCase();
                if (denyCase.equals(EncountersConstants.gymBroDeny())
                        || denyCase.equals(EncountersConstants.iceCreamDeny())
                        || denyCase.equals(EncountersConstants.moneyBagDeny())
                        || denyCase.equals(EncountersConstants.pusherDeny())
                        || denyCase.equals(EncountersConstants.robberDeny())) {
                    isValid = true;
                }
                assertTrue(isValid);
            }
        }
    }
}
