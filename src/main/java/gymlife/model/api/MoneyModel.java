package gymlife.model.api;
/**
 *The MoneyModel interface represents the money holded by the Character.
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
}
