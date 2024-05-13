package gymlife.utility;

import java.awt.Color;

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
     * Standard path for cells images.
     */
    public static final String CELLS_FILES_PATH = "images/cells/";
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
    /**
     * The standard color for the background of the fast travel interface.
     */
    public static final Color FAST_TRAVEL_MAP_BG_COLOR = new Color(4, 35, 0);

    private MapConstants() {

    }
}
