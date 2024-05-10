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
    public static final long serialVersionUID = 4328742;
    private static final int SIZE = 140;
    /**
     * Constructs a CharacterView object with the specified controller.
     */
    public CharacterView() {
        final String p = "images/character/level1_down.png";
        final ImageIcon img = new ImageIcon(new ImageIcon(ClassLoader.
                getSystemResource(p)).
                getImage().
                getScaledInstance(SIZE, SIZE, Image.SCALE_SMOOTH));
        this.setIcon(img);
        this.setSize(SIZE, SIZE);
    }

    public void changeImage(int level, Directions dir) {
        final String p = "images/character/level" + level + "_" + dir.toString().toLowerCase() + ".png";
        final ImageIcon img = new ImageIcon(new ImageIcon(ClassLoader.
                getSystemResource(p)).
                getImage().
                getScaledInstance(SIZE, SIZE, Image.SCALE_SMOOTH));
        this.setIcon(img);
    }
}
