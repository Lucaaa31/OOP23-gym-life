package gymlife.model.Minigame;

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
        new Thread((Runnable) currentMinigame).start();
    }

    /**
     * Sets the timer for the current minigame.
     *
     * @param timer taken by te Controller
     */
    public void setTimer(final TimerImpl timer) {
        currentMinigame.setTimer(timer);
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
     * Check if the timer in the current minigame is running.
     *
     * @return true if the timer is running, false otherwise
     */
    public boolean isTimerRunning() {
        return currentMinigame.isAlive();
    }

    /**
     * Retrieves the current state of the minigame.
     *
     * @return the current state of the minigame
     */
    public int getState() {
        return currentMinigame.getState();
    }

    /**
     * Retrieves the current minigame type.
     *
     * @return the current minigame type
     */
    public MinigameType getMinigameType() {
        return currentMinigameType;
    }
}
