package gymlife.model.api;

/**
 * The MoneyModel interface represents the money held by the Character.
 */
public interface MoneyModel {
    /**
     * Returns the current amount of money.
     * 
     * @return the current amount of money
     */
    int getMoney();
    /**
     * Increments the amount of money by 1.
     */
    void incrementMoney();
    /**
     * Decrements the amount of money by 1.
     */
    void decrementMoney();
    /**
     * Sets the money's level to the specified amount.
     * 
     * @param amount the amount to set the money's level to
     */
    void setMoney(int amount);
    /**
     * Increments the money's level by the specified amount.
     * 
     * @param amount the amount to increment the money's level by
     */
    void multiIncrementMoney(int amount);
}
