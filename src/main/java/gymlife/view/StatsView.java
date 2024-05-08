package gymlife.view;

import gymlife.controller.ControllerImpl;
import gymlife.controller.api.Controller;
import gymlife.utility.Constants;
import gymlife.utility.ScenariosType;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * The StatsView class represents a JPanel that displays statistics related to
 * the gym.
 * It is a part of the GUI of the Java application.
 */
public class StatsView extends JFrame {
    public static final long serialVersionUID = 4328743;
    private final transient Controller controller = new ControllerImpl();
    private final CharacterView charView = new CharacterView(controller);
    private final GameMapView gameMapView = new GameMapView(controller);
    private final Map<ScenariosType, JPanel> scenariosMap = new HashMap<>();

    /**
     * Starts the main view of the application.
     * Sets the size, layout, and default close operation of the frame.
     * Sets the size, layout, and visibility of the character view panel.
     * Adds the character view panel to the main frame and makes it visible.
     */
    public StatsView() {
        this.setSize(Constants.SCENARIO_WIDTH, Constants.HEIGHT);
        this.setLayout(new CardLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        charView.setSize(Constants.SCENARIO_WIDTH, Constants.HEIGHT);
        charView.setLayout(null);
        charView.setVisible(true);
        charView.setFocusable(true);
        charView.requestFocusInWindow();

        this.add((JPanel) charView);
        charView.setVisible(true);
        this.setVisible(true);
    }
}
