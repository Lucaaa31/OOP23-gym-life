package gymlife.controller;

import gymlife.model.CharacterModelImpl;
import gymlife.model.InteractionsManager;
import gymlife.model.GameMapImpl;
import gymlife.model.MapManagerImpl;
import gymlife.model.PlaneGameModel;
import gymlife.model.statistics.StatsManagerImpl;
import gymlife.model.ScenariosManager;
import gymlife.model.api.GameMap;
import gymlife.model.api.MapManager;
import gymlife.model.SynchronizerModel;
import gymlife.model.statistics.Counter;

import gymlife.model.statistics.StatsType;
import gymlife.model.statistics.api.StatsManager;
import gymlife.utility.Directions;
import gymlife.utility.GameDifficulty;
import gymlife.utility.Position;
import gymlife.controller.api.Controller;
import gymlife.model.api.CharacterModel;
import gymlife.utility.ScenariosType;


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
    private final SynchronizerModel sync1 = new SynchronizerModel();
    private final SynchronizerModel sync2 = new SynchronizerModel();
    private final PlaneGameModel model = new PlaneGameModel(sync1, sync2);

    /**
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
     * Gets the first synchronization object used to coordinate threads.
     *
     * @return the first SynchronizerModel instance used for thread synchronization.
     */
    @Override
    public SynchronizerModel getSync1() {
        return sync1;
    }

    /**
     * Gets the second synchronization object used to coordinate threads.
     *
     * @return the second SynchronizerModel instance used for thread
     *         synchronization.
     */
    @Override
    public SynchronizerModel getSync2() {
        return sync2;
    }

    /**
     * This method starts the multiplier thread, continuously updates the view with
     * the current multiplier value,
     * and waits for the thread to finish.
     *
     * @param money the money value with which to start the multiplier.
     */
    @Override
    public void startMultiplier(final float money) {
        model.runMultiplier(money);
    }
    /**
     * Returns the current value of the multiplier.
     *
     * @return The current value of the multiplier.
     */
    @Override
    public float getMultiplier() {
        return model.getMultiplierShort();
    }

    /**
     * Returns the threshold of the multiplier.
     *
     * @return The value of the multiplier's threshold.
     */
    @Override
    public float getThreshold() {
        return model.getThreshold();
    }

    /**
     * Stops the multiplier controlled by the controller.
     */
    @Override
    public void controllerStopMultiplier() {
        model.stopMultiplier();
    }

    /**
     * Returns the value of the money.
     *
     * @return The current value of the money.
     */
    @Override
    public float controllerGetMoney() {
        return model.getMoneyMultiplied();
    }

    /**
     * Returns the value of the new threshold.
     *
     * @return The new threshold.
     */
    @Override
    public float randomizeNewThreshold() {
        return model.randomizeNewThreshold();
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
