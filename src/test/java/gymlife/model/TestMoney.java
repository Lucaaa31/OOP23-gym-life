package gymlife.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import gymlife.model.api.MoneyModel;


public class TestMoney {
    @Test
    void testInitiation() {
        MoneyModel money = new MoneyModelImpl();
        assertEquals(0, money.getMoney());
    }
    @Test
    void testIncrement() {
        MoneyModel money = new MoneyModelImpl();
        money.setMoney(10);
        assertEquals(10, money.getMoney());
        money.incrementMoney();
        assertEquals(11, money.getMoney());
    }
}
