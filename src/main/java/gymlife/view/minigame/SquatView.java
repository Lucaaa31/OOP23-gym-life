package gymlife.view.minigame;

import gymlife.controller.api.Controller;

import javax.swing.JPanel;

/**
 * The SquatView class represents the view component for the Squat mini-game.
 * It extends the JPanel class and provides the necessary UI elements and
 * functionality
 * for the Squat mini-game.
 */
public class SquatView extends JPanel {
    private final transient Controller controller;
    /**
     * Constructs a new SquatView object with the specified controller.
     *
     * @param controller the controller for the mini-game
     */
    public SquatView(final Controller controller) {
        this.controller = controller;
    }

}
