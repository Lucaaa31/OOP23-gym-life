package gymlife.application;

import gymlife.controller.ControllerImpl;
import gymlife.controller.api.Controller;
import gymlife.view.FastTravelView;

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
        final Controller con = new ControllerImpl();
        new FastTravelView(con);
    }
}
