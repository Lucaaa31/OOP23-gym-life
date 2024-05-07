package gymlife.model;

import gymlife.model.encounter.Encounter;
import gymlife.model.encounter.EncountersConstants;
import gymlife.model.encounter.EncountersFactoryImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

class TestEncounters {
    @Test
    void testGetRandomEncounter() {
        for (int i = 0; i < 1000; i++) {
            boolean isValid = false;
            final Optional<Encounter> encounters = EncountersFactoryImpl.getRandomEncounter();
            if (encounters.isEmpty() 
                || "PUSHER".equals(encounters.get().getName())
                || "ICE_CREAM".equals(encounters.get().getName())
                || "MONEY_BAG".equals(encounters.get().getName())
                || "ROBBER".equals(encounters.get().getName())
                || "GYM_BRO".equals(encounters.get().getName())
            ) {
                isValid = true;
            }
            assertTrue(isValid);
        }
    }
}
