package gymlife.application;

import gymlife.model.TimerImpl;
import gymlife.view.MinigameViewImpl;

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
       TimerImpl timer = new TimerImpl(10000);
 
        new MinigameViewImpl();
        
        timer.start();
    }
}
