package gymlife.controller;

import gymlife.model.character.CharacterModelImpl;
import gymlife.model.InteractionsManager;
import gymlife.model.map.GameMapImpl;
import gymlife.model.map.MapManagerImpl;
import gymlife.model.encounter.Encounter;
import gymlife.model.api.MinigameManager;
import gymlife.model.minigame.MinigameManagerImpl;
import gymlife.model.minigame.ScoringTableManager;
import gymlife.model.statistics.LimitedCounterImpl;
import gymlife.model.statistics.StatsConstants;
import gymlife.model.statistics.StatsConstants;
import gymlife.model.statistics.StatsManagerImpl;
import gymlife.model.statistics.StatsType;
import gymlife.model.statistics.CounterImpl;
import gymlife.model.ScenariosManager;
import gymlife.model.map.api.GameMap;
import gymlife.model.map.api.MapManager;

import gymlife.model.statistics.StatsType;
import gymlife.model.statistics.api.StatsManager;
import gymlife.utility.ScenariosType;
import gymlife.utility.GameDifficulty;
import gymlife.utility.Position;
import gymlife.utility.Directions;
import gymlife.controller.api.Controller;
import gymlife.model.character.api.CharacterModel;
import gymlife.utility.minigame.MinigameDifficulty;
import gymlife.utility.minigame.MinigameState;
import gymlife.utility.minigame.MinigameType;


import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    private Encounter currentEncounter;

    /**
     * Constructs a new ControllerImpl object with the specified game difficulty.
     *
     * @param difficulty the difficulty of the game.
     */
    public ControllerImpl(final GameDifficulty difficulty) {
        this.statsManager = new StatsManagerImpl(difficulty);
        statsManager.setStat(StatsType.STAMINA, StatsConstants.MAX_STATS_LEVEL);
        statsManager.setStat(StatsType.HAPPINESS, StatsConstants.MAX_STATS_LEVEL / 2);
        this.scenariosManager = new ScenariosManager();
        this.minigameManager = new MinigameManagerImpl();
        this.currentEncounter = null;
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
    public Map<StatsType, LimitedCounterImpl> getStatistics() {
        return statsManager.getStats();
    }

    /**
     * Returns the number of days that have passed in the game.
     *
     * @return the number of days
     */
    @Override
    public CounterImpl getDays() {
        return statsManager.getDays();
    }

    /**
     * Returns the money of the player.
     *
     * @return the number of days
     */
    @Override
    public CounterImpl getMoney() {
        return statsManager.getMoney();
    }

    /**
     * Changes the current game map to the specified new map.
     *
     * @param newMap the new map to switch to
     */
    @Override
    public void goToNewMap(final GameMap newMap) {
        final Optional<Encounter> encounter = mapManager.changeMap(newMap);
        if (encounter.isPresent()) {
            currentEncounter = encounter.get();
            changeScenario(ScenariosType.ENCOUNTER);
        } else {
            changeScenario(ScenariosType.INDOOR_MAP);
        }
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
        return statsManager.getStats().get(StatsType.MASS).getCount() < StatsConstants.MAX_MASS_LEVEL
                ? statsManager.getStats().get(StatsType.MASS).getCount() / div + 1 : 4;
    }

    /**
     * Retrieves the current scenario type.
     *
     * @return the current scenario.
     */
    @Override
    public ScenariosType getActualScenario() {
        return scenariosManager.getActualScenariosType();
    }

    /**
     * Method to change the scenario.
     *
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
     * Method that returns the current Encounter.
     * @return the encounter object.
     */
    @Override
    public Encounter getCurrentEncounter() {
        return currentEncounter;
    }

    /**
     * Method to either accept or decline the encounter. Changes to INDOOR scenario after.
     * @param choice boolean indicating whether to accept or decline the encounter.
     */
    @Override
    public void resolveEncounter(final boolean choice) {
        if (choice) {
            statsManager.acceptEncounter(currentEncounter);
        } else {
            statsManager.denyEncounter(currentEncounter);
        }
        changeScenario(ScenariosType.INDOOR_MAP);
    }

    /**
     * Sets the difficulty level of the current minigame,
     * and starts it.
     *
     * @param difficulty the difficulty level to set
     */
    @Override
    public void setMinigameDifficulty(final MinigameDifficulty difficulty) {
        minigameManager.setMinigameDifficulty(difficulty);
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
    @Override
    public void setMinigameResult() {
        final int winExperience = 10;
        scoringTableManager.updateMinigameScore(minigameManager.getMinigameType(),
                minigameManager.getDifficulty(),
                minigameManager.getEndTime());

        statsManager.multiIncrementStat(minigameManager.getMinigameType().getStatsType(),
                minigameManager.getMinigameState() == MinigameState.ENDED_WON
                        ? minigameManager.getDifficulty().getExperienceGained() : -winExperience);
        statsManager.multiIncrementStat(StatsType.STAMINA, StatsConstants.GYM_STAMINA_CONSUMPTION);
        scenariosManager.updateScenarios(ScenariosType.MINIGAME_GYM);
    }

    /**
     * Return the minigame status.
     *
     * @return an enum representing the minigame status
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
    @Override
    public MinigameDifficulty getDifficulty() {
        return minigameManager.getDifficulty();
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


    /**
     * Method to check if the player has won the game.
     *
     * @return Returns true if the player has won, false otherwise.
     */
    @Override
    public boolean isWin() {
        return statsManager.checkWin();
    }

    /**
     * Method to check if the game is over.
     *
     * @return true if the game is over, false otherwise
     */
    @Override
    public boolean isGameOver() {
        return statsManager.isGameOver();
    }

}
