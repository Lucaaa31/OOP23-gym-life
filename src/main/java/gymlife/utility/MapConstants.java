package gymlife.utility;

/**
 * Class containing useful constants for the usage and loading of {@code GameMaps}.
 */
public final class MapConstants {
    /**
     * Standard horizontal dimensions of {@code GameMap}.
     */
    public static final int MAP_X_DIM = 8;
    /**
     * Standard vertical dimensions of {@code GameMap}.
     */
    public static final int MAP_Y_DIM = 6;
    /**
     * Standard path for Map files.
     */
    public static final String MAP_FILES_PATH = "maps/";
    /**
     * The position on which the player must spawn when going into the House map.
     */
    public static final Position HOUSE_INITIAL_POSITION = new Position(6, 2);
    /**
     * The position on which the player must spawn when going into the Shop map.
     */
    public static final Position SHOP_INITIAL_POSITION = new Position(6, 4);
    /**
     * The position on which the player must spawn when going into the Gym map.
     */
    public static final Position GYM_INITIAL_POSITION = new Position(1, 3);

    private MapConstants() {

    }
}
