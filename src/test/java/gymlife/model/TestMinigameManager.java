package gymlife.model;

import gymlife.model.minigame.MinigameManagerImpl;

import gymlife.utility.minigame.MinigameDifficulty;
import gymlife.utility.minigame.MinigameState;
import gymlife.utility.minigame.MinigameType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for MinigameManager.
 */
class TestMinigameManager {
    private MinigameManagerImpl minigameManager;

    @BeforeEach
    void setUp() {
        minigameManager = new MinigameManagerImpl();
        minigameManager.setCurrentMinigame(MinigameType.BENCH_PRESS);
    }

    @Test
    void testMinigameType() {
        assert minigameManager.getMinigameType() == MinigameType.BENCH_PRESS;
    }

    @Test
    void testMinigameDifficulty() {
        minigameManager.setMinigameDifficulty(MinigameDifficulty.EASY);
        assert minigameManager.getDifficulty() == MinigameDifficulty.EASY;
        assert minigameManager.getMinigameState() == MinigameState.NOT_STARTED;
    }

    @Test
    void testMinigameManager() {
        minigameManager.setMinigameDifficulty(MinigameDifficulty.EASY);
        minigameManager.notifyUserAction("0");
        assert minigameManager.getMinigameState() == MinigameState.PRESSED_START;
        minigameManager.notifyUserAction("0");
        assert minigameManager.getMinigameState() == MinigameState.RUNNING;
        minigameManager.notifyUserAction("0");
        minigameManager.notifyUserAction("0");
        assert minigameManager.getMinigameState() == MinigameState.ENDED_WON;
    }

    @Test
    void testMinigameManagerFail() throws InterruptedException {
        final int sleepTime = 5000;
        minigameManager.setMinigameDifficulty(MinigameDifficulty.HARD);
        minigameManager.notifyUserAction("0");
        minigameManager.notifyUserAction("0");
        Thread.sleep(sleepTime);
        minigameManager.notifyUserAction("0");
        assert minigameManager.getMinigameState() == MinigameState.ENDED_LOST;
    }

    @Test
    void testMinigameManagerInvalidPress() throws InterruptedException {
        final int validPress = 1000;
        final int invalidPress = 4000;
        minigameManager.setMinigameDifficulty(MinigameDifficulty.MEDIUM);
        minigameManager.notifyUserAction("0");
        minigameManager.notifyUserAction("0");
        Thread.sleep(validPress);
        minigameManager.notifyUserAction("0");
        assert minigameManager.getMinigameState() == MinigameState.VALID_PRESS;
        Thread.sleep(invalidPress);
        minigameManager.notifyUserAction("1");
        assert minigameManager.getMinigameState() == MinigameState.INVALID_PRESS;
    }


}
