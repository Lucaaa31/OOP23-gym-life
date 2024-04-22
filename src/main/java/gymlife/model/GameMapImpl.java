package gymlife.model;


import java.util.Map;

import gymlife.model.api.Cell;
import gymlife.model.api.GameMap;
import gymlife.model.api.Pair;
import gymlife.utilities.MapConstants;
import gymlife.utilities.MapLoader;

public enum GameMapImpl implements GameMap {
    HOUSE_MAP(0, "house", MapLoader.load("maps/housemap.txt")),
    GYM_MAP(1, "gym", MapLoader.load("gymmap.txt")),
    SHOP_MAP(2, "shop", MapLoader.load("maps/shopmap.txt"));

    private final int id;
    private final String name;
    private final int dimY;
    private final int dimX;
    private final Map<Pair<Integer, Integer>, Cell> map;

    GameMapImpl(final int id, final String name, final Map<Pair<Integer, Integer>, Cell> map) {
        this.id = id;
        this.name = name;
        this.dimX = MapConstants.MAP_X_DIM;
        this.dimY = MapConstants.MAP_Y_DIM;
        this.map = map;
    }

    @Override
    public boolean isCellCollidable(final Pair<Integer, Integer> coord) {
        return this.getCellAtCoord(coord).getCollision();
    }

    @Override
    public Cell getCellAtCoord(final Pair<Integer, Integer> coord) {
        return map.get(coord);
    }

    @Override
    public boolean checkBorders(final Pair<Integer, Integer> coord) {
        return coord.getX() >= 0 && coord.getX() < this.dimX && coord.getY() >= 0 && coord.getY() < this.dimY;
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

}
