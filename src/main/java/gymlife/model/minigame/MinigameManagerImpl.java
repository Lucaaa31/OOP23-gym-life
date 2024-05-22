package gymlife.model.minigame;

import gymlife.model.api.Minigame;
import gymlife.model.api.MinigameManager;
import gymlife.utility.minigame.MinigameDifficulty;
import gymlife.utility.minigame.MinigameState;
import gymlife.utility.minigame.MinigameType;

import java.lang.reflect.InvocationTargetException;

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

    /**
     * Constructs a new MinigameManager object.
     */
    public MinigameManagerImpl() {
        this.currentMinigame = null;
        this.currentMinigameType = null;
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
                    .forName(minigameType.getName())
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
     * Notifies the current minigame that the player has done something.
     */
    @Override
    public void notifyUserAction() {
        currentMinigame.notifyUserAction();
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
    public int getEndTime() {
        return currentMinigame.getTimeMinigame();
    }


}
