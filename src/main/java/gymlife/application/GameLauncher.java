package gymlife.application;

import gymlife.view.MainView;

import gymlife.controller.ControllerImpl;
import gymlife.controller.api.Controller;
import gymlife.view.GameMapView;

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
        final MainView mainView = new MainView();
        mainView.start();
        final Controller con = new ControllerImpl();
        new GameMapView(con);
    }
}
