package gymlife.model.minigame;

import gymlife.model.minigame.api.MinigameManager;
import gymlife.model.minigame.api.ScoringTableManager;
import gymlife.utility.minigame.MinigameDifficulty;
import gymlife.utility.minigame.MinigameState;
import gymlife.utility.minigame.MinigameType;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * The MinigameManager class is responsible for managing the current minigame in
 * the gym life application.
 * It provides methods to start a minigame, set the current minigame type, set
 * the difficulty level, set the timer,
 * and retrieve the current minigame type and instance.
 */
public class MinigameManagerImpl implements MinigameManager {
    private Minigame currentMinigame;
    private MinigameType currentMinigameType;
    private final ScoringTableManager scoringTableManager = new ScoringTableManagerImpl();

    /**
     * Constructs a new MinigameManager object.
     */
    public MinigameManagerImpl() {
        this.currentMinigame = null;
        this.currentMinigameType = null;
    }


    /**
     * Get the sequence for the lat and squat minigame.
     *
     * @return the sequence of the minigame
     */
    @Override
    public List<String> getSequence() {
        return currentMinigame.getSequence();
    }


    /**
     * Sets the current minigame type.
     *
     * @param minigameType the minigame type to set
     */
    @Override
    public void setCurrentMinigame(final MinigameType minigameType) {
        this.currentMinigameType = minigameType;
        try {
            this.currentMinigame = (Minigame) Class
                    .forName(minigameType.getStrategy())
                    .getDeclaredConstructor()
                    .newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException
                 | InvocationTargetException ignored) {
        }
    }

    /**
     * Sets the difficulty level of the current minigame.
     *
     * @param selectedDifficulty the difficulty level to set
     */
    @Override
    public void setMinigameDifficulty(final MinigameDifficulty selectedDifficulty) {
        this.currentMinigame.setDifficulty(selectedDifficulty);
    }


    /**
     * Notify the minigame of an action of the player.
     */
    @Override
    public void notifyUserAction(final String button) {
        currentMinigame.notifyUserAction(button);
    }


    /**
     * Retrieves the current minigame type.
     *
     * @return the current minigame type
     */
    @Override
    public MinigameType getMinigameType() {
        return currentMinigameType;
    }


    /**
     * Used to check if the minigame is ended.
     *
     * @return 0 if the minigame isn't started, 1 if the minigame is running, 2 if the minigame is ended
     */
    @Override
    public MinigameState getMinigameState() {
        if (currentMinigame == null) {
            return MinigameState.NOT_STARTED;
        }
        return currentMinigame.getMinigameState();
    }

    /**
     * Used to get the difficulty of the minigame.
     *
     * @return the difficulty of the minigame
     */
    @Override
    public MinigameDifficulty getDifficulty() {
        return currentMinigame.getDifficulty();
    }

    /**
     * Used to get the end time of the minigame.
     *
     * @return the end time of the minigame
     */
    @Override
    public int getTimeMinigame() {
        return currentMinigame.getTimeMinigame();
    }

    /**
     * Update the minigame result.
     */
    @Override
    public void updateMinigameResult() {
        scoringTableManager.updateMinigameScore(getMinigameType(),
                getDifficulty(),
                getTimeMinigame());
    }

    /**
     * Get the minigame score.
     *
     * @return the minigame score
     */
    @Override
    public List<Integer> getMinigameScore(final MinigameType minigameType, final MinigameDifficulty difficulty) {
        return scoringTableManager.getMinigameScore(minigameType, difficulty);
    }


}
