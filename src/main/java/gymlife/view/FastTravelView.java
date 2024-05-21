package gymlife.view;

import gymlife.controller.api.Controller;
import gymlife.model.GameMapImpl;
import gymlife.utility.FontLoader;
import gymlife.utility.MapConstants;
import gymlife.utility.ScenariosType;
import gymlife.view.api.GamePanel;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

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
    private final List<JButton> buttonsList;

    /**
     * Constructor for the Interface.
     *
     * @param controller takes the unique controller given by the MainView.
     * @param dimensionGetter the manager of the GamePanel's dimensions.
     */
    public FastTravelView(final Controller controller, final DimensionGetter dimensionGetter) {
        this.controller = controller;
        this.dimensionGetter = dimensionGetter;
        this.setSize(dimensionGetter.getFastTravelDimension());

        this.setLayout(new BorderLayout());
        this.setBackground(MapConstants.FAST_TRAVEL_MAP_BG_COLOR);


        final JPanel mapPanel = new JPanel(new BorderLayout());
        mapPanel.setBackground(MapConstants.FAST_TRAVEL_MAP_BG_COLOR);

        buttonsPanel = new JPanel(new GridLayout(1, 3));
        buttonsPanel.setPreferredSize(dimensionGetter.getFastTravelButtonsDimension());

        buttonsList = new ArrayList<>(Set.of(
                new JButton("Gym"),
                new JButton("House"),
                new JButton("Shop")));

        FontLoader.loadFont();
        buttonsList.forEach(button -> button.setFont(FontLoader.getCustomFont(dimensionGetter.getBigFontSize())));

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

        final ActionListener al = e -> {
            final String loc = ((JButton) e.getSource()).getText();
            controller.goToNewMap(GameMapImpl.fromString(loc));
            controller.changeScenario(ScenariosType.INDOOR_MAP);
            controller.resetPlayerPosition();
            changeLocation();
            disableFocus();
        };

        buttonsList.forEach(button -> {
            button.addMouseListener(ml);
            button.addActionListener(al);
            button.setOpaque(true);
            button.setBackground(MapConstants.BUTTONS_COLOR);
            button.setBorderPainted(false);
            button.setForeground(Color.WHITE);
            buttonsPanel.add(button);
        });

        this.add(mapPanel, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.SOUTH);
        this.mapLabel = new JLabel();
        changeLocation();
        mapPanel.add(mapLabel);
        this.setVisible(true);
    }

    private void showWay(final String whereToGo) {
        final String fileName = getMap() + "_to_" + whereToGo + ".png";
        final ImageIcon img = loadResizedImage(MapConstants.FAST_TRAVEL_FILES_PATH + fileName);
        mapLabel.setIcon(img);
    }

    private void changeLocation() {
        final String fileName = "current_" + getMap() + ".png";
        final ImageIcon img = loadResizedImage(MapConstants.FAST_TRAVEL_FILES_PATH + fileName);
        mapLabel.setIcon(img);
    }


    private String getMap() {
        return controller.getCurrentMap().getName();
    }

    @Override
    public void resizeComponents() {
        final String path = MapConstants.FAST_TRAVEL_FILES_PATH + "current_" + getMap() + ".png";
        mapLabel.setIcon(loadResizedImage(path));
        buttonsPanel.setPreferredSize(dimensionGetter.getFastTravelButtonsDimension());
        buttonsList.forEach(button -> button.setFont(FontLoader.getCustomFont(dimensionGetter.getBigFontSize())));


    }

    private ImageIcon loadResizedImage(final String path) {
        final Image imgToResize = new ImageIcon(ClassLoader.getSystemResource(path)).getImage();
        return new ImageIcon(imgToResize.getScaledInstance(
                dimensionGetter.getFastTravelDimension().width,
                dimensionGetter.getFastTravelDimension().height,
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
