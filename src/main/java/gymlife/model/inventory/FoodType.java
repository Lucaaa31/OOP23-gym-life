package gymlife.model.inventory;

import java.util.HashMap;
import java.util.Map;
import gymlife.model.statistics.StatsType;

/**
 * Enum representing different types of food with associated costs and stat
 * bonuses.
 */
public enum FoodType {
    /**
     * Hamburger food type with cost, stamina bonus, happiness bonus, and mass
     * penalty.
     */
    HAMBURGER(15, 5, 10, -10),

    /**
     * Broccoli food type with cost, stamina bonus, happiness penalty, and mass
     * bonus.
     */
    BROCCOLI(5, 10, -10, 5),

    /**
     * Meat food type with cost, stamina bonus, happiness bonus, and mass penalty.
     */
    MEAT(10, 5, 5, -5);

    private final double cost;
    private final Map<StatsType, Integer> foodPerks;

    /**
     * Constructs a new FoodType with the specified cost and stat bonuses.
     *
     * @param cost           The cost of the food.
     * @param staminaValue   The bonus to stamina provided by the food.
     * @param happinessValue The bonus/penalty to happiness provided by the food.
     * @param massValue      The penalty to mass provided by the food.
     */
    FoodType(final double cost, final int staminaValue, final int happinessValue, final int massValue) {
        this.cost = cost;
        this.foodPerks = new HashMap<>();
        foodPerks.put(StatsType.STAMINA, staminaValue);
        foodPerks.put(StatsType.HAPPINESS, happinessValue);
        foodPerks.put(StatsType.MASS, massValue);
    }

    /**
     * Returns a read-only view of the stat bonuses provided by the food.
     *
     * @return A map containing the stat bonuses.
     */
    public Map<StatsType, Integer> returnFoodPerks() {
        return Map.copyOf(foodPerks);
    }

    /**
     * This method returns the cost of the food.
     *
     * @return cost.
     */
    public double getCost() {
        return cost;
    }
}
