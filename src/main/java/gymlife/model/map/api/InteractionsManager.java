package gymlife.model.map.api;

import gymlife.model.inventory.FoodType;
import gymlife.utility.minigame.MinigameType;

/**
 * An Object that manages the different interactions in the game.
 */
public interface InteractionsManager {

    /**
     * Interaction of the exit.
     */
    void exitInteraction();

    /**
     * Interaction for the bed.
     */
    void daysInteraction();

    /**
     * Interaction to start a minigame.
     * @param minigameType what minigame to start.
     */
    void minigameInteraction(MinigameType minigameType);

    /**
     * Interaction to begin the bank minigame.
     */
    void bankInteraction();

    /**
     * Interaction to buy food.
     * @param food what food to buy.
     */
    void buyFoodInteraction(FoodType food);

    /**
     * Interaction to eat a specific food.
     * @param food what food to eat.
     */
    void eatFood(FoodType food);
}
