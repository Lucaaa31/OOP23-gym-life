package gymlife.model;

import gymlife.model.encounter.Encounter;
import gymlife.model.encounter.EncountersConstants;
import gymlife.model.statistics.StatsManagerImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import gymlife.utility.GameDifficulty;
import gymlife.model.statistics.StatsConstants;
import gymlife.model.statistics.StatsType;
import gymlife.model.statistics.api.StatsManager;

class TestStatsmanager {

    @Test
    void testInitiation() {
        final StatsManager stats = new StatsManagerImpl(GameDifficulty.EASY);
        stats.resetAll();
        assertEquals(StatsConstants.STARTING_STATS_LEVEL, stats.getStats().get(StatsType.HUMOR).getCount());
        assertEquals(StatsConstants.STARTING_STATS_LEVEL, stats.getStats().get(StatsType.STAMINA).getCount());
        assertEquals(StatsConstants.STARTING_STATS_LEVEL, stats.getStats().get(StatsType.LEG_MASS).getCount());
        assertEquals(StatsConstants.STARTING_STATS_LEVEL, stats.getStats().get(StatsType.BACK_MASS).getCount());
        assertEquals(StatsConstants.STARTING_STATS_LEVEL, stats.getStats().get(StatsType.CHEST_MASS).getCount());
        assertEquals(StatsConstants.STARTING_STATS_LEVEL * 3, stats.getStats().get(StatsType.MASS).getCount());
    }

    @Test
    void testGameOver() {
        final StatsManager stats = new StatsManagerImpl(GameDifficulty.EASY);
        stats.resetAll();
        stats.getStats().get(StatsType.HUMOR).multiIncrement(-StatsConstants.MAX_STATS_LEVEL);
        assertTrue(stats.isGameOver());
        stats.getStats().get(StatsType.HUMOR).multiIncrement(1);
        assertFalse(stats.isGameOver());
    }

    @Test
    void testWin() {
        final StatsManager stats = new StatsManagerImpl(GameDifficulty.EASY);
        /*
         * add for each type of mass the exact amount 
         */
        stats.getStats().get(StatsType.BACK_MASS).multiIncrement(StatsConstants.MAX_STATS_LEVEL 
        - StatsConstants.STARTING_STATS_LEVEL);
        assertFalse(stats.checkWin());
        stats.getStats().get(StatsType.CHEST_MASS).multiIncrement(StatsConstants.MAX_STATS_LEVEL 
        - StatsConstants.STARTING_STATS_LEVEL);
        assertFalse(stats.checkWin());
        stats.getStats().get(StatsType.LEG_MASS).multiIncrement(StatsConstants.MAX_STATS_LEVEL 
        - StatsConstants.STARTING_STATS_LEVEL);
        assertTrue(stats.checkWin());
        stats.getStats().get(StatsType.MASS).multiIncrement(-StatsConstants.MAX_MASS_LEVEL);
        stats.resetAll();
    }

    @Test
    void testAcceptEncounter() {
        final StatsManager stats = new StatsManagerImpl(GameDifficulty.EASY);
        final Encounter encounter = new Encounter("GYM_BRO", "prova",
                EncountersConstants.gymBroAccept(), EncountersConstants.gymBroDeny());
        /*
         * add for each type of mass the exact amount 
         */
        assertEquals(StatsConstants.STARTING_STATS_LEVEL, stats.getStats().get(StatsType.HUMOR).getCount());
        stats.acceptEncounter(encounter);
        stats.resetAll();
    }

}
