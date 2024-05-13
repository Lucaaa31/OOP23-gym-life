package gymlife.view;

import gymlife.controller.api.Controller;
import gymlife.utility.Constants;
import gymlife.utility.Directions;
import gymlife.utility.MapConstants;
import gymlife.utility.Position;


import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import static java.lang.Thread.sleep;

/**
 * JPanel that shows the current map on which teh character is. it shows all the cells of the map loaded.
 */
public final class GameMapView extends JPanel {
    @Serial
    private static final long serialVersionUID = -3544425405075144844L;
    private final transient Controller controller;
    private transient Map<Position, JLabel> cells = new HashMap<>();
    private final CharacterView characterLabel;
    private static final int SIZE = Constants.SCENARIO_WIDTH / MapConstants.MAP_X_DIM;
    private final DimensionGetter dimensionGetter;
    private  JLayeredPane mainPanel;
    private  JPanel mapPanel;
    /**
     * Constructor for the GameMapView. it requires an external controller, given by the MainView.
     *
     * @param controller Controller given.
     */
    public GameMapView(final Controller controller, final DimensionGetter dimensionGetter) {
        this.controller = controller;
        this.dimensionGetter = dimensionGetter;
        this.setSize(new Dimension(Constants.SCENARIO_WIDTH, Constants.HEIGHT));
        this.setLayout(new BorderLayout());

        mainPanel = new JLayeredPane();
        mapPanel = new JPanel(new GridLayout(MapConstants.MAP_Y_DIM, MapConstants.MAP_X_DIM));
        mapPanel.setSize(dimensionGetter.getScenarioDimension());
        mainPanel.setSize(dimensionGetter.getScenarioDimension());
        mainPanel.setPreferredSize(dimensionGetter.getScenarioDimension());
        mapPanel.setPreferredSize(dimensionGetter.getScenarioDimension());

        mainPanel.add(mapPanel);
        mainPanel.setLayer(mapPanel, JLayeredPane.DEFAULT_LAYER);

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

        characterLabel = new CharacterView(dimensionGetter);
        characterLabel.setLocation(controller.getCharacterPos().X() * dimensionGetter.getCellDimension().width,
                controller.getCharacterPos().Y() * dimensionGetter.getCellDimension().height);
        mainPanel.add(characterLabel);
        mainPanel.setLayer(characterLabel, JLayeredPane.PALETTE_LAYER);

        setFocusable(true);
        requestFocusInWindow();
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(final KeyEvent e) {
                if (Directions.getDir(e.getKeyChar()).isPresent()) {
                    controller.moveCharacter(Directions.getDir(e.getKeyChar()).get());
                    characterLabel.changeImage(1, Directions.getDir(e.getKeyChar()).get());
                    moveCharacter();
                    System.out.println("Character moved");
                }
            }
            @Override
            public void keyPressed(final KeyEvent e) {
            }
            @Override
            public void keyReleased(final KeyEvent e) {
            }
        });

        this.add(mainPanel, BorderLayout.CENTER);
        this.setFocusable(true);
        this.setBackground(Color.BLACK);
        this.setBackground(Color.BLUE);
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
                cells.get(controller.getCharacterPos()).getX() ,
                cells.get(controller.getCharacterPos()).getY());

//        final Point locationMid = new Point( (int) (( characterLabel.getX() + locationToGo.getX()) / 2) ,  (int) ((characterLabel.getY() + locationToGo.getY()) / 2));

//        characterLabel.setLocation(locationMid);
//
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        characterLabel.setLocation(locationToGo);

    }


    public void resizeComponents() {
        this.remove(mainPanel);
        cells.clear();
        mainPanel = new JLayeredPane();
        mapPanel = new JPanel(new GridLayout(MapConstants.MAP_Y_DIM, MapConstants.MAP_X_DIM));

        mapPanel.setSize(dimensionGetter.getScenarioDimension());
        mainPanel.setPreferredSize(dimensionGetter.getScenarioDimension());
        mapPanel.setPreferredSize(dimensionGetter.getScenarioDimension());

        mainPanel.add(mapPanel);
        mainPanel.setLayer(mapPanel, JLayeredPane.DEFAULT_LAYER);
        mapPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        // Calcola le nuove dimensioni proporzionali per i pannelli
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

        characterLabel.resizeImage();
        System.out.println("Character resized" + characterLabel.getSize().width + " " + characterLabel.getSize().height);
        characterLabel.setLocation(controller.getCharacterPos().X() * dimensionGetter.getCellDimension().width,
                controller.getCharacterPos().Y() * dimensionGetter.getCellDimension().height);
        mainPanel.add(characterLabel);
        mainPanel.setLayer(characterLabel, JLayeredPane.PALETTE_LAYER);
        this.setSize(dimensionGetter.getScenarioDimension());
        this.add(mainPanel, BorderLayout.CENTER);


        mainPanel.setPreferredSize(dimensionGetter.getScenarioDimension());
        mapPanel.setPreferredSize(dimensionGetter.getScenarioDimension());

        System.out.println("Dimensiongetter.cell" + dimensionGetter.getCellDimension().width + " " + dimensionGetter.getCellDimension().height);
        System.out.println("CELL DIMENSION " + cells.get(new Position(2,1)).getSize().width + " "
                + cells.get(new Position(1,1)).getSize().height + "\n");
        System.out.println("Cell Position" + cells.get(new Position(2,1)).getX()
                + " " + cells.get(new Position(1,1)).getY());
        System.out.println("MAP view " + this.getSize().width + " " + this.getSize().height);
        System.out.println("MAIN MAP Panel " + mainPanel.getSize().width + " " + mainPanel.getSize().height);
        System.out.println("MAP Panel " + mapPanel.getSize().width + " " + mapPanel.getSize().height);
        System.out.println("Frame " + dimensionGetter.getFrameDimension() + "\n");
;

        // Aggiorna il layout
        mapPanel.revalidate();
        mainPanel.revalidate();
        this.revalidate();
        this.repaint();
    }
}

