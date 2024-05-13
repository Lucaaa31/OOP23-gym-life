package gymlife.view;

import java.awt.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import javax.swing.*;

import gymlife.controller.api.Controller;
import gymlife.utility.Constants;
import gymlife.utility.Directions;
import gymlife.utility.MapConstants;


/**
 * The CharacterView class represents a panel that displays a character.
 * It extends the JPanel class and implements the KeyListener interface to handle keyboard events.
 */
public class CharacterView extends JLabel {
    public static final long serialVersionUID = -3544425405375144844L;
    private final DimensionGetter dimensionGetter;

    /**
     * Constructs a CharacterView object with the specified controller.
     */
    public CharacterView(DimensionGetter dimensionGetter) {
        this.dimensionGetter = dimensionGetter;
        final String p = "images/character/level1_down.png";
        final ImageIcon img = new ImageIcon(new ImageIcon(ClassLoader.
                getSystemResource(p)).
                getImage().
                getScaledInstance(dimensionGetter.getCellDimension().width,
                        dimensionGetter.getCellDimension().height,
                        Image.SCALE_SMOOTH));
        this.setIcon(img);
        this.setSize(dimensionGetter.getCellDimension());
    }

    public void changeImage(int level, Directions dir) {
        final String p = "images/character/level" + level + "_" + dir.toString().toLowerCase() + ".png";
        final ImageIcon img = new ImageIcon(new ImageIcon(ClassLoader.
                getSystemResource(p)).
                getImage().
                getScaledInstance(dimensionGetter.getCellDimension().width,
                        dimensionGetter.getCellDimension().height,
                        Image.SCALE_SMOOTH));
        System.out.println("IMAGE   " + img.getIconHeight());
        this.setIcon(img);
    }

    public void resizeImage() {
        final ImageIcon img = new ImageIcon(new ImageIcon(ClassLoader.
                getSystemResource("images/character/level1_down.png")).
                getImage().
                getScaledInstance(dimensionGetter.getCellDimension().width,
                        dimensionGetter.getCellDimension().height,
                        Image.SCALE_SMOOTH));
        this.setIcon(img);
        this.setSize(dimensionGetter.getCellDimension());
    }
}
