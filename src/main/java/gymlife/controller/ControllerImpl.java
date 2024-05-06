package gymlife.controller;

import gymlife.model.*;
import gymlife.model.api.GameMap;
import gymlife.model.api.MapManager;
import gymlife.utility.Directions;
import gymlife.utility.Position;
import gymlife.controller.api.Controller;
import gymlife.model.api.CharacterModel;

/**
 * Class responsible for managing Character movements.
 */
public class ControllerImpl implements Controller {
    private final CharacterModel characterModel = new CharacterModelImpl();
    private final MapManager mapManager = new MapManagerImpl(GameMapImpl.HOUSE_MAP);
    private final ScenariosManager scenariosManager = new ScenariosManager();
    private final InteractionsManager interactionsManager = new InteractionsManager(scenariosManager);

    /**
     * Moves the character in the specified direction.
     * 
     * @param dir the direction in which to move the character
     */
    @Override
    public void moveCharacter(final Directions dir) {
        characterModel.move(dir);
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
    public void goToGym(final GameMap newMap) {
        mapManager.changeMap(newMap);
    }

    /**
     * Method to return the current map, taken from the MapManager.
     * @return Returns the current {@code GameMap}.
     */
    public GameMap getCurrentMap() {
        return mapManager.getCurrentMap();
    }

    /**
     * Method to execute the action relative to the cell on which the player is standing.
     */
    public void cellInteraction() {
        mapManager.getCurrentMap()
                .getCellAtCoord(characterModel.getCharacterPos())
                .getInteraction()
                .ifPresent((e) -> e.interact(interactionsManager));
    }
}
