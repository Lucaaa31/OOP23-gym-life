package gymlife.controller;

import gymlife.model.CharacterModelImpl;
import gymlife.model.InteractionsManager;
import gymlife.model.MapManagerImpl;
import gymlife.model.ScenariosManager;
import gymlife.model.GameMapImpl;
import gymlife.model.api.GameMap;
import gymlife.model.api.MapManager;
import gymlife.model.statistics.Counter;
import gymlife.model.statistics.StatsManagerImpl;

import gymlife.model.statistics.StatsType;
import gymlife.model.statistics.api.StatsManager;
import gymlife.utility.Directions;
import gymlife.utility.GameDifficulty;
import gymlife.utility.Position;
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
import java.util.Optional;


import java.util.Map;

/**
 * Class responsible for managing Character movements.
 */
public class ControllerImpl implements Controller {
    private final CharacterModel characterModel = new CharacterModelImpl();
    private MinigameManager minigameManager;
    private Timer timer = new Timer();

    private final MapManager mapManager = new MapManagerImpl(GameMapImpl.HOUSE_MAP);
    private final ScenariosManager scenariosManager = new ScenariosManager();
    private final InteractionsManager interactionsManager = new InteractionsManager(scenariosManager);
    private final StatsManager statsManager;
    /**
     * Create a new ControllerImpl object.
     *
     * @param difficulty the difficulty of the game.
     */
    public ControllerImpl(final GameDifficulty difficulty) {
        statsManager = new StatsManagerImpl(difficulty);
    }
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

    public boolean getVisibilityTimer() {
        return minigameManager.getCurrentMinigame().isAlive();
    }

    @Override
    public void notifyKeyPressed(char keyChar) {
        minigameManager.getCurrentMinigame().notifyKeyPressed(keyChar);
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
    public int getTime() {
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


    /**
     * Retrieves the current position of the character.
     *
     * @return the current position of the character
     */
    @Override
    public Map<StatsType, Counter> getStatistics() {
        return statsManager.getAllStats();
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
