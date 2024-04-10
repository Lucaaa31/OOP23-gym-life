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
    public CharacterView(Controller controller) {
        // Define serialization id to avoid serialization related bugs
        this.controller = controller;
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(final KeyEvent e) {
                System.out.println((e.getKeyChar()));
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
    
    /**
     * Update the view .
     * @param newPos
     */
    public static void update(final Position newPos) {
        LABEL.setLocation(newPos.X(), newPos.Y());
    }
}
