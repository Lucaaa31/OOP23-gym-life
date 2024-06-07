package gymlife.model;

import gymlife.model.api.MinigameManager;
import gymlife.model.inventory.FoodType;
import gymlife.model.inventory.Inventory;
import gymlife.model.statistics.StatsType;
import gymlife.model.statistics.api.StatsManager;
import gymlife.utility.ScenariosType;
import gymlife.utility.minigame.MinigameType;

import java.util.Map;
import java.util.Optional;

/**
 * A class that contains the methods used for cell interactions.
 */
public final class InteractionsManager {

    private final ScenariosManager scenariosManager;
    private final StatsManager statsManager;
    private final MinigameManager minigameManager;
    private final Inventory inventory;

    /**
     * Constructor of the interactionsManager. All the managers of the game are given to it in order to act as a filter
     * for the interactions.
     *
     * @param scenariosManager the scenarioManager on which the interactions will occur.
     * @param statsManager     the StatsManager on which the interactions will occur.
     * @param minigameManager  the MinigameManager on which the interactions will occur.
     */
    public InteractionsManager(final ScenariosManager scenariosManager, final StatsManager statsManager,
                               final MinigameManager minigameManager, final Inventory inventory) {
        this.scenariosManager = scenariosManager;
        this.statsManager = statsManager;
        this.minigameManager = minigameManager;
        this.inventory = inventory;
    }

    /**
     * method to call if the interaction of a cell concerns changing the current scenario.
     */
    public void exitInteraction() {
        this.scenariosManager.updateScenarios(ScenariosType.MAIN_MAP);
    }

    /**
     * Method to advance the days, decreasing the Days counter.
     */
    public void daysInteraction() {
        this.scenariosManager.updateScenarios(ScenariosType.SLEEPING);
        statsManager.setStat(StatsType.STAMINA, 100);
        this.statsManager.newDay();
    }

    /**
     * Method to call if the interaction of a cell concerns changing the current minigame.
     *
     * @param minigameType the new minigame to change to.
     */
    public void minigameInteraction(final MinigameType minigameType) {
        this.scenariosManager.updateScenarios(ScenariosType.MINIGAME_GYM);
        minigameManager.setCurrentMinigame(minigameType);
    }

    /**
     * This method triggers an update to the scenario manager to change the current scenario to the bank mini-game.
     */
    public void bankInteraction() {
        scenariosManager.updateScenarios(ScenariosType.MINIGAME_BANK);
    }

    public void buyFoodInteraction(final FoodType food) {
        inventory.changeFoodToBuy(food);
        scenariosManager.updateScenarios(ScenariosType.BUY_FOOD);
    }

    public void eatFood(final FoodType food) {
        inventory.enableInventory();
        final Optional<Map<StatsType, Integer>> foodMap = inventory.consume(food);
        if (foodMap.isPresent()) {
            for (StatsType st : foodMap.get().keySet()) {
                statsManager.multiIncrementStat(st, foodMap.get().get(st));
            }
        }
    }
}
