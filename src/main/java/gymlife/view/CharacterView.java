package gymlife.view;

import java.awt.Color;
import gymlife.utility.Constants;
import javax.swing.JFrame;
import javax.swing.JLabel;
import gymlife.controller.CharacterController;
import gymlife.model.CharacterModel;
import gymlife.utility.Position;


public class CharacterView extends JFrame {
    private final JLabel label = new JLabel();
    public static final long serialVersionUID = 4328743;
    public CharacterModel charMod;
    public CharacterController charContr;
    /**
     * 
     * No argument passed.
     */
    public void start(Position startpos){
        System.out.println(startpos);
        charMod = new CharacterModel(startpos);
        charContr = new CharacterController(charMod);
        //Define serialization id to avoid serialization related bugs
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(Constants.HEIGHT, Constants.WIDTH);
        this.setLayout(null);

        this.addKeyListener(charContr.getChaListener());
        label.setLocation(charMod.getCharacterPos().X(), charMod.getCharacterPos().Y());
        label.setBounds(100, 100, Constants.CHARACTER_WIDTH, Constants.CHARACTER_HEIGHT);
        label.setBackground(Color.PINK);
        label.setOpaque(true);
        this.add(label);
        this.setVisible(true);
    }
}

