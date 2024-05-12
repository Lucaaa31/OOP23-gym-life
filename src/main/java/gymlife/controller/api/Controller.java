package gymlife.controller.api;

import java.util.Map;

import gymlife.model.Minigame.MinigameManager;
import gymlife.model.api.GameMap;
import gymlife.model.statistics.Counter;
import gymlife.model.statistics.StatsType;
import gymlife.utility.Directions;
import gymlife.utility.MinigameDifficulty;
import gymlife.utility.MinigameType;
import gymlife.utility.Position;

/**
 * Interface that handles key inputs from the keyboard to move the character.
 */
public interface Controller {
    /**
     * Moves the character in the specified direction.
     * 
     * @param dir the direction in which to move the character
     */
    void moveCharacter(Directions dir);

    /**
     * Returns the current position of the character.
     * 
     * @return the position of the character
     */
    Position getCharacterPos();

    /**
     * Sets the difficulty level of the minigame.
     * 
     * @param difficulty the difficulty level to set
     */
    void setDifficulty(MinigameDifficulty difficulty);

    /**
     * Notifies the controller that a button has been pressed.
     */
    void notifyButtonPressed();

    /**
     * Returns the current time in the game.
     * 
     * @return the current time
     */
    int getTime();

    /**
     * Returns the current state of the game.
     * 
     * @return the current state
     */
    int getState();

    /**
     * Returns the type of the current minigame.
     * 
     * @return the minigame type
     */
    MinigameType getMinigameType();


    /**
     * Returns the visibility status of the timer.
     * 
     * @return true if the timer is visible, false otherwise
     */
    boolean isTimerRunning();

    /**
     * Returns a Map that represents the current values of the statistics in the
     * game.
     *
     * @return a Map of the statistics
     */
    Map<StatsType, Counter> getStatistics();

    /**
     * javadoc.
     *
     * @param newMap javadoc.
     */
    void goToGym(GameMap newMap);

    /**
     * javadoc.
     *
     * @return javadoc.
     */
    GameMap getCurrentMap();


    /**
     * javadoc.
     */
    void cellInteraction();


    /**
     * temporary.
     * @param minigameManager temporary.
     */
    void setMinigameManager(MinigameManager minigameManager);
}
