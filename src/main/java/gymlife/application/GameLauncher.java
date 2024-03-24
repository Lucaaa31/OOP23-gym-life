package gymlife.application;

import gymlife.view.CharacterViewImpl;
import gymlife.view.api.CharacterView;
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
        CharacterView characterView = new CharacterViewImpl();
        characterView.start();
    }
}
