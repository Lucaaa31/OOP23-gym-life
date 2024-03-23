package gymlife.application;

import gymlife.view.CharacterView;

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
        CharacterView characterView = new CharacterView();
        characterView.start();
    }
}
