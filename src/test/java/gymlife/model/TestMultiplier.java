package gymlife.model;

import gymlife.controller.ControllerImpl;
import gymlife.utility.GameDifficulty;
import gymlife.view.DimensionGetter;
import gymlife.view.bankgame.BankGameView;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This test check if the value of threshold and multiplierShort.
 */
class TestMultiplier {

    @Test
    void equal() throws InterruptedException {
        final ControllerImpl controller = new ControllerImpl(GameDifficulty.EASY);
        final PlaneGameModel number = new PlaneGameModel(controller.getSync1(), controller.getSync2());
        final BankGameView view = new BankGameView(controller, new DimensionGetter());
        final float money = 20;

        final Thread modelThread = new Thread(() -> {
            number.runMultiplier(money);
        });
        modelThread.start();
        controller.getSync2().signal();
        view.showsMulti(controller);
        modelThread.join();
        assertEquals(number.getThreshold(), number.getMultiplierShort());
    }
}
