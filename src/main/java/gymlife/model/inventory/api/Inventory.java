package gymlife.model.inventory.api;

import gymlife.model.inventory.FoodType;
import gymlife.model.statistics.StatsType;

import javax.annotation.concurrent.Immutable;
import java.util.Map;
import java.util.Optional;

/**
 * Interface for how to create the Inventory.
 */
@Immutable
public interface Inventory {
    /**
     * Method to get a map of foods and their counts.
     * @return map of foods and int.
     */
    Map<FoodType, Integer> getFoodCount();

    /**
     * Method to add a food item to the map.
     * @param food FoodType to add.
     */
    void addFood(FoodType food);

    /**
     * Method to consume a food and also returns the nutritional values of said food.
     * @param food the FoodType to consume.
     * @return An optional of the map of nutritional values. Empty if said food is not present in inventory.
     */
    Optional<Map<StatsType, Integer>> consume(FoodType food);

    /**
     * Method to enable consumption.
     */
    void enableInventory();

    /**
     * Method to return the food that is to be bought.
     * @return FoodType to be bought.
     */
    FoodType getCurrentFoodToBuy();

    /**
     * Method that changes the food to buy.
     * @param food New food to be bought.
     */
    void changeFoodToBuy(FoodType food);
}
