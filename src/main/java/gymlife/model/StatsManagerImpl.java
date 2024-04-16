package gymlife.model;
import java.util.Optional;

import gymlife.model.api.StatsModel;
import gymlife.utility.Constants;
import gymlife.model.api.DaysModel;
import gymlife.utility.GameDifficulty;
import gymlife.model.api.StatsManager;


public class StatsManagerImpl implements StatsManager{
    StatsModel gameStats;
    DaysModel gameDays;
    
    public StatsManagerImpl(final GameDifficulty difficulty) {
        gameStats = new StatsModelImpl();
        gameDays = new DaysModelImpl(difficulty.getDays());
    }
}
