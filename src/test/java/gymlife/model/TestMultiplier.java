package gymlife.test;

import gymlife.model.PlaneGameModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This test check if the value of treshold and multiplierShort.
 */
class TestMultiplier {

    @Test
    void equal() throws InterruptedException {
        final PlaneGameModel number = new PlaneGameModel();
        number.run();

        assertEquals(number.getTreshold(), number.getMultiplierShort());
    }
}
