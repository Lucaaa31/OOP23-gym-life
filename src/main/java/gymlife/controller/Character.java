package gymlife.controller;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import gymlife.utility.Constants;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * 
 * Class responsible for managing Character movements .
 */

public class Character extends JFrame {

    private final JLabel label;
    public static final long serialVersionUID = 4328743;
    private final transient KeyListener characterListener = new KeyListener() {
        @Override
        public void keyTyped(final KeyEvent e) {
            switch (e.getKeyChar()) {
                case 'a':
                label.setLocation(label.getX() - Constants.MOVEMENT, label.getY());
                break;
                case 'd':
                label.setLocation(label.getX() + Constants.MOVEMENT, label.getY());
                break;
                case 'w':
                label.setLocation(label.getX(), label.getY() - Constants.MOVEMENT);
                break;
                case 's':
                label.setLocation(label.getX(), label.getY() + Constants.MOVEMENT);
                break;
                default:
                break;
            }
        }
        @Override
        public void keyPressed(final KeyEvent e) {
        }
        @Override
        public void keyReleased(final KeyEvent e) {
        }
    };
    /**
     * 
     * No argument passed.
     */
    public Character() {
        //Define serialization id to avoid serialization related bugs
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(Constants.HEIGHT, Constants.WIDTH);
        this.setLayout(null);
   /*      characterListener a = characterListener;*/
        this.addKeyListener(characterListener); 
        label = new JLabel();
        label.setBounds(100, 100, Constants.CHARACTER_WIDTH, Constants.CHARACTER_HEIGHT);
        label.setBackground(Color.PINK);
        label.setOpaque(true);
        this.add(label);
        this.setVisible(true);
    }
}
