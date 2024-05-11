package gymlife.view.minigame;

import gymlife.controller.api.Controller;

import javax.swing.BoxLayout;

import javax.swing.JPanel;

/**
 * The MinigameViewImpl class represents the view component for the minigame
 * feature in the GymLife application.
 * It extends the JPanel class and provides a graphical user interface for the
 * minigame.
 */
public class MinigameViewImpl extends JPanel {
    private JPanel minigamePanel;
    private MinigameDifficultyView difficultyView;
    // private TimerView timerView;

    /**
     * Constructs a MinigameViewImpl object with the specified controller.
     * 
     * @param controller the controller object that handles the minigame logic
     */
    public MinigameViewImpl(Controller controller) {
        // timerView = new TimerView(controller);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        difficultyView = new MinigameDifficultyView(controller);
        this.add(difficultyView);
        try {
            minigamePanel = (JPanel) Class.forName(controller.getMinigameType().getViewName())
                    .getDeclaredConstructor(controller.getClass())
                    .newInstance(controller);
        } catch (Exception e) {

        }
        this.add(minigamePanel);
        // this.add(timerView, BoxLayout.X_AXIS);
    }
}
