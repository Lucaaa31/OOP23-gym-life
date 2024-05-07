package gymlife.model;

import gymlife.model.encounter.EncountersList;
import gymlife.model.encounter.EncountersCases;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

class TestEncounters {
    @Test
    void testGetDescription() {
        assertEquals("A gym bro stop you in the street. Do you accept?", EncountersList.GYM_BRO.getDescription());
        assertEquals("An ice cream truck is parked outside the gym. Do you accept?", EncountersList.ICE_CREAM.getDescription());
        assertEquals("You find a bag of money on the ground. Do you take it?", EncountersList.MONEY_BAG.getDescription());
        assertEquals("A pusher offers you some steroids. Do you accept?", EncountersList.PUSHER.getDescription());
        assertEquals("A robber tries to steal your wallet. Do you fight back?", EncountersList.ROBBER.getDescription());
    }
    @Test
    void testGetAcceptCase() {
        assertEquals(EncountersCases.gymBroAccept(), EncountersList.GYM_BRO.getAcceptCase());
        assertEquals(EncountersCases.iceCreamAccept(), EncountersList.ICE_CREAM.getAcceptCase());
        assertEquals(EncountersCases.moneyBagAccept(), EncountersList.MONEY_BAG.getAcceptCase());
        assertEquals(EncountersCases.pusherAccept(), EncountersList.PUSHER.getAcceptCase());
        assertEquals(EncountersCases.robberAccept(), EncountersList.ROBBER.getAcceptCase());
    }
    @Test
    void testGetDenyCase() {
        assertEquals(EncountersCases.gymBroDeny(), EncountersList.GYM_BRO.getDenyCase());
        assertEquals(EncountersCases.iceCreamDeny(), EncountersList.ICE_CREAM.getDenyCase());
        assertEquals(EncountersCases.moneyBagDeny(), EncountersList.MONEY_BAG.getDenyCase());
        assertEquals(EncountersCases.pusherDeny(), EncountersList.PUSHER.getDenyCase());
        assertEquals(EncountersCases.robberDeny(), EncountersList.ROBBER.getDenyCase());
    }
    @Test
    void testGetRandomEncounter() {
        for (int i = 0; i < 1000; i++) {
            boolean isValid = false;
            final Optional<EncountersList> encounters = EncountersList.getRandomEncounter();
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
