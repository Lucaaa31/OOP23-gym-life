package gymlife.controller;

import gymlife.model.CharacterModelImpl;
import gymlife.model.InteractionsManager;
import gymlife.model.GameMapImpl;
import gymlife.model.MapManagerImpl;
import gymlife.model.StatsManagerImpl;
import gymlife.model.ScenariosManager;
import gymlife.model.api.GameMap;
import gymlife.model.api.MapManager;
import gymlife.model.api.StatsManager;
import gymlife.utility.Directions;
import gymlife.utility.GameDifficulty;
import gymlife.utility.Position;
import gymlife.controller.api.Controller;
import gymlife.model.api.CharacterModel;
import gymlife.utility.StatsType;

/**
 * Class responsible for managing Character movements.
 */
public class ControllerImpl implements Controller {
    private final CharacterModel characterModel = new CharacterModelImpl();
    private final MapManager mapManager = new MapManagerImpl(GameMapImpl.HOUSE_MAP);
    private final ScenariosManager scenariosManager = new ScenariosManager();
    private final StatsManager statsManager = new StatsManagerImpl(GameDifficulty.EASY);
    private final InteractionsManager interactionsManager = new InteractionsManager(
            scenariosManager,
            statsManager
            );

    /**
     * Moves the character in the specified direction.
     * 
     * @param dir the direction in which to move the character
     */
    @Override
    public void moveCharacter(final Directions dir) {
        Position positionToGo = new Position(getCharacterPos().X() + dir.getPos().X(), getCharacterPos().Y() + dir.getPos().Y());
        if (mapManager.getCurrentMap().checkBorders(positionToGo) && !mapManager.getCurrentMap().isCellCollidable(positionToGo)){
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
     * Method to directly change the current map to parameter newMap.
     * @param newMap GameMap to switch the current map to.
     */
    @Override
    public void goToNewMap(final GameMap newMap) {
        mapManager.changeMap(newMap);
    }

    /**
     * Method to return the current map, taken from the MapManager.
     * @return Returns the current {@code GameMap}.
     */
    @Override
    public GameMap getCurrentMap() {
        return mapManager.getCurrentMap();
    }

    /**
     * Method to execute the action relative to the cell on which the player is standing.
     */
    @Override
    public void cellInteraction() {
        mapManager.getCurrentMap()
                .getCellAtCoord(characterModel.getCharacterPos())
                .getInteraction()
                .ifPresent((e) -> e.interact(interactionsManager));
    }

    @Override
    public int getPlayerLevel() {
        return statsManager.getStats().get(StatsType.MASS).getCount()/75;
    }
}