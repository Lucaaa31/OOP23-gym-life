package gymlife.view;

import gymlife.controller.api.Controller;
import gymlife.model.GameMapImpl;
import gymlife.utility.MapConstants;
import gymlife.utility.ScenariosType;
import gymlife.view.api.GamePanel;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.*;

import java.io.Serial;
import java.util.Locale;

/**
 * Interface for when the player has to move from his current location to a different map.
 */
public final class FastTravelView extends GamePanel {

    @Serial
    private static final long serialVersionUID = -5960084209507548196L;
    private final transient Controller controller;
    private final transient DimensionGetter dimensionGetter;
    private final JLabel mapLabel;
    private final JPanel buttonsPanel;

    /**
     * Constructor for the Interface.
     *
     * @param controller takes the unique controller given by the MainView.
     */
    public FastTravelView(final Controller controller, final DimensionGetter dimensionGetter) {
        this.controller = controller;
        this.dimensionGetter = dimensionGetter;
        this.setSize(dimensionGetter.getScenarioDimension());

        this.setLayout(new BorderLayout());
        this.setBackground(MapConstants.FAST_TRAVEL_MAP_BG_COLOR);


        final JPanel mapPanel = new JPanel(new BorderLayout());
        mapPanel.setBackground(MapConstants.FAST_TRAVEL_MAP_BG_COLOR);

        buttonsPanel = new JPanel(new GridLayout(1,3));
        buttonsPanel.setPreferredSize(new Dimension(
                dimensionGetter.getScenarioDimension().width,
                dimensionGetter.getScenarioDimension().height/8));

        final JButton gymButton = new JButton("Gym");
        final JButton houseButton = new JButton("House");
        final JButton shopButton = new JButton("Shop");

        final MouseAdapter ml = new MouseAdapter() {
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
                controller.resetPlayerPosition();
                changeLocation();
                disableFocus();
            }
        };

        gymButton.addMouseListener(ml);
        houseButton.addMouseListener(ml);
        shopButton.addMouseListener(ml);

        gymButton.addActionListener(al);
        houseButton.addActionListener(al);
        shopButton.addActionListener(al);


        this.add(mapPanel, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.SOUTH);
        buttonsPanel.add(shopButton);
        buttonsPanel.add(houseButton);
        buttonsPanel.add(gymButton);
        this.mapLabel = new JLabel();
        changeLocation();
        mapPanel.add(mapLabel);
        this.setVisible(true);
    }

    private void showWay(final String whereToGo) {
        final String fileName = getMap() + "_to_" + whereToGo + ".png";
        final ImageIcon img = loadResizedImage("images/fastTravelMap/" + fileName);
        mapLabel.setIcon(img);
    }

    private void changeLocation() {
        final String fileName = "current_" + getMap() + ".png";
        final ImageIcon img = loadResizedImage("images/fastTravelMap/" + fileName);
        mapLabel.setIcon(img);
    }


    private String getMap() {
        return controller.getCurrentMap().getName();
    }

    @Override
    public void resizeComponents() {
        final String path = "images/fastTravelMap/current_" + getMap() + ".png";
        mapLabel.setIcon(loadResizedImage(path));
        buttonsPanel.setPreferredSize(new Dimension(
                dimensionGetter.getScenarioDimension().width,
                dimensionGetter.getScenarioDimension().height/8));

    }

    private ImageIcon loadResizedImage(final String path) {
        final Image imgToResize = new ImageIcon(ClassLoader.getSystemResource(path)).getImage();
        return new ImageIcon(imgToResize.getScaledInstance(
                dimensionGetter.getScenarioDimension().width,
                dimensionGetter.getScenarioDimension().height,
                Image.SCALE_SMOOTH));
    }

    @Override
    public String getPanelName() {
        return "fastTravel";
    }

    private void disableFocus() {
        this.transferFocus();
    }
}
