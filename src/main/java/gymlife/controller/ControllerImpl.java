package gymlife.controller;

import java.util.Optional;

import gymlife.utility.Directions;
import gymlife.utility.Position;
import gymlife.controller.api.Controller;
import gymlife.model.api.CharacterModel;
import gymlife.model.CharacterModelImpl;
import gymlife.model.api.DaysModel;
import gymlife.model.DaysModelImpl;
import gymlife.model.api.StatsModel;
import gymlife.model.StatsModelImpl;

/**
 * Class responsible for managing Character movements.
 */
public class ControllerImpl implements Controller {
    private final CharacterModel characterModel = new CharacterModelImpl();
    private final DaysModel daysModel = new DaysModelImpl();
    private final StatsModel statsModel = new StatsModelImpl();
    /**
     * Moves the character up.
     */
    @Override
    public void moveUp() {
        characterModel.setNewDir(Optional.of(Directions.UP));
    }
    /**
     * Moves the character down.
     */
    @Override
    public void moveDown() {
        characterModel.setNewDir(Optional.of(Directions.DOWN));
    }
    /**
     * Moves the character to the right.
     */
    @Override
    public void moveRight() {
        characterModel.setNewDir(Optional.of(Directions.RIGHT));
    }
    /**
     * Moves the character to the left.
     */
    @Override
    public void moveLeft() {
        characterModel.setNewDir(Optional.of(Directions.LEFT));
    }

    public Position getCharacterPos() {
        return characterModel.getCharacterPos();
    }
 
}
