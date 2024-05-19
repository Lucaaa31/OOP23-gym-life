package gymlife.controller;

import gymlife.model.CharacterModelImpl;
import gymlife.model.InteractionsManager;
import gymlife.model.GameMapImpl;
import gymlife.model.MapManagerImpl;
import gymlife.model.encounter.Encounter;
import gymlife.model.statistics.StatsManagerImpl;
import gymlife.model.ScenariosManager;
import gymlife.model.api.GameMap;
import gymlife.model.api.MapManager;
import gymlife.model.statistics.Counter;

import gymlife.model.statistics.StatsType;
import gymlife.model.statistics.api.StatsManager;
import gymlife.utility.Directions;
import gymlife.utility.GameDifficulty;
import gymlife.utility.Position;
import gymlife.controller.api.Controller;
import gymlife.model.api.CharacterModel;
import gymlife.utility.ScenariosType;


import java.util.Map;
import java.util.Optional;

/**
 * This class implements the Controller interface and is responsible for managing Character movements.
 * It handles the character's movements, interactions with the game map, and game statistics.
 */
public class ControllerImpl implements Controller {
    private final CharacterModel characterModel = new CharacterModelImpl();
    private final MapManager mapManager = new MapManagerImpl(GameMapImpl.HOUSE_MAP);
    private final ScenariosManager scenariosManager;
    private final StatsManager statsManager;
    private final InteractionsManager interactionsManager;
    private Encounter currentEncounter;

    /**
     * Constructs a new ControllerImpl object with the specified game difficulty.
     *
     * @param difficulty the difficulty of the game.
     */
    public ControllerImpl(final GameDifficulty difficulty) {
        this.statsManager = new StatsManagerImpl(difficulty);
        this.scenariosManager = new ScenariosManager();
        this.currentEncounter = null;
        this.interactionsManager = new InteractionsManager(
                scenariosManager,
                statsManager
        );
    }

    /**
     * Moves the character in the specified direction.
     *
     * @param dir the direction in which to move the character
     */
    @Override
    public void moveCharacter(final Directions dir) {
        final Position positionToGo = new Position(
                getCharacterPos().X() + dir.getPos().X(),
                getCharacterPos().Y() + dir.getPos().Y());
        if (mapManager.getCurrentMap().checkBorders(positionToGo)
                && !mapManager.getCurrentMap().isCellCollidable(positionToGo)) {
            characterModel.move(dir);
        }
    }

    /**
     * Retrieves the current position of the character.
     *
     * @return the current position of the character
     */
    @Override
    public Position getCharacterPos() {
        return characterModel.getCharacterPos();
    }

    /**
     * Retrieves the current game statistics.
     *
     * @return a Map of the current game statistics
     */
    @Override
    public Map<StatsType, Counter> getStatistics() {
        return statsManager.getAllStats();
    }

    /**
     * Changes the current game map to the specified new map.
     *
     * @param newMap the new map to switch to
     */
    @Override
    public void goToNewMap(final GameMap newMap) {
        Optional<Encounter> encounter = mapManager.changeMap(newMap);
        if (encounter.isPresent()) {
            currentEncounter = encounter.get();
            changeScenario(ScenariosType.ENCOUNTER);
        } else {
            changeScenario(ScenariosType.INDOOR_MAP);
        }
    }

    /**
     * Retrieves the current game map.
     *
     * @return the current game map
     */
    @Override
    public GameMap getCurrentMap() {
        return mapManager.getCurrentMap();
    }

    /**
     * Executes the action associated with the cell on which the character is currently standing.
     */
    @Override
    public void cellInteraction() {
        mapManager.getCurrentMap()
                .getCellAtCoord(characterModel.getCharacterPos())
                .getInteraction()
                .ifPresent((e) -> e.interact(interactionsManager));
    }

    /**
     * Retrieves the level of mass of the character.
     *
     * @return an int representing the level of mass from 1 to 4
     */
    @Override
    public int getPlayerLevel() {
        final int div = 75;
        return statsManager.getStats().get(StatsType.MASS).getCount() / div + 1;
    }

    /**
     * Retrieves the current scenario type.
     * @return the current scenario.
     */
    @Override
    public ScenariosType getActualScenario() {
        return scenariosManager.getActualScenariosType();
    }

    /**
     * Method to change the scenario.
     * @param newScenario The ScenariosType to change the current one to.
     */
    @Override
    public void changeScenario(final ScenariosType newScenario) {
        scenariosManager.updateScenarios(newScenario);
    }

    /**
     * Method to return the character to the current map's default position.
     */
    @Override
    public void resetPlayerPosition() {
        characterModel.setPosition(mapManager.getCurrentMap().getDefaultPosition());
    }

    public Encounter getCurrentEncounter() {
        return currentEncounter;
    }

    public void resolveEncounter(boolean choice) {
        if(choice) {
            statsManager.acceptEncounter(currentEncounter);
        } else {
            statsManager.denyEncounter(currentEncounter);
        }
        changeScenario(ScenariosType.INDOOR_MAP);
    }
}
