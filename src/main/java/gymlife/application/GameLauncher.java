package gymlife.application;

import gymlife.view.MainView;

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
        final MainView characterView = new MainView();
        characterView.start();
    }
}
