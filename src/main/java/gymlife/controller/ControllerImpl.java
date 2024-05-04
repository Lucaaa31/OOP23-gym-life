package gymlife.controller;

import gymlife.controller.api.Controller;
import gymlife.model.CharacterModelImpl;
import gymlife.model.Minigame.MinigameManager;
import gymlife.model.Minigame.Timer;
import gymlife.model.api.CharacterModel;
import gymlife.utility.Directions;
import gymlife.utility.MinigameDifficulty;
import gymlife.utility.MinigameType;
import gymlife.utility.Position;

import java.util.List;

/**
 * Class responsible for managing Character movements.
 */
public class ControllerImpl implements Controller {
    private final CharacterModel characterModel = new CharacterModelImpl();
    private final MinigameManager minigameManager = new MinigameManager();
    private Timer timer;

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


    public void setMinigame(final MinigameDifficulty difficulty) {
        minigameManager.setDifficulty(difficulty)
                .setCurrentMinigame(MinigameType.BENCH_PRESS)
                .setTimer(timer)
                .startMinigame();
    }

    @Override
    public void notifyKeyPressed(char keyChar) {
        minigameManager.getCurrentMinigame().notifyKeyPressed(keyChar);
    }

    public void getTimer(final Timer timer){
        this.timer = timer;
    }

    @Override
    public List<Integer> getTime() {
        return timer.getRunningTime();
    }
}






























