package gymlife.controller;

import java.util.Map;

import gymlife.controller.api.Controller;
import gymlife.model.CharacterModelImpl;
import gymlife.model.GameMapImpl;
import gymlife.model.InteractionsManager;
import gymlife.model.GameMapImpl;
import gymlife.model.MapManagerImpl;
import gymlife.model.statistics.StatsManagerImpl;
import gymlife.model.ScenariosManager;
import gymlife.model.minigame.MinigameManager;
import gymlife.model.minigame.ScoringTableManager;
import gymlife.model.minigame.TimerImpl;
import gymlife.model.api.CharacterModel;
import gymlife.model.api.GameMap;
import gymlife.model.api.MapManager;
import gymlife.model.statistics.Counter;
import gymlife.model.statistics.StatsManagerImpl;

import gymlife.model.statistics.StatsType;
import gymlife.model.statistics.api.StatsManager;
import gymlife.utility.Directions;
import gymlife.utility.GameDifficulty;
import gymlife.utility.ScenariosType;
import gymlife.utility.minigame.MinigameDifficulty;
import gymlife.utility.minigame.MinigameType;
import gymlife.utility.Position;
import gymlife.controller.api.Controller;
import gymlife.model.api.CharacterModel;
import gymlife.utility.ScenariosType;


/**
 * This class implements the Controller interface and is responsible for managing Character movements.
 * It handles the character's movements, interactions with the game map, and game statistics.
 */
public class ControllerImpl implements Controller {
    private final CharacterModel characterModel = new CharacterModelImpl();
    private MinigameManager minigameManager;
    private final TimerImpl timer = new TimerImpl();

    private final ScoringTableManager scoringTableManager = new ScoringTableManager();

    private final MapManager mapManager = new MapManagerImpl(GameMapImpl.HOUSE_MAP);
    private final ScenariosManager scenariosManager;
    private final StatsManager statsManager;

    private final InteractionsManager interactionsManager;

    /**
     * Create a new ControllerImpl object.
     *
     * Constructs a new ControllerImpl object with the specified game difficulty.
     *
     * @param difficulty the difficulty of the game.
     */
    public ControllerImpl(final GameDifficulty difficulty) {
        this.statsManager = new StatsManagerImpl(difficulty);
        this.scenariosManager = new ScenariosManager();
        this.interactionsManager = new InteractionsManager(
                scenariosManager,
                statsManager
        );
    }

    /**
     * Moves the character in the specified direction.
     *
     * Moves the character in the specified direction if the destination cell is within the map borders and is not collidable.
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
     * Sets the difficulty level of the current minigame,
     * and starts it.
     *
     * @param difficulty the difficulty level to set
     */
    @Override
    public void setDifficulty(final MinigameDifficulty difficulty) {
        minigameManager.setDifficulty(difficulty);
        minigameManager.startMinigame();
    }


    /**
     * Notifies the current minigame that a button has been pressed.
     */
    @Override
    public void notifyUserAction() {
        minigameManager.notifyUserAction();
    }

    /**
     * Retrieves the running time of the minigame's timer.
     *
     * @return the running time of the timer
     */
    @Override
    public int getTime() {
        return timer.getRunningTime();
    }

    /**
     * Retrieves the state of the current minigame.
     *
     * @return the state of the current minigame
     */
    @Override
    public int getState() {
        return minigameManager.getState();
    }

    /**
     * Retrieves the type of the current minigame.
     * Retrieves the current game statistics.
     *
     * @return the type of the current minigame
     */
    @Override
    public MinigameType getMinigameType() {
        return minigameManager.getMinigameType();
    }


    /**
     * Retrieves the statistics of the game.
     *
     * @return a map containing the statistics
     * @return a Map of the current game statistics
     */
    @Override
    public Map<StatsType, Counter> getStatistics() {
        return statsManager.getAllStats();
    }

    /**
     * Changes the current map to the specified new map.
     *
     * @param newMap the new map to switch to
     * Changes the current game map to the specified new map.
     *
     * @param newMap the new map to switch to
     */
    @Override
    public void goToGym(final GameMap newMap) {
    @Override
    public void goToNewMap(final GameMap newMap) {
        mapManager.changeMap(newMap);
    }

    /**
     * Retrieves the current map.
     *
     * @return the current game map
     * Retrieves the current game map.
     *
     * @return the current game map
     */
    @Override
    public GameMap getCurrentMap() {
        return mapManager.getCurrentMap();
    }

    /**
     * Executes the action relative to the cell on which the player is standing.
     * Executes the action associated with the cell on which the character is currently standing.
     */
    @Override
    public void cellInteraction() {
        mapManager.getCurrentMap()
                .getCellAtCoord(characterModel.getCharacterPos())
                .getInteraction()
                .ifPresent((e) -> e.interact(interactionsManager));
    }

    @Override
    public void setMinigameManager(MinigameManager minigameManager) {
        this.minigameManager = minigameManager;
    }

    public void setMinigameResult() {
        scoringTableManager.updateMinigameScore(minigameManager.getMinigameType(),
                minigameManager.getDifficulty(),
                minigameManager.getMinigameResult());
        statsManager.setStat(minigameManager.getMinigameType().getStatsType(), minigameManager.getMinigameResult());
        scenariosManager.updateScenarios(ScenariosType.MINIGAME_GYM);
    }

    @Override
    public boolean isMinigameEnded() {
        return minigameManager.isMinigameEnded();
    }

    public MinigameDifficulty getDifficulty() {
        return minigameManager.getDifficulty();
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
     *
     * @return the current scenario type
     */
    @Override
    public ScenariosType getActualScenario() {
        return scenariosManager.getActualScenariosType();
    }
}
