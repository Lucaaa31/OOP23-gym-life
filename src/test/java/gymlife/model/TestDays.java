package gymlife.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import gymlife.utility.GameDifficulty;

public class TestDays {
    @Test
    void testNewDay() {
        DaysModelImpl days = new DaysModelImpl(5);
        days.newDay();
        assertEquals(4, days.dayLeft());
    }

    @Test
    void testIsDayOver() {
        DaysModelImpl days = new DaysModelImpl(1);
        days.newDay();
        assertEquals(true, days.isDayOver());
    }

    @Test
    void testInitFromDifficulty() {
        DaysModelImpl days = new DaysModelImpl(GameDifficulty.EASY.getDays());
        assertEquals(40, days.getCount());
        days = new DaysModelImpl(GameDifficulty.MEDIUM.getDays());
        assertEquals(30, days.getCount());
        days = new DaysModelImpl(GameDifficulty.HARD.getDays());
        assertEquals(20, days.getCount());
        days = new DaysModelImpl(1);
        assertEquals(1, days.getCount());
        assertEquals(false, days.isDayOver());
        days.newDay();
        assertEquals(0, days.getCount());
        assertEquals(true, days.isDayOver());
        days.newDay();
        assertEquals(0, days.getCount());
        assertEquals(true, days.isDayOver());
    }

}
