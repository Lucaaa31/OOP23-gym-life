package gymlife.controller;

import java.util.Optional;

import gymlife.utility.Directions;
import gymlife.utility.GameDifficulty;
import gymlife.utility.Position;
import gymlife.controller.api.Controller;
import gymlife.model.api.CharacterModel;
import gymlife.model.CharacterModelImpl;
import gymlife.model.api.DaysModel;
import gymlife.model.DaysModelImpl;
import gymlife.model.StatsManagerImpl;
import gymlife.model.api.StatsManager;

/**
 * Class responsible for managing Character movements.
 */
public class ControllerImpl implements Controller {
    private final CharacterModel characterModel = new CharacterModelImpl();
    private final StatsManager statsManager = new StatsManagerImpl(GameDifficulty.EASY);
    /**
     * Moves the character up.
     */
    @Override
    public void moveUp() {
        characterModel.move(Optional.of(Directions.UP));
    }
    /**
     * Moves the character down.
     */
    @Override
    public void moveDown() {
        characterModel.move(Optional.of(Directions.DOWN));
    }
    /**
     * Moves the character to the right.
     */
    @Override
    public void moveRight() {
        characterModel.move(Optional.of(Directions.RIGHT));
    }
    /**
     * Moves the character to the left.
     */
    @Override
    public void moveLeft() {
        characterModel.move(Optional.of(Directions.LEFT));
    }

    public Position getCharacterPos() {
        return characterModel.getCharacterPos();
    }
 
}
