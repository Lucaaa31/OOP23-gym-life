package gymlife.model;

import gymlife.controller.ControllerImpl;
import gymlife.model.bankgame.PlaneGameModelImpl;
import gymlife.model.statistics.GameDifficulty;
import gymlife.utility.DimensionGetter;
import gymlife.view.bankgame.BankGameView;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This class test the bank game model.
 */
class TestBankGame {
    private static ControllerImpl controller;
    private static PlaneGameModelImpl model;
    private static final double MAX_MULTIPLIER_VALUE = 5.90;
    private static final double MIN_MULTIPLIER_VALUE = 0.90;

    @BeforeAll
    static void init() {
        controller = new ControllerImpl(GameDifficulty.EASY);
        model = new PlaneGameModelImpl(controller.getSync1(), controller.getSync2());
    }

    @Test
    void equal() throws InterruptedException {

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

    @Test
    void testThreshold() {
        model.randomizeNewThreshold();
        assertTrue(model.getThreshold() >= MIN_MULTIPLIER_VALUE && model.getThreshold() <= MAX_MULTIPLIER_VALUE);
    }

    @Test
    void testMultiplierShort() {
        assertTrue(model.getMultiplierShort() <= MAX_MULTIPLIER_VALUE);
    }

    @Test
    void testReturnList() {
        assertNotNull(model.getList());
    }

    @Test
    void testMoneyMultiplied() {
        assertTrue(model.getMoneyMultiplied() > model.getMultiplierShort());
    }

}
