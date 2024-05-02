package gymlife.application;

import gymlife.controller.BankGameController;
import gymlife.model.PlaneGameModel;
import gymlife.view.bankgame.BankGameView;

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
        final PlaneGameModel model = new PlaneGameModel();
        final BankGameView view = new BankGameView();
        final BankGameController controller = new BankGameController(model, view);
        controller.startMultiplier();
    }
}
