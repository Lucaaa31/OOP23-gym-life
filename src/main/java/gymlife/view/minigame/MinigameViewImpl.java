package gymlife.view.minigame;

import gymlife.controller.api.Controller;
import gymlife.view.DimensionGetter;

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
    @Serial
    private static final long serialVersionUID = 7421500249399144105L;
    private final transient Controller controller;
    private final transient DimensionGetter dimensionGetter;
    final MinigameDifficultyView difficultyView;

    /**
     * Constructs a MinigameViewImpl object with the specified controller.
     *
     * @param controller the controller object that handles the minigame logic
     */
    public MinigameViewImpl(final Controller controller, final DimensionGetter dimensionGetter) {
        this.controller = controller;
        this.dimensionGetter = dimensionGetter;
        this.difficultyView = new MinigameDifficultyView(controller);
        this.setLayout(new BorderLayout());

        this.add(difficultyView);

        this.setVisible(true);
    }

    public void startMinigame(){
        this.remove(difficultyView);
        try {
            JPanel minigamePanel = (JPanel) Class.forName(controller.getMinigameType().getViewName())
                    .getDeclaredConstructor(Controller.class, DimensionGetter.class)
                    .newInstance(controller, dimensionGetter);
            this.revalidate();
            this.repaint();
            minigamePanel.setVisible(true);
            this.add(minigamePanel);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException
                 | InvocationTargetException ignored) {
        }

    }

    public void endMinigame(){
        this.removeAll();
        this.add(new MinigameEndView(controller));
        this.revalidate();
        this.repaint();
    }

}
