package gymlife.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import gymlife.utility.GameDifficulty;

class TestDays {
    @Test
    void testNewDay() {
        final DaysModelImpl days = new DaysModelImpl(5);
        days.newDay();
        assertEquals(4, days.dayLeft());
    }

    @Test
    void testIsDayOver() {
        final DaysModelImpl days = new DaysModelImpl(1);
        days.newDay();
        assertTrue(days.isDayOver());
    }

    @Test
    void testInitFromDifficulty() {
        DaysModelImpl days = new DaysModelImpl(GameDifficulty.EASY.getDays());
        assertEquals(GameDifficulty.EASY.getDays(), days.getCount());
        days = new DaysModelImpl(GameDifficulty.MEDIUM.getDays());
        assertEquals(GameDifficulty.MEDIUM.getDays(), days.getCount());
        days = new DaysModelImpl(GameDifficulty.HARD.getDays());
        assertEquals(GameDifficulty.HARD.getDays(), days.getCount());
        days = new DaysModelImpl(1);
        assertEquals(1, days.getCount());
        assertFalse(days.isDayOver());
        days.newDay();
        assertEquals(0, days.getCount());
        assertTrue(days.isDayOver());
        days.newDay();
        assertEquals(0, days.getCount());
        assertTrue(days.isDayOver());
    }
}
