package gymlife.controller.api;

import gymlife.model.statistics.Counter;
import gymlife.model.statistics.StatsType;
import gymlife.model.api.GameMap;
import gymlife.utility.Directions;
import gymlife.utility.Position;

import java.util.Map;

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
     * Returns a Map that represents the current values of the statistics in the game.
     *
     * @return a Map of the statistics
     */
    Map<StatsType, Counter> getStatistics();

    /**
     * Method to go to a new map.
     * @param newMap new map to go to.
     */
    void goToNewMap(GameMap newMap);

    /**
     * Method to get the current map.
     * @return Returns the current map.
     */
    GameMap getCurrentMap();

    /**
     * Executes whatever action is associated with cell on which the character currently is.
     */
    void cellInteraction();

    /**
     * Method to get the level of mass of the character.
     * @return an int representing the level of mass from 1 to 4.
     */
    int getPlayerLevel();
}
