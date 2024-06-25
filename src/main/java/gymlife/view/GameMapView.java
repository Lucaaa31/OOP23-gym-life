package gymlife.view;

import gymlife.controller.api.Controller;
import gymlife.utility.DimensionGetter;
import gymlife.utility.Direction;
import gymlife.utility.MapConstants;
import gymlife.utility.Position;
import gymlife.view.api.GamePanel;
import gymlife.view.character.CharacterView;

import java.awt.Point;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import javax.swing.BorderFactory;
import javax.swing.border.Border;


/**
 * JPanel that shows the current map on which the character is. It shows all the cells of the map loaded.
 */
public final class GameMapView extends GamePanel {
    @Serial
    private static final long serialVersionUID = -3544425405075144844L;
    private final transient Controller controller;
    private transient Map<Position, JLabel> cells = new HashMap<>();
    private CharacterView characterLabel;
    private final transient DimensionGetter dimensionGetter;
    private  JLayeredPane mainPanel;
    private  JPanel mapPanel;
    private static final Border BORDER = BorderFactory.createLineBorder(Color.BLACK, 2);

    /**
     * Constructor for the GameMapView. It requires an external controller, given by the MainView.
     *
     * @param controller Controller given.
     * @param dimensionGetter DimensionGetter given.
     */
    public GameMapView(final Controller controller, final DimensionGetter dimensionGetter) {
        this.setFocusable(true);
        this.controller = controller;
        this.dimensionGetter = dimensionGetter;
        this.setSize(dimensionGetter.getScenarioDimension());
        this.setLayout(new BorderLayout());
        this.setDoubleBuffered(true);

        // Initialize mainPanel and mapPanel with the appropriate dimensions
        mainPanel = new JLayeredPane();
        mapPanel = new JPanel(new GridLayout(MapConstants.MAP_Y_DIM, MapConstants.MAP_X_DIM));
        mapPanel.setSize(dimensionGetter.getScenarioDimension());
        mainPanel.setSize(dimensionGetter.getScenarioDimension());
        mainPanel.setPreferredSize(dimensionGetter.getScenarioDimension());
        mapPanel.setPreferredSize(dimensionGetter.getScenarioDimension());

        // Add mapPanel to mainPanel
        mainPanel.add(mapPanel);
        mainPanel.setLayer(mapPanel, JLayeredPane.DEFAULT_LAYER);

        // Populate the map with cells
        this.loadMap();

        // Initialize characterLabel and set its location
        characterLabel = new CharacterView(dimensionGetter, controller.getPlayerLevel());
        characterLabel.setLocation(controller.getCharacterPos().X() * dimensionGetter.getCellDimension().width,
                controller.getCharacterPos().Y() * dimensionGetter.getCellDimension().height);
        mainPanel.add(characterLabel);
        mainPanel.setLayer(characterLabel, JLayeredPane.PALETTE_LAYER);

        // Set focusable and add key listener
        setFocusable(true);
        requestFocusInWindow();

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(final KeyEvent e) {
            }
            @Override
            public void keyPressed(final KeyEvent e) {
                final int keyCode = e.getKeyCode();
                final Direction direction = Direction.getDir(keyCode).orElse(null);
                if (direction != null) {
                    controller.moveCharacter(direction);
                    characterLabel.changeImage(controller.getPlayerLevel(), direction);
                    moveCharacter();
                }
                if (keyCode == KeyEvent.VK_E && controller.getCurrentMap()
                        .getCellAtCoord(controller.getCharacterPos())
                        .getInteraction()
                        .isPresent()) {
                    controller.cellInteraction();
                    GameMapView.super.transferFocus();
                }
            }
            @Override
            public void keyReleased(final KeyEvent e) {
            }
        });
        // Add mainPanel to this panel
        this.add(mainPanel, BorderLayout.CENTER);
        this.setFocusable(true);
        mapPanel.setBackground(Color.BLACK);
        this.setBackground(Color.BLACK);
        this.setBorder(BORDER);
    }

    // Method to serialize the object
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

    // Method to move the character to a new position
    private void moveCharacter() {
        final Point locationToGo = new Point(
                cells.get(controller.getCharacterPos()).getX(),
                cells.get(controller.getCharacterPos()).getY());
        characterLabel.setLocation(locationToGo);

    }
    /**
     * Method to resize the component of the map, it clears the cells and the mapPanel, and then
     * it resizes the cells and the characterLabel.
     */
    @Override
    public void resizeComponents() {
        cells.clear();
        characterLabel = new CharacterView(dimensionGetter, controller.getPlayerLevel());
        mapPanel = new JPanel(new GridLayout(MapConstants.MAP_Y_DIM, MapConstants.MAP_X_DIM));
        mapPanel.setSize(dimensionGetter.getScenarioDimension());
        mapPanel.setPreferredSize(dimensionGetter.getScenarioDimension());

        mapPanel.setBackground(Color.BLACK);

        // Resize each cell of the map
        this.loadMap();


        // Resize character image and set its location
        characterLabel.resizeImage();
        characterLabel.setLocation(controller.getCharacterPos().X() * dimensionGetter.getCellDimension().width,
                controller.getCharacterPos().Y() * dimensionGetter.getCellDimension().height);
        this.remove(mainPanel);
        mainPanel = new JLayeredPane();
        mainPanel.add(mapPanel);
        mainPanel.repaint();
        mainPanel.setPreferredSize(dimensionGetter.getScenarioDimension());
        mainPanel.add(characterLabel);
        mainPanel.setLayer(mapPanel, JLayeredPane.DEFAULT_LAYER);
        mainPanel.setLayer(characterLabel, JLayeredPane.PALETTE_LAYER);
        this.setSize(dimensionGetter.getScenarioDimension());
        this.add(mainPanel, BorderLayout.CENTER);

        mainPanel.setPreferredSize(dimensionGetter.getScenarioDimension());
        mapPanel.setPreferredSize(dimensionGetter.getScenarioDimension());

        this.revalidate();
        this.repaint();
    }

    @Override
    public String getPanelName() {
        return "gameMap";
    }

    /**
     * Method to load the game's current map and set its size accordingly.
     */
    public void loadMap() {
        for (int y = 0; y < MapConstants.MAP_Y_DIM; y++) {
            for (int x = 0; x < MapConstants.MAP_X_DIM; x++) {
                final Position pos = new Position(x, y);
                final String fileName = controller.getCurrentMap().getCellAtCoord(pos).getName() + ".png";
                final String path = MapConstants.CELLS_FILES_PATH + fileName;
                final JLabel lb = new JLabel();
                lb.setSize(dimensionGetter.getCellDimension());
                lb.setPreferredSize(dimensionGetter.getCellDimension());
                final Image imgToResize = new ImageIcon(ClassLoader.getSystemResource(path)).getImage();
                final ImageIcon imageIcon = new ImageIcon(imgToResize.getScaledInstance(
                        dimensionGetter.getCellDimension().width,
                        dimensionGetter.getCellDimension().height,
                        Image.SCALE_SMOOTH));
                lb.setIcon(imageIcon);
                mapPanel.add(lb);
                cells.put(pos, lb);
            }
        }
    }
}
