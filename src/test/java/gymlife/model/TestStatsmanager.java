package gymlife.model;

import java.util.Map;
import java.util.HashMap;
import gymlife.model.encounter.Encounter;
import gymlife.model.encounter.EncountersConstants;
import gymlife.model.statistics.StatsManagerImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import gymlife.model.statistics.GameDifficulty;
import gymlife.model.statistics.StatsConstants;
import gymlife.model.statistics.StatsType;
import gymlife.model.statistics.api.StatsManager;

class TestStatsmanager {

    @Test
    void testInitiation() {
        final StatsManager stats = new StatsManagerImpl(GameDifficulty.EASY);
        stats.resetAll();
        assertEquals(StatsConstants.STARTING_STATS_LEVEL, stats.getCommonStats().get(StatsType.HAPPINESS).getCount());
        assertEquals(StatsConstants.STARTING_STATS_LEVEL, stats.getCommonStats().get(StatsType.STAMINA).getCount());
        assertEquals(StatsConstants.STARTING_STATS_LEVEL, stats.getCommonStats().get(StatsType.LEG_MASS).getCount());
        assertEquals(StatsConstants.STARTING_STATS_LEVEL, stats.getCommonStats().get(StatsType.BACK_MASS).getCount());
        assertEquals(StatsConstants.STARTING_STATS_LEVEL, stats.getCommonStats().get(StatsType.CHEST_MASS).getCount());
        assertEquals(StatsConstants.STARTING_STATS_LEVEL * 3, stats.getCommonStats().get(StatsType.MASS).getCount());
    }

    @Test
    void testGameOver() {
        final StatsManager stats = new StatsManagerImpl(GameDifficulty.EASY);
        stats.resetAll();
        stats.getCommonStats().get(StatsType.HAPPINESS).multiIncrement(-StatsConstants.MAX_STATS_LEVEL);
        assertTrue(stats.isGameOver());
        stats.getCommonStats().get(StatsType.HAPPINESS).multiIncrement(1);
        assertFalse(stats.isGameOver());
        stats.resetAll();
        for (int i = 0; i < GameDifficulty.EASY.getDays(); i++) {
            stats.newDay();
        }
        assertTrue(stats.isGameOver());
    }

    @Test
    void testWin() {
        final StatsManager stats = new StatsManagerImpl(GameDifficulty.EASY);
        /*
         * add for each type of mass the exact amount 
         */
        stats.getCommonStats().get(StatsType.BACK_MASS).multiIncrement(StatsConstants.MAX_STATS_LEVEL
        - StatsConstants.STARTING_STATS_LEVEL);
        assertFalse(stats.checkWin());
        stats.getCommonStats().get(StatsType.CHEST_MASS).multiIncrement(StatsConstants.MAX_STATS_LEVEL
        - StatsConstants.STARTING_STATS_LEVEL);
        assertFalse(stats.checkWin());
        stats.getCommonStats().get(StatsType.LEG_MASS).multiIncrement(StatsConstants.MAX_STATS_LEVEL
        - StatsConstants.STARTING_STATS_LEVEL);
        assertTrue(stats.checkWin());
        stats.getCommonStats().get(StatsType.MASS).multiIncrement(-StatsConstants.MAX_MASS_LEVEL);
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
        assertEquals(StatsConstants.MAX_STATS_LEVEL, stats.getCommonStats().get(StatsType.HAPPINESS).getCount());
        stats.acceptEncounter(encounter);
        stats.resetAll();
    }

    @Test
    void testGetAllStats() {
        final StatsManager stats = new StatsManagerImpl(GameDifficulty.EASY);
        stats.resetAll();

        /*
         * add for each type of mass the exact amount
         */
        assertEquals(StatsConstants.STARTING_STATS_LEVEL, stats.getCommonStats().get(StatsType.HAPPINESS).getCount());
        assertEquals(StatsConstants.STARTING_STATS_LEVEL, stats.getCommonStats().get(StatsType.HAPPINESS).getCount());
        assertEquals(StatsConstants.STARTING_MASS_LEVEL, stats.getCommonStats().get(StatsType.MASS).getCount());
        assertEquals(StatsConstants.STARTING_STATS_LEVEL, stats.getMoney());
        assertEquals(GameDifficulty.EASY.getDays(), stats.getDays());
        stats.resetAll();
    }

    @Test
    void testMultiIncrementStats() {
        final StatsManager stats = new StatsManagerImpl(GameDifficulty.EASY);
        stats.resetAll();
        stats.multiIncrementStat(StatsType.HAPPINESS, TestConstants.TEST_MULTI_INCREMENT_POSITIVE_5); 
        assertEquals(TestConstants.TEST_MULTI_INCREMENT_POSITIVE_5 + StatsConstants.STARTING_STATS_LEVEL,
                stats.getCommonStats().get(StatsType.HAPPINESS).getCount());
        stats.resetAll();
        stats.multiIncrementStat(StatsType.MONEY, TestConstants.TEST_MULTI_INCREMENT_POSITIVE_5);
        assertEquals(TestConstants.TEST_MULTI_INCREMENT_POSITIVE_5 + StatsConstants.STARTING_STATS_LEVEL,
                stats.getMoney());
    }

    @Test
    void testSetStats() {
        final StatsManager stats = new StatsManagerImpl(GameDifficulty.EASY);
        stats.setStat(StatsType.HAPPINESS, 10);
        assertEquals(10, stats.getCommonStats().get(StatsType.HAPPINESS).getCount());
        stats.resetAll();
        stats.setStat(StatsType.MONEY, TestConstants.TEST_MULTI_INCREMENT_POSITIVE_5);
        assertEquals(TestConstants.TEST_MULTI_INCREMENT_POSITIVE_5, stats.getMoney());
        stats.setStat(StatsType.MASS, TestConstants.TEST_MULTI_INCREMENT_POSITIVE_5);
        assertEquals(TestConstants.TEST_MULTI_INCREMENT_POSITIVE_5 * 3, stats.getCommonStats().get(StatsType.MASS).getCount());
    }

    @Test
    void testDenyEncounter() {
        final int medium = 10;
        final int zero = 0;
        final int high =  20;
        final StatsManager stats = new StatsManagerImpl(GameDifficulty.EASY);
        stats.setStat(StatsType.HAPPINESS, medium);
        assertEquals(medium, stats.getCommonStats().get(StatsType.HAPPINESS).getCount());
        stats.denyEncounter(new Encounter("GYM_BRO", "prova",
                EncountersConstants.gymBroAccept(), EncountersConstants.gymBroDeny()));
        assertEquals(zero, stats.getCommonStats().get(StatsType.HAPPINESS).getCount());
        stats.setStat(StatsType.MONEY, high);
        stats.denyEncounter(new Encounter("ROBBER", "prova",
                EncountersConstants.robberAccept(), EncountersConstants.robberDeny()));
        assertEquals(zero, stats.getMoney());
    }

    @Test
    void testChangeStatsWithFood() {
        final int medium = 10;
        final int zero = 0;
        final Map<StatsType, Integer> statsChanged = new HashMap<>();
        statsChanged.put(StatsType.HAPPINESS, -medium);
        final StatsManager stats = new StatsManagerImpl(GameDifficulty.EASY);
        stats.setStat(StatsType.HAPPINESS, medium);
        assertEquals(medium, stats.getCommonStats().get(StatsType.HAPPINESS).getCount());
        stats.changeStatsWithFood(statsChanged);
        assertEquals(zero, stats.getCommonStats().get(StatsType.HAPPINESS).getCount());

    }

}
