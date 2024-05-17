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
     * The standard color for the background of the fast travel interface.
     */
    public static final Color FAST_TRAVEL_MAP_BG_COLOR = new Color(4, 35, 0);
    /**
     * Standard path for the FastTravel images.
     */
    public static final String FAST_TRAVEL_FILES_PATH = "images/fastTravelMap/";
    /**
     * Standard color for Fast Travel buttons.
     */
    public static final Color BUTTONS_COLOR = new Color(0, 85, 14);

    private MapConstants() {

    }
}
