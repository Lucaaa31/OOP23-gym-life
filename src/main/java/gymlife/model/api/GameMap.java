package gymlife.model.api;

public interface GameMap {
    /**
     * 
     * @param coord A specific position (Pair).
     * @return Returns true if the cell in the given position is collidable.
     */
    boolean isCellCollidable(Pair<Integer, Integer> coord);

    /**
     * 
     * @param coord A specific position (Pair).
     * @return Returns the cell in the given position.
     */
    Cell getCellAtCoord(Pair<Integer, Integer> coord);

    /**
     * 
     * @param coord A specific position (Pair).
     * @return Returns true if the given coordinate is within the borders of the
     *         map.
     */
    boolean checkBorders(Pair<Integer, Integer> coord);

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
}
