package gymlife.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

class TestEncounters {
    @Test
    void testGetDescription() {
        assertEquals("A gym bro stop you in the street. Do you accept?", Encounters.GYM_BRO.getDescription());
        assertEquals("An ice cream truck is parked outside the gym. Do you accept?", Encounters.ICE_CREAM.getDescription());
        assertEquals("You find a bag of money on the ground. Do you take it?", Encounters.MONEY_BAG.getDescription());
        assertEquals("A pusher offers you some steroids. Do you accept?", Encounters.PUSHER.getDescription());
        assertEquals("A robber tries to steal your wallet. Do you fight back?", Encounters.ROBBER.getDescription());
    }
    @Test
    void testGetAcceptCase() {
        assertEquals(EncountersCases.gymBroAccept(), Encounters.GYM_BRO.getAcceptCase());
        assertEquals(EncountersCases.iceCreamAccept(), Encounters.ICE_CREAM.getAcceptCase());
        assertEquals(EncountersCases.moneyBagAccept(), Encounters.MONEY_BAG.getAcceptCase());
        assertEquals(EncountersCases.pusherAccept(), Encounters.PUSHER.getAcceptCase());
        assertEquals(EncountersCases.robberAccept(), Encounters.ROBBER.getAcceptCase());
    }
    @Test
    void testGetDenyCase() {
        assertEquals(EncountersCases.gymBroDeny(), Encounters.GYM_BRO.getDenyCase());
        assertEquals(EncountersCases.iceCreamDeny(), Encounters.ICE_CREAM.getDenyCase());
        assertEquals(EncountersCases.moneyBagDeny(), Encounters.MONEY_BAG.getDenyCase());
        assertEquals(EncountersCases.pusherDeny(), Encounters.PUSHER.getDenyCase());
        assertEquals(EncountersCases.robberDeny(), Encounters.ROBBER.getDenyCase());
    }
    @Test
    void testGetRandomEncounter() {
        for (int i = 0; i < 1000; i++) {
            boolean isValid = false;
            final Optional<Encounters> encounters = Encounters.getRandomEncounter();
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
}
