package gymlife.view;

import gymlife.controller.api.Controller;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.io.Serial;

/**
 * Interface for when the player has to move from his current location to a different map.
 */
public final class FastTravelView extends JFrame {

    @Serial
    private static final long serialVersionUID = -5960084209507548196L;
    private final transient Controller controller;

    /**
     * Constructor for the Interface.
     * @param controller takes the unique controller given by the MainView.
     */
    public FastTravelView(final Controller controller) {
        this.controller = controller;
        final JPanel mainPanel = new JPanel(new BorderLayout());
        final JPanel mapPanel = new JPanel(new BorderLayout());
        final JPanel buttonsPanel = new JPanel(new BorderLayout());

        this.getContentPane().add(mainPanel);
        mainPanel.add(mapPanel, BorderLayout.NORTH);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
        final String fileName = "current_" + getMap() + "_map.png";
        mapPanel.add(new JLabel(new ImageIcon(ClassLoader.getSystemResource(
                "images/fastTravelMap/" + fileName))));
        this.setVisible(true);
    }

    private String getMap() {
        return controller.getCurrentMap().getName();
    }
}
