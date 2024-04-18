package gymlife.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.HashSet;
import java.util.Set;

import gymlife.controller.api.Controller;
import gymlife.controller.ControllerImpl;
import gymlife.utility.Constants;

/**
 * The MainView class represents the main view of the application.
 * It extends the JFrame class and provides methods to start and display the main view.
 */
public class MainView extends JFrame {
    public static final long serialVersionUID = 4328743;
    private final transient  Controller controller = new ControllerImpl();
    private final CharacterView charView = new CharacterView(controller);
    private final Set<JPanel> mainPanels = new HashSet<>();

    /**
     * Starts the main view of the application.
     * Sets the size, layout, and default close operation of the frame.
     * Sets the size, layout, and visibility of the character view panel.
     * Adds the character view panel to the main frame and makes it visible.
     */
    public void start() {
        this.setSize(Constants.PANEL_WIDTH, Constants.PANEL_HEIGHT);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        charView.setSize(Constants.PANEL_WIDTH, Constants.PANEL_HEIGHT);
        charView.setLayout(null);
        charView.setVisible(true);

        charView.setFocusable(true);
        charView.requestFocusInWindow();

        this.add((JPanel) charView);
        this.makeVisible(charView);
        this.setVisible(true);
    }

    /**
     * Makes the specified panel visible and hides all other main panels.
     * @param panel the panel to make visible
     */
    private void makeVisible(final JPanel panel) {
        mainPanels.forEach(p -> p.setVisible(false));
        panel.setVisible(true);
    }
}
