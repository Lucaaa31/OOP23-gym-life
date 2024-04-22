package gymlife.controller;

import gymlife.utility.Directions;
import gymlife.utility.MinigameDifficulty;
import gymlife.utility.Position;
import gymlife.controller.api.Controller;
import gymlife.model.api.CharacterModel;
import gymlife.model.CharacterModelImpl;
import gymlife.model.MinigameManager;

/**
 * Class responsible for managing Character movements.
 */
public class ControllerImpl implements Controller {
    private final CharacterModel characterModel = new CharacterModelImpl();
    private final MinigameManager minigameManager = new MinigameManager();

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

    @Override
    public void setDifficulty(final MinigameDifficulty selectedDifficulty) {
        minigameManager.setDifficulty(selectedDifficulty);
    }
}
