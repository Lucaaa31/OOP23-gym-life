package gymlife.model;

import gymlife.model.statistics.MoneyModelImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import gymlife.model.statistics.api.MoneyModel;


class TestMoney {
    @Test
    void testInitiation() {
        final MoneyModel money = new MoneyModelImpl();
        assertEquals(0, money.getMoney());
    }
    @Test
    void testIncrement() {
        final MoneyModel money = new MoneyModelImpl();
        money.setMoney(TestConstants.TEST_MULTI_INCREMENT_POSITIVE_5);
        assertEquals(TestConstants.TEST_MULTI_INCREMENT_POSITIVE_5, money.getMoney());
        money.incrementMoney();
        assertEquals(TestConstants.TEST_MULTI_INCREMENT_POSITIVE_5 + 1, money.getMoney());
    }
    @Test
    void testDecrement() {
        final MoneyModel money = new MoneyModelImpl();
        money.setMoney(TestConstants.TEST_MULTI_INCREMENT_POSITIVE_5);
        assertEquals(TestConstants.TEST_MULTI_INCREMENT_POSITIVE_5, money.getMoney());
        money.decrementMoney();
        assertEquals(TestConstants.TEST_MULTI_INCREMENT_POSITIVE_5 - 1, money.getMoney());
    }
    @Test
    void testMultiIncrement() {
        final MoneyModel money = new MoneyModelImpl();
        money.setMoney(TestConstants.TEST_MULTI_INCREMENT_POSITIVE_5);
        assertEquals(TestConstants.TEST_MULTI_INCREMENT_POSITIVE_5, money.getMoney());
        money.multiIncrementMoney(TestConstants.TEST_MULTI_INCREMENT_NEGATIVE_3);
        assertEquals(2, money.getMoney());
        money.multiIncrementMoney(TestConstants.TEST_MULTI_INCREMENT_NEGATIVE_3);
        assertEquals(0, money.getMoney());
    }
}
