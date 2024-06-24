package gymlife.model;

import gymlife.controller.ControllerImpl;
import gymlife.utility.FontLoader;
import gymlife.model.statistics.GameDifficulty;
import gymlife.utility.DimensionGetter;
import gymlife.view.bankgame.BankGameView;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This test check if the value of threshold and multiplierShort.
 */
class TestMultiplier {

    @Test
    void equal() throws InterruptedException {
        FontLoader.loadFont();
        final ControllerImpl controller = new ControllerImpl(GameDifficulty.EASY);
        final PlaneGameModel model = new PlaneGameModel(controller.getSync1(), controller.getSync2());
        final BankGameView view = new BankGameView(controller, new DimensionGetter());
        final float money = 20;
        final Thread modelThread = new Thread(() -> {
            model.runMultiplier(money);
        });

        modelThread.start();
        view.showsMulti(controller);
        modelThread.join();
        assertEquals(model.getThreshold(), model.getMultiplierShort());
    }
}
