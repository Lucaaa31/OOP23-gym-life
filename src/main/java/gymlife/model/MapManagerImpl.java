package gymlife.model;

import gymlife.model.api.GameMap;
import gymlife.model.api.MapManager;

/**
 * Implementation of Interface MapManager, it has a private attribute {@code GameMap} that represents the current map.
 */
public final class MapManagerImpl implements MapManager {

    private GameMap currentMap;

    /**
     * Constructor to create a {@code MapManager} with an initial map to begin.
     * @param initialMap the {@code GameMap} to create the MapManager with.
     */
    public MapManagerImpl(final GameMap initialMap) {
        this.currentMap = initialMap;
    }

    @Override
    public void changeMap(final GameMap newMap) {
        this.currentMap = newMap;
    }

    @Override
    public GameMap getCurrentMap() {
        return currentMap;
    }
}
