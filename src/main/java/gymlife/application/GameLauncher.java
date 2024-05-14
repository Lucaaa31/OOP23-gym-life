package gymlife.application;

import javax.swing.JFrame;
import gymlife.controller.ControllerImpl;
import gymlife.view.bankgame.BankGameView;
import java.awt.Dimension;

/**
 * Main Class of the Java application.
 */
public final class GameLauncher {

    private GameLauncher() {
    }

    /**
     * 
     * @param args No arguments passed.
     */
    public static void main(final String[] args) {
        final ControllerImpl controller = new ControllerImpl();

        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(450, 450));
        frame.setContentPane(new BankGameView(controller));
        frame.setVisible(true);
    }
}
