package gymlife.view;

import gymlife.controller.api.Controller;
import gymlife.model.GameMapImpl;
import gymlife.utility.MapConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serial;

/**
 * Interface for when the player has to move from his current location to a different map.
 */
public final class FastTravelView extends JFrame {

    @Serial
    private static final long serialVersionUID = -5960084209507548196L;
    private final transient Controller controller;
    private final int size;
    private final JLabel mapLabel;

    /**
     * Constructor for the Interface.
     * @param controller takes the unique controller given by the MainView.
     */
    public FastTravelView(final Controller controller) {
        this.controller = controller;
        this.size = 100;
        this.setSize(MapConstants.MAP_X_DIM * size, MapConstants.MAP_Y_DIM * size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        final JPanel mainPanel = new JPanel(new BorderLayout());
        final JPanel mapPanel = new JPanel(new BorderLayout());
        mapPanel.setBackground(new Color(4, 35, 0));
        final JPanel buttonsPanel = new JPanel(new BorderLayout());
        final JButton gymButton = new JButton("gym");
        final JButton houseButton = new JButton("house");
        final JButton shopButton = new JButton("shop");
        buttonsPanel.add(gymButton, BorderLayout.EAST);
        buttonsPanel.add(houseButton, BorderLayout.CENTER);
        buttonsPanel.add(shopButton, BorderLayout.WEST);

        final MouseListener ml = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                final String loc = ((JButton) e.getSource()).getText();
                if (!loc.equals(getMap())){
                    showWay(loc);
                }


            }

            @Override
            public void mouseExited(MouseEvent e) {
                changeLocation();

            }
        };

        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String loc = ((JButton) e.getSource()).getText();
                controller.goToNewMap(GameMapImpl.fromString(loc));
            }
        };

        gymButton.addMouseListener(ml);
        houseButton.addMouseListener(ml);
        shopButton.addMouseListener(ml);

        gymButton.addActionListener(al);
        houseButton.addActionListener(al);
        shopButton.addActionListener(al);


        this.getContentPane().add(mainPanel);
        mainPanel.add(mapPanel, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
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
