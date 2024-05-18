package gymlife.view.minigame;

import gymlife.controller.api.Controller;
import gymlife.view.DimensionGetter;
import gymlife.view.api.MinigamePanel;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;
import java.lang.reflect.InvocationTargetException;


/**
 * The MinigameViewImpl class represents the view component for the minigame
 * feature in the GymLife application.
 * It extends the JPanel class and provides a graphical user interface for the
 * minigame.
 */
public class MinigameView extends JPanel {
    @Serial
    private static final long serialVersionUID = 7421500249399144105L;
    private final transient Controller controller;
    private final transient DimensionGetter dimensionGetter;
    final MinigameDifficultyView difficultyView;
    private MinigamePanel minigamePanel;
    private JPanel minigameEndView;

    /**
     * Constructs a MinigameViewImpl object with the specified controller.
     *
     * @param controller the controller object that handles the minigame logic
     */
    public MinigameView(final Controller controller, final DimensionGetter dimensionGetter) {
        this.controller = controller;
        this.dimensionGetter = dimensionGetter;
        this.difficultyView = new MinigameDifficultyView(controller);
        this.setLayout(new BorderLayout());

        this.add(difficultyView);

        new Thread(this::waitForStart).start();

        this.setVisible(true);
    }

    /**
     * Starts the minigame and displays the minigame view.
     */
    public void startMinigame(){
        try {
            minigamePanel = (MinigamePanel) Class.forName(controller.getMinigameType().getViewName())
                    .getDeclaredConstructor(Controller.class, DimensionGetter.class)
                    .newInstance(controller, dimensionGetter);
            this.revalidate();
            this.repaint();
            this.add((JPanel) minigamePanel);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException
                 | InvocationTargetException ignored) {
        }
    }

    private void waitForStart(){
        while (difficultyView.isVisible()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {
            }
        }
        startMinigame();
    }

    public void resizeComponents(){
        //difficultyView.resizeComponents();
        minigamePanel.resizeComponents();
//        minigameEndView.resizeComponents();
    }


}
