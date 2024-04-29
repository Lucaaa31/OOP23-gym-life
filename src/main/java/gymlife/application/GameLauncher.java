package gymlife.application;

import gymlife.controller.BankGameController;
import gymlife.model.PlaneGameModel;
import gymlife.view.bankgame.BankGameView;

/**
 * 
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
        PlaneGameModel model = new PlaneGameModel();

        // Crea un'istanza della vista
        BankGameView view = new BankGameView();

        // Crea un'istanza del controller e passa il modello e la vista
        BankGameController controller = new BankGameController(model, view);

        // Avvia il gioco chiamando il metodo startGame() del controller
        controller.startMultiplier();
    }
}
