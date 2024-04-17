package gymlife.model.api;

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
