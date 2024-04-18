package gymlife.model.api;
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
    boolean getCollision();

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
}
