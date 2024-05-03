package gymlife.model;

import org.junit.jupiter.api.Test;

import gymlife.utility.EncountersConstants;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(EncountersConstants.GYM_BRO_ACCEPT, Encounters.GYM_BRO.getAcceptCase());
        assertEquals(EncountersConstants.ICE_CREAM_ACCEPT, Encounters.ICE_CREAM.getAcceptCase());
        assertEquals(EncountersConstants.MONEY_BAG_ACCEPT, Encounters.MONEY_BAG.getAcceptCase());
        assertEquals(EncountersConstants.PUSHER_ACCEPT, Encounters.PUSHER.getAcceptCase());
        assertEquals(EncountersConstants.ROBBER_ACCEPT, Encounters.ROBBER.getAcceptCase());
    }
    @Test
    void testGetDenyCase() {
        assertEquals(EncountersConstants.GYM_BRO_DENY, Encounters.GYM_BRO.getDenyCase());
        assertEquals(EncountersConstants.ICE_CREAM_DENY, Encounters.ICE_CREAM.getDenyCase());
        assertEquals(EncountersConstants.MONEY_BAG_DENY, Encounters.MONEY_BAG.getDenyCase());
        assertEquals(EncountersConstants.PUSHER_DENY, Encounters.PUSHER.getDenyCase());
        assertEquals(EncountersConstants.ROBBER_DENY, Encounters.ROBBER.getDenyCase());
    }
    @Test
    void testGetEncounter() {
        boolean isValid = false;
        Optional<Encounters> encounters = Encounters.getRandomEncounter();
        if (encounters.isEmpty() 
        || encounters.get().equals(Encounters.GYM_BRO)
        || encounters.get().equals(Encounters.ICE_CREAM)
        || encounters.get().equals(Encounters.MONEY_BAG)
        || encounters.get().equals(Encounters.PUSHER)
        || encounters.get().equals(Encounters.ROBBER)) {
            isValid = true;
        }
        assertEquals(true, isValid);
    }
}
