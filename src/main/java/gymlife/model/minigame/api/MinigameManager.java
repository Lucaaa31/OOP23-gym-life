package gymlife.model.minigame.api;

import gymlife.utility.minigame.MinigameDifficulty;
import gymlife.utility.minigame.MinigameState;
import gymlife.utility.minigame.MinigameType;

import javax.annotation.concurrent.Immutable;
import java.util.List;


/**
 * The MinigameManager interface provides methods to manage the current minigame in the gym life application.
 */
@Immutable
public interface MinigameManager {

    /**
     * Sets the current minigame type.
     *
     * @param minigameType the minigame type to set
     */
    void setCurrentMinigame(MinigameType minigameType);

    /**
     * Retrieves the sequence of the current minigame.
     *
     * @return the sequence of the current minigame
     */
    List<String> getSequence();

    /**
     * Sets the difficulty level of the current minigame.
     *
     * @param selectedDifficulty the difficulty level to set
     */
    void setMinigameDifficulty(MinigameDifficulty selectedDifficulty);

    /**
     * Notifies the current minigame that the player has done something.
     *
     * @param button the button pressed by the player
     */
    void notifyUserAction(String button);

    /**
     * Retrieves the current minigame type.
     *
     * @return the current minigame type
     */
    MinigameType getMinigameType();

    /**
     * Used to check if the minigame is ended.
     *
     * @return 0 if the minigame isn't started, 1 if the minigame is running, 2 if the minigame is ended
     */
    MinigameState getMinigameState();

    /**
     * Used to get the difficulty of the minigame.
     *
     * @return the difficulty of the minigame
     */
    MinigameDifficulty getDifficulty();

    /**
     * Used to get the end time of the minigame.
     *
     * @return the end time of the minigame
     */
    int getEndTime();


}
