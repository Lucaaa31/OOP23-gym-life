package gymlife.view;

import java.awt.Color;
import gymlife.utility.Constants;
import javax.swing.JFrame;
import javax.swing.JLabel;
import gymlife.controller.CharacterController;
import gymlife.model.CharacterModel;
import gymlife.utility.Position;

public class CharacterView extends JFrame {
    private final static JLabel label = new JLabel();
    public static final long serialVersionUID = 4328743;
    public CharacterModel charMod;
    public CharacterController charContr;

    /**
     * 
     * No argument passed.
     */
    public void start() {
        charMod = new CharacterModel();
        charContr = new CharacterController(charMod);

        // Define serialization id to avoid serialization related bugs
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(Constants.PANEL_WIDTH, Constants.PANEL_HEIGHT);

        this.setLayout(null);
        this.addKeyListener(charContr.getChaListener());
        label.setBounds(Constants.CHARACTER_START_POS.X(), Constants.CHARACTER_START_POS.Y(), Constants.CHARACTER_WIDTH,
                Constants.CHARACTER_HEIGHT);
        label.setBackground(Color.RED);
        label.setOpaque(true);
        this.add(label);
        this.setVisible(true);
    }

    public static void update(Position newPos) {
        label.setLocation(newPos.X(), newPos.Y());
    }
}
