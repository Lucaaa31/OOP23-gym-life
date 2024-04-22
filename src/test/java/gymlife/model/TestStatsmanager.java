package gymlife.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import gymlife.utility.GameDifficulty;
import gymlife.utility.StatsConstants;
import gymlife.utility.StatsType;
import gymlife.model.api.StatsManager;

public class TestStatsmanager {

    @Test
    public void TestInitiation () {
        StatsManager stats = new StatsManagerImpl(GameDifficulty.EASY);
        assertEquals(StatsConstants.STARTING_STATS_LEVEL, stats.getStats().get(StatsType.HUMOR).getCount());
        assertEquals(StatsConstants.STARTING_STATS_LEVEL, stats.getStats().get(StatsType.STAMINA).getCount());
        assertEquals(StatsConstants.STARTING_STATS_LEVEL, stats.getStats().get(StatsType.LEG_MASS).getCount());
        assertEquals(StatsConstants.STARTING_STATS_LEVEL, stats.getStats().get(StatsType.BACK_MASS).getCount());
        assertEquals(StatsConstants.STARTING_STATS_LEVEL, stats.getStats().get(StatsType.CHEST_MASS).getCount());
        assertEquals(StatsConstants.STARTING_STATS_LEVEL * 3, stats.getStats().get(StatsType.MASS).getCount());
    }

    @Test
    public void TestGameOver () {
        StatsManager stats = new StatsManagerImpl(GameDifficulty.EASY);
        stats.getStats().get(StatsType.HUMOR).multiIncrement(-100);
        assertEquals(true, stats.isGameOver());
        stats.getStats().get(StatsType.HUMOR).multiIncrement(1);
        assertEquals(false, stats.isGameOver());
    }

    @Test
    public void TestWin () {
        StatsManager stats = new StatsManagerImpl(GameDifficulty.EASY);
        /*
         * add for each type of mass the exact amount 
         */
        stats.getStats().get(StatsType.BACK_MASS).multiIncrement(StatsConstants.MAX_STATS_LEVEL - StatsConstants.STARTING_STATS_LEVEL);
        assertEquals(false, stats.checkWin());
        stats.getStats().get(StatsType.CHEST_MASS).multiIncrement(StatsConstants.MAX_STATS_LEVEL - StatsConstants.STARTING_STATS_LEVEL);
        assertEquals(false, stats.checkWin());
        stats.getStats().get(StatsType.LEG_MASS).multiIncrement(StatsConstants.MAX_STATS_LEVEL - StatsConstants.STARTING_STATS_LEVEL);
        assertEquals(true, stats.checkWin());
    }
}
