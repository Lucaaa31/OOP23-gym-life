package gymlife.model.minigame;

import gymlife.model.api.Minigame;
import gymlife.utility.minigame.MinigameDifficulty;
import gymlife.utility.minigame.MinigameType;

import java.lang.reflect.InvocationTargetException;

/**
 * The MinigameManager class is responsible for managing the current minigame in
 * the gym life application.
 * It provides methods to start a minigame, set the current minigame type, set
 * the difficulty level, set the timer,
 * and retrieve the current minigame type and instance.
 */
public class MinigameManager {
    private Minigame currentMinigame;
    private MinigameType currentMinigameType;

    /**
     * Constructs a new MinigameManager object.
     */
    public MinigameManager() {
        this.currentMinigame = null;
        this.currentMinigameType = null;
    }

    /**
     * Starts the current minigame in a new thread.
     */
    public void startMinigame() {
        new BenchMinigame();
    }


    /**
     * Sets the current minigame type.
     *
     * @param minigameType the minigame type to set
     */
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
    public void setDifficulty(final MinigameDifficulty selectedDifficulty) {
        this.currentMinigame.setDifficulty(selectedDifficulty);
    }

    /**
     * Notifies the current minigame that the player has done something.
     */
    public void notifyUserAction() {
        currentMinigame.notifyUserAction();
    }

    /**
     * The view check if a reps is done.
     *
     * @return true if the reps is done, false otherwise
     */
    public boolean isRepsDone() {
        return currentMinigame.isRepsCompleted();
    }

    /**
     * Retrieves the current minigame type.
     *
     * @return the current minigame type
     */
    public MinigameType getMinigameType() {
        return currentMinigameType;
    }

    /**
     * Used to get the result of the minigame.
     *
     * @return the result of the minigame if is a win, 0 otherwise
     */
    public int getMinigameResult() {
        return currentMinigame.minigameResult();
    }

    /**
     * Used to check if the minigame is ended.
     *
     * @return true if the minigame is ended, false otherwise
     */
    public boolean isMinigameEnded() {
        return currentMinigame.isMinigameEnded();
    }

    /**
     * Used to get the difficulty of the minigame.
     *
     * @return the difficulty of the minigame
     */
    public MinigameDifficulty getDifficulty() {
        return currentMinigame.getDifficulty();
    }

}
