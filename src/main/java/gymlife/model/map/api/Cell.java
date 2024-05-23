package gymlife.model.map.api;

import gymlife.model.api.GameInteraction;

import java.util.Optional;

/**
 * An object that contains information on specific parts of the {@code GameMap}.
 * A Cell should contain information on what type it is, wether the player can
 * collide with it or not, and if there is any interaction when the player is on it.
 */
public interface Cell {

    /**
     * 
     * @return Returns true if the cell has collisions (player cannot be on it).
     */
    boolean isCollision();

    /**
     * 
     * @return Returns the id of the cell.
     */
    int getId();

    /**
     * 
     * @return Returns the name of the object through reflection.
     */
    String getName();

    /**
     * Method to get the interaction of the cell.
     * @return if the cell has no interaction it returns Optional.empty(), otherwise it returns a GameInteraction;
     */
    Optional<GameInteraction> getInteraction();
}
