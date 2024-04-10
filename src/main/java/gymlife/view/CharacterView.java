package gymlife.view;

import java.awt.Color;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.JLabel;
import gymlife.controller.ControllerImpl;
import gymlife.controller.api.Controller;
import gymlife.utility.Position;
import gymlife.utility.Constants;


/**
 * Class that didplay character.
 */
public class CharacterView extends JPanel{
    private static final JLabel LABEL = new JLabel();
    final Controller controller;
 
    /**
     * No argument passed.
     */
    public CharacterView(final Controller controller) {
        // Define serialization id to avoid serialization related bugs
        this.controller = controller;
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(final KeyEvent e) {
                switch (Character.toUpperCase(e.getKeyChar())) {
                    case KeyEvent.VK_W:
                        controller.moveUp();
                        break;
                    case KeyEvent.VK_A:
                        controller.moveLeft();
                        break;
                    case KeyEvent.VK_S:
                        controller.moveDown();
                        break;
                    case KeyEvent.VK_D:
                        controller.moveRight();
                        break;
                    default:
                        break;
                }
                LABEL.setBounds(controller.getCharacterPos().X(), controller.getCharacterPos().Y(), 
                        Constants.CHARACTER_WIDTH,Constants.CHARACTER_HEIGHT);
            }
    
            @Override
            public void keyPressed(final KeyEvent e) {
            }
    
            @Override
            public void keyReleased(final KeyEvent e) {
            }
        });
        LABEL.setBounds(Constants.CHARACTER_START_POS.X(), Constants.CHARACTER_START_POS.Y(), 
                Constants.CHARACTER_WIDTH,Constants.CHARACTER_HEIGHT);
        LABEL.setBackground(Color.RED);
        LABEL.setOpaque(true);
        this.add(LABEL);
    }
}
