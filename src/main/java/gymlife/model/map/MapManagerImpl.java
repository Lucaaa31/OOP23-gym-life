package gymlife.model.map;

import gymlife.model.encounter.api.EncountersFactory;
import gymlife.model.map.api.GameMap;
import gymlife.model.map.api.MapManager;
import gymlife.model.encounter.Encounter;

import java.util.Optional;

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
    public Optional<Encounter> changeMap(final GameMap newMap) {
        this.currentMap = newMap;
        return EncountersFactory.getRandomEncounter();
    }

    @Override
    public GameMap getCurrentMap() {
        return currentMap;
    }
}
