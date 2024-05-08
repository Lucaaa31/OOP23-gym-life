package gymlife.view;

import gymlife.controller.api.Controller;
import gymlife.utility.MapConstants;
import gymlife.utility.Position;


import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

/**
 * JPanel that shows the current map on which teh character is. it shows all the cells of the map loaded.
 */
public final class GameMapView extends JFrame {

    @Serial
    private static final long serialVersionUID = -3544425405075144844L;
    private final transient Controller controller;
    private transient Map<Position, JLayeredPane> cells = new HashMap<>();

    /**
     * Constructor for the GameMapView. it requires an external controller, given by the MainView.
     * @param controller Controller given.
     */
    public GameMapView(final Controller controller) {
        this.controller = controller;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800, 600);
        JPanel mainPanel = new JPanel();
        this.getContentPane().add(mainPanel);
        mainPanel.setBackground(new Color(50,50,50));
        JPanel mapPanel = new JPanel(new GridLayout(MapConstants.MAP_Y_DIM, MapConstants.MAP_X_DIM));
        mainPanel.add(mapPanel);


        for (int y = 0; y < MapConstants.MAP_Y_DIM; y++) {
            for (int x = 0; x < MapConstants.MAP_X_DIM; x++) {
                final Position pos = new Position(x, y);

                final String path = "images/cells/" + controller.getCurrentMap().getCellAtCoord(pos).getName() + ".png";
                JLabel lb = new JLabel();
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(ClassLoader.getSystemResource(path)).getImage().getScaledInstance(this.getWidth()/8, this.getHeight()/6, Image.SCALE_SMOOTH));
                lb.setIcon(imageIcon);
                mapPanel.add(lb);
            }
        }




        this.setVisible(true);
    }
    @Serial
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        cells = new HashMap<>();
    }

    /**
     * Method to return the controller.
     * @return Returns the controller.
     */
    public Controller getController() {
        return this.controller;
    }



    /**
     * Method to get a JLayered pane from a position.
     * @param pos position.
     * @return returns the JLayered pane.
     */
    public JLayeredPane getCellFromPosition(final Position pos) {
        return cells.get(pos);
    }

}
