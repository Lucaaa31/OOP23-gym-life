package gymlife.model;

import gymlife.model.api.GameMap;
import gymlife.model.api.MapManager;

public class MapManagerImpl implements MapManager{

    private GameMap currentMap;

    public MapManagerImpl(final GameMap initialMap) {
        this.currentMap = initialMap;
    }
    @Override
    public void changeMap(GameMap newMap) {
        this.currentMap = newMap;
    }

    @Override
    public GameMap getCurrentMap() {
        return currentMap;
    }
}
