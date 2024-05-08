package gymlife.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import gymlife.controller.api.Controller;
import gymlife.controller.ControllerImpl;
import gymlife.utility.Constants;
import gymlife.utility.ScenariosType;

/**
 * The MainView class represents the main view of the application.
 * It extends the JFrame class and provides methods to start and display the main view.
 */
public class MainView extends JFrame {
    public static final long serialVersionUID = 4328743;
    private final transient  Controller controller = new ControllerImpl();
    private final CharacterView charView = new CharacterView(controller);
    private final GameMapView gameMapView = new GameMapView(controller);
    private final Map<ScenariosType,JPanel> scenariosMap = new HashMap<>();

    /**
     * Starts the main view of the application.
     * Sets the size, layout, and default close operation of the frame.
     * Sets the size, layout, and visibility of the character view panel.
     * Adds the character view panel to the main frame and makes it visible.
     */
    public MainView() {
        this.setSize(Constants.PANEL_WIDTH, Constants.PANEL_HEIGHT);
        this.setLayout(new CardLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        charView.setSize(Constants.PANEL_WIDTH, Constants.PANEL_HEIGHT);
        charView.setLayout(null);
        charView.setVisible(true);
        charView.setFocusable(true);
        charView.requestFocusInWindow();

        this.add((JPanel) charView);
        charView.setVisible(true);
        this.setVisible(true);
    }

    /**
     * Makes the specified panel visible and hides all other main panels.
     * @param scenarios the panel to make visible
     */
    private void makeVisible(final ScenariosType scenarios) {

    }
}
