package gymlife.model.inventory;

import java.util.HashMap;
import java.util.Map;
import gymlife.model.statistics.StatsType;

public enum FoodType {
    HAMBURGER(14.99, 5, 10, -10), 
    BROCCOLI(4.99, 10, -10, 5),
    MEAT(9.99, 5, 5, -5);

    private double cost;
    private Map<StatsType, Integer> foodPerks;

    private FoodType(final double cost, final int staminaValue, final int happinessValue, final int massValue) {
        this.cost = cost;
        this.foodPerks = new HashMap<>();
        foodPerks.put(StatsType.STAMINA, staminaValue);
        foodPerks.put(StatsType.HUMOR, happinessValue);
        foodPerks.put(StatsType.MASS, massValue);
    }

    public Map<StatsType, Integer> returnFoodPerks() {
        return Map.copyOf(foodPerks);
    }

}
