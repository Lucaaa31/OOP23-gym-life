package gymlife.application;


import gymlife.view.MainView;

/**
 * The GameLauncher class launches the GymLife application.
 */
public final class GameLauncher {

    /**
     * Constructs a GameLauncher object.
     */
    private GameLauncher() {
    }

    /**
     * The main method launches the GymLife application.
     *
     * @param args the command line arguments
     */
    public static void main(final String[] args) {
        new MainView().start();
    }

}
