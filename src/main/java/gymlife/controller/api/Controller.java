package gymlife.controller.api;

import gymlife.model.api.GameMap;
import gymlife.model.statistics.Counter;
import gymlife.model.statistics.StatsType;
import gymlife.utility.Directions;
import gymlife.utility.Position;
import gymlife.utility.ScenariosType;
import gymlife.utility.minigame.MinigameDifficulty;
import gymlife.utility.minigame.MinigameState;
import gymlife.utility.minigame.MinigameType;

import javax.annotation.concurrent.Immutable;
import java.util.List;
import java.util.Map;

/**
 * This interface defines the operations that a game controller should support.
 * The game controller is responsible for handling key inputs from the keyboard to move the character,
 * getting the current position of the character, getting the current game statistics,
 * managing the game map, executing actions associated with the cell on which the character currently is,
 * and getting the level of mass of the character.
 */
@Immutable
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
     * Returns a Map that represents the current values of the statistics in the game.
     *
     * @return a Map of the statistics
     */
    Map<StatsType, Counter> getStatistics();

    /**
     * Changes the current game map to the specified new map.
     *
     * @param newMap new map to go to.
     */
    void goToNewMap(GameMap newMap);

    /**
     * Method to get the current map.
     *
     * @return Returns the current map.
     */
    GameMap getCurrentMap();

    /**
     * Executes whatever action is associated with cell on which the character currently is.
     */
    void cellInteraction();

    /**
     * Returns the level of mass of the character.
     *
     * @return an int representing the level of mass from 1 to 4.
     */
    int getPlayerLevel();

    /**
     * Method to get the current Scenario of the game.
     *
     * @return Returns the ScenariosType of the current scenario.
     */
    ScenariosType getActualScenario();

    /**
     * Method to modify the current scenario.
     *
     * @param newScenario The ScenariosType to change the current one to.
     */
    void changeScenario(ScenariosType newScenario);

    /**
     * Method to return the player to the default position of the map he's in.
     */
    void resetPlayerPosition();

    /**
     * Sets the difficulty level of the minigame.
     *
     * @param difficulty the difficulty level to set
     */
    void setMinigameDifficulty(MinigameDifficulty difficulty);

    /**
     * Notifies the controller that a button has been pressed.
     */
    void notifyUserAction();


    /**
     * Set the minigame result, update the statistics and change the scenario.
     */
    void setMinigameResult();

    /**
     * Check the status of the minigame.
     *
     * @return an enum representing the state of the minigame
     */
    MinigameState getMinigameState();

    /**
     * Returns the difficulty level of the minigame.
     *
     * @return the enum of difficulty level of the minigame
     */
    MinigameDifficulty getDifficulty();


    /**
     * Returns the type of the current minigame.
     *
     * @return the minigame type
     */
    MinigameType getMinigameType();


    /**
     * Get the score of the player in the scoring table.
     *
     * @param minigameType the type of the minigame that has been played
     * @param difficulty   the difficulty of the minigame that has been played
     * @return the list of scores
     */
    List<Integer> getScores(MinigameType minigameType, MinigameDifficulty difficulty);


}
