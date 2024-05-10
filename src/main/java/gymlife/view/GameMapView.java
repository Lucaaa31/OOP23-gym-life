package gymlife.view;

import gymlife.controller.api.Controller;
import gymlife.utility.Directions;
import gymlife.utility.MapConstants;
import gymlife.utility.Position;


import java.awt.Point;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

/**
 * JPanel that shows the current map on which teh character is. it shows all the cells of the map loaded.
 */
public final class GameMapView extends JFrame {

    @Serial
    private static final long serialVersionUID = -3544425405075144844L;
    private final transient Controller controller;
    private transient Map<Position, JLabel> cells = new HashMap<>();
    private final JLabel characterLabel;
    private static final int SIZE = 100;

    /**
     * Constructor for the GameMapView. it requires an external controller, given by the MainView.
     *
     * @param controller Controller given.
     */
    public GameMapView(final Controller controller) {
        this.controller = controller;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(MapConstants.MAP_X_DIM * SIZE, MapConstants.MAP_Y_DIM * SIZE);
        final JLayeredPane mainPanel = new JLayeredPane();
        final JPanel mapPanel = new JPanel(new GridLayout(MapConstants.MAP_Y_DIM, MapConstants.MAP_X_DIM));
        mapPanel.setSize(this.getSize());
        mainPanel.add(mapPanel);
        mainPanel.setLayer(mapPanel, JLayeredPane.DEFAULT_LAYER);
        final String p = "images/character/level1_down.png";
        final ImageIcon img = new ImageIcon(new ImageIcon(ClassLoader.getSystemResource(p)).getImage().getScaledInstance(
                this.getWidth() / MapConstants.MAP_X_DIM,
                this.getHeight() / MapConstants.MAP_Y_DIM,
                Image.SCALE_SMOOTH));
        this.characterLabel = new JLabel(img);
        characterLabel.setSize(SIZE, SIZE);
        mainPanel.add(characterLabel);
        mainPanel.setLayer(characterLabel, JLayeredPane.PALETTE_LAYER);


        for (int y = 0; y < MapConstants.MAP_Y_DIM; y++) {
            for (int x = 0; x < MapConstants.MAP_X_DIM; x++) {
                final Position pos = new Position(x, y);
                final String fileName = controller.getCurrentMap().getCellAtCoord(pos).getName() + ".png";
                final String path = MapConstants.CELLS_FILES_PATH + fileName;
                final JLabel lb = new JLabel();
                final Image imgToResize = new ImageIcon(ClassLoader.getSystemResource(path)).getImage();
                final ImageIcon imageIcon = new ImageIcon(imgToResize.getScaledInstance(
                        this.getWidth() / MapConstants.MAP_X_DIM,
                        this.getHeight() / MapConstants.MAP_Y_DIM,
                        Image.SCALE_SMOOTH));
                lb.setIcon(imageIcon);
                mapPanel.add(lb);
                cells.put(pos, lb);
            }
        }

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(final KeyEvent e) {
                controller.moveCharacter(Directions.getDir(e.getKeyChar()).get());
                moveCharacter();
            }

            @Override
            public void keyPressed(final KeyEvent e) {

            }

            @Override
            public void keyReleased(final KeyEvent e) {

            }
        });

        this.getContentPane().add(mainPanel);

        this.setVisible(true);


        moveCharacter();
    }

    @Serial
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        cells = new HashMap<>();
    }

    /**
     * Method to return the controller.
     *
     * @return Returns the controller.
     */
    public Controller getController() {
        return this.controller;
    }


    private void moveCharacter() {
        final Point locationToGo = new Point(
                cells.get(controller.getCharacterPos()).getX(),
                cells.get(controller.getCharacterPos()).getY());
        characterLabel.setLocation(locationToGo);
    }


}
