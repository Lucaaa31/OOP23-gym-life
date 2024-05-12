package gymlife.model.Minigame;

import gymlife.model.api.Minigame;
import gymlife.utility.MinigameDifficulty;
import gymlife.utility.MinigameType;

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
     * @return the updated MinigameManager instance
     */
    public MinigameManager setDifficulty(final MinigameDifficulty selectedDifficulty) {
        this.currentMinigame.setDifficulty(selectedDifficulty);
        return this;
    }

    /**
     * Retrieves the current minigame instance.
     *
     * @return the current minigame instance
     */
    public Minigame getCurrentMinigame() {
        return currentMinigame;
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
