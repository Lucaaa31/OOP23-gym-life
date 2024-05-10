package gymlife.view;

import java.awt.Color;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.JLabel;
import gymlife.controller.api.Controller;
import gymlife.utility.Constants;
import gymlife.utility.Directions;


/**
 * The CharacterView class represents a panel that displays a character.
 * It extends the JPanel class and implements the KeyListener interface to handle keyboard events.
 */
public class CharacterView extends JLabel {
    public static final long serialVersionUID = 4328742;
    private static final JLabel LABEL = new JLabel();
    /**
     * Constructs a CharacterView object with the specified controller.
     * 
     * @param controller the controller object used to control the character movement
     */
    public CharacterView(final Controller controller) {
        // Define serialization id to avoid serialization related bugs
        final Controller controllerGame;
        controllerGame = controller;
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(final KeyEvent e) {
                switch (Character.toUpperCase(e.getKeyChar())) {
                    case KeyEvent.VK_W:
                        controllerGame.moveCharacter(Directions.UP);
                        break;
                    case KeyEvent.VK_A:
                        controllerGame.moveCharacter(Directions.LEFT);
                        break;
                    case KeyEvent.VK_S:
                        controllerGame.moveCharacter(Directions.DOWN);
                        break;
                    case KeyEvent.VK_D:
                        controllerGame.moveCharacter(Directions.RIGHT);
                        break;
                    default:
                        break;
                }
                LABEL.setBounds(controller.getCharacterPos().X(), controller.getCharacterPos().Y(), 
                    Constants.CHARACTER_WIDTH, Constants.CHARACTER_HEIGHT);
            }
            @Override
            public void keyPressed(final KeyEvent e) {
            }
            @Override
            public void keyReleased(final KeyEvent e) {
            }
        });
        LABEL.setBounds(Constants.CHARACTER_START_POS.X(), Constants.CHARACTER_START_POS.Y(), 
            Constants.CHARACTER_WIDTH, Constants.CHARACTER_HEIGHT);
        LABEL.setBackground(Color.RED);
        LABEL.setOpaque(true);
        this.add(LABEL);
    }
}
