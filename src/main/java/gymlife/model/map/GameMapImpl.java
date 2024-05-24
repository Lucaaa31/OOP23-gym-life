package gymlife.model.map;


import java.util.Locale;
import java.util.Map;

import gymlife.model.map.api.Cell;
import gymlife.model.map.api.GameMap;
import gymlife.utility.MapConstants;
import gymlife.utility.MapLoader;
import gymlife.utility.Position;

/**
 * GameMapImpl is an enum with all the possible maps in the game, these are House, Shop, Gym.
 */
public enum GameMapImpl implements GameMap {
    /**
     * Map of the house.
     */
    HOUSE_MAP(0, "house", MapLoader.load("housemap.txt"), new Position(6, 2)),
    /**
     * Map of the gym.
     */
    GYM_MAP(1, "gym", MapLoader.load("gymmap.txt"), new Position(1, 3)),
    /**
     * Map of the shop.
     */
    SHOP_MAP(2, "shop", MapLoader.load("shopmap.txt"), new Position(6, 4));

    private final int id;
    private final String name;
    private final int dimY;
    private final int dimX;
    private final transient Map<Position, Cell> map;
    private final Position defaultPosition;

    GameMapImpl(final int id, final String name, final Map<Position, Cell> map, final Position position) {
        this.id = id;
        this.name = name;
        this.dimX = MapConstants.MAP_X_DIM;
        this.dimY = MapConstants.MAP_Y_DIM;
        this.map = map;
        this.defaultPosition = position;
    }

    @Override
    public boolean isCellCollidable(final Position coord) {
        return this.getCellAtCoord(coord).isCollision();
    }

    @Override
    public Cell getCellAtCoord(final Position coord) {
        return map.get(coord);
    }

    @Override
    public boolean checkBorders(final Position coord) {
        return coord.X() >= 0 && coord.X() < this.dimX && coord.Y() >= 0 && coord.Y() < this.dimY;
    }

    @Override
    public int getDimX() {
        return this.dimX;
    }

    @Override
    public int getDimY() {
        return this.dimY;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Static method to get an instance of GameMapImpl given a string.
     * @param name This string should be the simple name of the GameMap.
     * @return Returns the correspondent GameMap.
     */
    public static GameMapImpl fromString(final String name) {
        return valueOf(name.toUpperCase(new Locale("en")) + "_MAP");
    }

    @Override
    public Position getDefaultPosition() {
        return this.defaultPosition;
    }

}
