package gymlife.model.inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import gymlife.model.statistics.StatsType;

public class Inventory {
    private final Map<FoodType, Integer> foodInventory;
    private boolean inventoryAccessable;

    public Inventory() {
        this.inventoryAccessable = false;
        this.foodInventory = new HashMap<>();
        for (FoodType food : FoodType.values()) {
            foodInventory.put(food, 0);
        }
    }

    public void addFood(FoodType food) {
        foodInventory.put(food, foodInventory.get(food) + 1);
    }

    public Optional<Map<StatsType, Integer>> consume(FoodType food) {
        if (inventoryAccessable) {
            int currentQuantity = foodInventory.get(food);
            if (currentQuantity > 0) {
                foodInventory.put(food, foodInventory.get(food) - 1);
            }
            inventoryAccessable = false;
            return Optional.of(food.returnFoodPerks());
        }
        return Optional.empty();
    }
    public void enableInventory() {
        this.inventoryAccessable = true;
    }

}
