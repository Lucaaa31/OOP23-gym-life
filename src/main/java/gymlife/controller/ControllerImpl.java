package gymlife.controller;

import gymlife.model.CharacterModelImpl;
import gymlife.model.InteractionsManager;
import gymlife.model.GameMapImpl;
import gymlife.model.MapManagerImpl;
import gymlife.model.minigame.MinigameManager;
import gymlife.model.minigame.ScoringTableManager;
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
import gymlife.utility.minigame.MinigameDifficulty;
import gymlife.utility.minigame.MinigameState;
import gymlife.utility.minigame.MinigameType;


import java.util.List;
import java.util.Map;

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
    private final MinigameManager minigameManager;
    private final ScoringTableManager scoringTableManager = new ScoringTableManager();

    /**
     * Constructs a new ControllerImpl object with the specified game difficulty.
     *
     * @param difficulty the difficulty of the game.
     */
    public ControllerImpl(final GameDifficulty difficulty) {
        this.statsManager = new StatsManagerImpl(difficulty);
        this.scenariosManager = new ScenariosManager();
        this.minigameManager = new MinigameManager();
        this.interactionsManager = new InteractionsManager(
                scenariosManager,
                statsManager,
                minigameManager
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
        mapManager.changeMap(newMap);
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

    /**
     * Sets the difficulty level of the current minigame,
     * and starts it.
     *
     * @param difficulty the difficulty level to set
     */
    @Override
    public void setDifficulty(final MinigameDifficulty difficulty) {
        minigameManager.setDifficulty(difficulty);
    }


    /**
     * Notifies the current minigame that a button has been pressed.
     */
    @Override
    public void notifyUserAction() {
        minigameManager.notifyUserAction();
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
     * Set the current minigame score.
     * Updates the game statistics.
     * Updates the scenarios.
     */
    public void setMinigameResult() {
        scoringTableManager.updateMinigameScore(minigameManager.getMinigameType(),
                minigameManager.getDifficulty(),
                minigameManager.getEndTime());
        statsManager.setStat(minigameManager.getMinigameType().getStatsType(), 0);
        scenariosManager.updateScenarios(ScenariosType.MINIGAME_GYM);
    }

    /**
     * Return the minigame status.
     *
     * @return true if the minigame is ended, false otherwise
     */
    @Override
    public MinigameState getMinigameState() {
        return minigameManager.getMinigameState();
    }

    /**
     * Retrieves the current minigame difficulty.
     *
     * @return the current minigame difficulty
     */
    public MinigameDifficulty getDifficulty() {
        return minigameManager.getDifficulty();
    }

    /**
     * Checks if a rep has been completed.
     *
     * @return true if the rep is done, false otherwise
     */
    @Override
    public boolean isRepDone() {
        return minigameManager.isRepsDone();
    }

    /**
     * Retrieves the current minigame score.
     *
     * @param minigameType the type of the minigame
     * @param difficulty   the difficulty of the minigame
     * @return the current minigame score
     */
    @Override
    public List<Integer> getScores(final MinigameType minigameType, final MinigameDifficulty difficulty) {
        return scoringTableManager.getScores(minigameType, difficulty);
    }

    @Override
    public boolean checkValidity() {
        return minigameManager.getValidity();
    }




}
