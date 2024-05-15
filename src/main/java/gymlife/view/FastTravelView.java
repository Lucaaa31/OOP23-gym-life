package gymlife.view;

import gymlife.controller.api.Controller;
import gymlife.model.GameMapImpl;
import gymlife.utility.MapConstants;
import gymlife.utility.ScenariosType;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serial;
import java.util.Locale;

/**
 * Interface for when the player has to move from his current location to a different map.
 */
public final class FastTravelView extends JPanel {

    @Serial
    private static final long serialVersionUID = -5960084209507548196L;
    /**
     * Constant to set percentage of vertical screen taken by map.
     */
    public static final double MAP_WEIGHT_Y = 0.75;
    /**
     * Constant to set percentage of horizontal screen taken by buttons.
     */
    public static final double BUTTONS_WEIGHT_X = 0.33;
    /**
     * Constant to set percentage of vertical screen taken by buttons.
     */
    public static final double BUTTONS_WEIGHT_Y = 0.25;
    private final transient Controller controller;
    private final JLabel mapLabel;

    /**
     * Constructor for the Interface.
     *
     * @param controller takes the unique controller given by the MainView.
     */
    public FastTravelView(final Controller controller) {
        this.controller = controller;
        final int size = 100;
        this.setSize(MapConstants.MAP_X_DIM * size, MapConstants.MAP_Y_DIM * size);

        this.setLayout(new GridBagLayout());
        this.setBackground(MapConstants.FAST_TRAVEL_MAP_BG_COLOR);

        final GridBagConstraints constraints = new GridBagConstraints();

        final JPanel mapPanel = new JPanel(new BorderLayout());
        mapPanel.setBackground(MapConstants.FAST_TRAVEL_MAP_BG_COLOR);
        final JButton gymButton = new JButton("Gym");
        final JButton houseButton = new JButton("House");
        final JButton shopButton = new JButton("Shop");

        final MouseListener ml = new MouseListener() {
            @Override
            public void mouseClicked(final MouseEvent e) {

            }

            @Override
            public void mousePressed(final MouseEvent e) {

            }

            @Override
            public void mouseReleased(final MouseEvent e) {

            }

            @Override
            public void mouseEntered(final MouseEvent e) {
                final String loc = ((JButton) e.getSource()).getText().toLowerCase(new Locale("en"));
                if (!loc.equals(getMap())) {
                    showWay(loc);
                }
            }

            @Override
            public void mouseExited(final MouseEvent e) {
                changeLocation();

            }
        };

        final ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final String loc = ((JButton) e.getSource()).getText();
                controller.goToNewMap(GameMapImpl.fromString(loc));
                controller.changeScenario(ScenariosType.INDOOR_MAP);
                changeLocation();
            }
        };

        gymButton.addMouseListener(ml);
        houseButton.addMouseListener(ml);
        shopButton.addMouseListener(ml);

        gymButton.addActionListener(al);
        houseButton.addActionListener(al);
        shopButton.addActionListener(al);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.weightx = 1;
        constraints.weighty = MAP_WEIGHT_Y;
        this.add(mapPanel, constraints);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridy = 1;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.weightx = BUTTONS_WEIGHT_X;
        constraints.weighty = BUTTONS_WEIGHT_Y;
        this.add(shopButton, constraints);
        constraints.gridx = 1;
        this.add(houseButton, constraints);
        constraints.gridx = 2;
        this.add(gymButton, constraints);
        this.mapLabel = new JLabel();
        changeLocation();
        mapPanel.add(mapLabel);
        this.setVisible(true);
    }

    private void showWay(final String whereToGo) {
        final String fileName = getMap() + "_to_" + whereToGo + ".png";
        final ImageIcon img = new ImageIcon(ClassLoader.getSystemResource(
                "images/fastTravelMap/" + fileName));
        mapLabel.setIcon(img);
    }

    private void changeLocation() {
        final String fileName = "current_" + getMap() + ".png";
        final ImageIcon img = new ImageIcon(ClassLoader.getSystemResource(
                "images/fastTravelMap/" + fileName));
        mapLabel.setIcon(img);
    }


    private String getMap() {
        return controller.getCurrentMap().getName();
    }
}
