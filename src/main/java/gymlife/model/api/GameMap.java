package gymlife.model.api;

import gymlife.utility.Position;

/**
 * GameMap represents maps in the game, has methods th check the borders, get a specific cell and get collisions of a cell.
 */
public interface GameMap {
    /**
     * 
     * @param coord A specific position (Position).
     * @return Returns true if the cell in the given position is collidable.
     */
    boolean isCellCollidable(Position coord);

    /**
     * 
     * @param coord A specific position (Position).
     * @return Returns the cell in the given position.
     */
    Cell getCellAtCoord(Position coord);

    /**
     * 
     * @param coord A specific position (Position).
     * @return Returns true if the given coordinate is within the borders of the
     *         map.
     */
    boolean checkBorders(Position coord);

    /**
     * 
     * @return Returns the horizontal dimensions of the map
     */
    int getDimX();

    /**
     * 
     * @return Returns the vertical dimensions of the map.
     */
    int getDimY();

    /**
     * 
     * @return Returns the map's id.
     */
    int getId();

    /**
     * 
     * @return Returns map's symbolic name.
     */
    String getName();

    /**
     * Method to get the position at which the player must spawn when the map loads.
     * @return default player position.
     */
    Position getDefaultPosition();
}
