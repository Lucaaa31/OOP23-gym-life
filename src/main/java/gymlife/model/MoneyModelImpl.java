/**
 * The MoneyModel class represents a model for tracking money in a gym.
 * It extends the AbstractCounter class, which provides common functionality for counters.
 */
package gymlife.model;

import gymlife.model.api.Counter;
import gymlife.model.api.MoneyModel;
/**
* Class that implements MoneyModel.
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
}
