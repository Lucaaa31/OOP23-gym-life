package gymlife.application;

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
        new BankGameView();

    }
}
