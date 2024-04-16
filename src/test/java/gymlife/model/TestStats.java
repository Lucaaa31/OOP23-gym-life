package gymlife.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import gymlife.model.StatsModelImpl;
import gymlife.model.api.StatsModel;

public class TestStats {
    @Test
    void testInitiation() {
        final StatsModel stats = new StatsModelImpl();
        int initialMass = stats.getMass();
        assertEquals(initialMass, 0);
    }

    @Test
    void testMove() {
        

    }
}
