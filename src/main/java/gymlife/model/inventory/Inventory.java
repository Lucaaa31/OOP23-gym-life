package gymlife.model.inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import gymlife.model.statistics.StatsType;

/**
 * Class representig an inventory that manages food items.
 */
public class Inventory {
    private final Map<FoodType, Integer> foodInventory;
    private boolean inventoryAccessable;

    /**
     * Constructs a new Inventory object with an empty food inventory.
     */

    public Inventory() {
        this.inventoryAccessable = false;
        this.foodInventory = new HashMap<>();
        for (final FoodType food : FoodType.values()) {
            foodInventory.put(food, 0);
        }
    }

    /**
     *  Retrieves a copy of the current food inventory.
     * @return a map representing the current food inventory.
     */
    public Map<FoodType, Integer> getFoodCount() {
        return Map.copyOf(foodInventory);
    }

    /**
     * Adds a food item to the inventory.
     *
     * @param food The type of food to add to the inventory.
     */
    public void addFood(final FoodType food) {
        foodInventory.put(food, foodInventory.get(food) + 1);
    }

    /**
     * Consumes a food item from the inventory.
     *
     * @param food The type of food to consume from the inventory.
     * @return An optional containing the stat bonuses of the consumed food item if
     *         successful,
     *         or empty if the food item is not available in the inventory or the
     *         inventory is not accessible.
     */
    public Optional<Map<StatsType, Integer>> consume(final FoodType food) {
        if (inventoryAccessable) {
            final int currentQuantity = foodInventory.get(food);
            if (currentQuantity > 0) {
                foodInventory.put(food, foodInventory.get(food) - 1);
            }
            inventoryAccessable = false;
            return Optional.of(food.returnFoodPerks());
        }
        return Optional.empty();
    }

    /**
     * Enables the inventory for access.
     */
    public void enableInventory() {
        this.inventoryAccessable = true;
    }

}
