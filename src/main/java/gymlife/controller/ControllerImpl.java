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
    private MinigameManager minigameManager;
    private Timer timer = new Timer();

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
     * Sets the difficulty level of the current minigame.
     *
     * @param difficulty the difficulty level to set
     */
    public void setDifficulty(final MinigameDifficulty difficulty) {
        minigameManager.setDifficulty(difficulty)
                .getCurrentMinigame()
                .setTimer(timer);
        minigameManager.startMinigame();
    }

    public Timer getTimer(){
        return this.timer;
    }

    /**
     * Notifies the current minigame that a button has been pressed,
     * during the Minigame.
     */
    @Override
    public void notifyButtonPressed() {
        minigameManager.getCurrentMinigame().notifyButtonPressed();
    }

    /**
     * Retrieves the running time of the Timer of the minigame.
     *
     * @return a list of integers representing the running time
     */
    @Override
    public List<Integer> getTime() {
        return timer.getRunningTime();
    }

    /**
     * Retrieves the state of the current minigame,
     * used by the view for updating herself.
     *
     * @return the state of the current minigame
     */
    public int getState() {
        return minigameManager.getCurrentMinigame().getState();
    }

    /**
     * Retrieves the type of the current minigame,
     * used by the view for create the istance of the minigame.
     *
     * @return the type of the current minigame
     */
    @Override
    public MinigameType getMinigameType() {
        return minigameManager.getMinigameType();
    }

    /**
     * Sets the minigame manager for this controller.
     *
     * @param minigameManager the minigame manager to set
     */
    public void setMinigameManager(final MinigameManager minigameManager) {
        this.minigameManager = minigameManager;
    }
}
