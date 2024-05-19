package gymlife.test;

import gymlife.controller.ControllerImpl;
import gymlife.model.PlaneGameModel;
import gymlife.model.statistics.api.SynchronizerModel;
import gymlife.view.bankgame.BankGameView;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.ModuleLayer.Controller;

/**
 * This test check if the value of treshold and multiplierShort.
 */
class TestMultiplier {

    @Test
    void equal() throws InterruptedException {
        final ControllerImpl controller = new ControllerImpl();
        final PlaneGameModel number = new PlaneGameModel(controller.getSync1(), controller.getSync2());
        final BankGameView view = new BankGameView(controller);
        final float money = 20;
        
            Thread modelThread = new Thread(() -> {
            number.runMultiplier(money);
            });

            modelThread.start();


            controller.getSync2().signal();
            view.showsMulti(controller);

            modelThread.join();
            //Thread.sleep(300);

            System.out.println(number.getMultiplierShort());
            System.out.println(number.getTreshold());

        assertEquals(number.getTreshold(), number.getMultiplierShort());
    }
}
