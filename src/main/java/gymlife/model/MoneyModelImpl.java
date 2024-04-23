package gymlife.model;

import gymlife.model.api.Counter;
import gymlife.model.api.MoneyModel;

/**
 * The MoneyModelImpl class represents a model for tracking money in a gym.
 * It extends the Counter class, which provides common functionality for counters.
 * 
 * This class implements the MoneyModel interface, which defines methods for managing money in the gym.
 */
public class MoneyModelImpl extends Counter implements MoneyModel {
    /**
     * Returns the current amount of money.
     * 
     * @return the current amount of money
     */
    @Override
    public int getMoney() {
        return this.getCount();
    }
    /**
     * Increments the amount of money by 1.
     */
    @Override
    public void incrementMoney() {
        this.increment();
    }
    /**
     * Decrements the amount of money by 1.
     */
    @Override
    public void decrementMoney() {
        this.decrement();
    }
    /**
     * Sets the amount of money to the specified amount.
     * 
     * @param amount the new amount of money
     */
    @Override
    public void setMoney(final int amount) {
        this.setCount(amount);
    }
    /**
     * Increments the amount of money by the specified amount.
     * 
     * @param amount the amount to increment by
     */
    @Override
    public void multiIncrementMoney(final int amount) {
        this.multiIncrement(amount);
    }
}
