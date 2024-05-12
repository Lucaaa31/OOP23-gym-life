package gymlife.view.minigame;

import gymlife.controller.api.Controller;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.io.Serial;
import java.lang.reflect.InvocationTargetException;


/**
 * The MinigameViewImpl class represents the view component for the minigame
 * feature in the GymLife application.
 * It extends the JPanel class and provides a graphical user interface for the
 * minigame.
 */
public class MinigameViewImpl extends JPanel {
    private JPanel minigamePanel;
    @Serial
    private static final long serialVersionUID = 7421500249399144105L;

    /**
     * Constructs a MinigameViewImpl object with the specified controller.
     *
     * @param controller the controller object that handles the minigame logic
     */
    public MinigameViewImpl(final Controller controller) {
        this.setLayout(new BorderLayout());
        MinigameDifficultyView difficultyView = new MinigameDifficultyView(controller);
        this.add(difficultyView, BorderLayout.NORTH);
        try {
            minigamePanel = (JPanel) Class.forName(controller.getMinigameType().getViewName())
                    .getDeclaredConstructor(Controller.class)
                    .newInstance(controller);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException
                 | InvocationTargetException ignored) {

        }
        this.add(minigamePanel, BorderLayout.CENTER);
        this.setVisible(true);
    }


}
