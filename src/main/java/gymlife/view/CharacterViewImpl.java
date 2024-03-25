package gymlife.view;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import gymlife.controller.CharacterControllerImpl;
import gymlife.controller.api.CharacterController;
import gymlife.model.CharacterModelImpl;
import gymlife.model.api.CharacterModel;
import gymlife.utility.Position;
import gymlife.utility.Constants;
import gymlife.view.api.CharacterView;

/**
 * Class that didplay character.
 */
public class CharacterViewImpl extends JFrame implements CharacterView {
    private static final JLabel LABEL = new JLabel();
    private static final long serialVersionUID = 4328743;
    /**
     * No argument passed.
     */
    @Override
    public void start() {
        final CharacterModel charMod = new CharacterModelImpl();
        final CharacterController charContr = new CharacterControllerImpl();
        charContr.setCharactermodel(charMod);
        // Define serialization id to avoid serialization related bugs
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(Constants.PANEL_WIDTH, Constants.PANEL_HEIGHT);

        this.setLayout(null);
        this.addKeyListener(charContr.getCharacterListener());
        LABEL.setBounds(Constants.CHARACTER_START_POS.X(), Constants.CHARACTER_START_POS.Y(), Constants.CHARACTER_WIDTH,
                Constants.CHARACTER_HEIGHT);
        LABEL.setBackground(Color.RED);
        LABEL.setOpaque(true);
        this.add(LABEL);
        this.setVisible(true);
    }

    /**
     * Update the view .
     * @param newPos
     */
    public static void update(final Position newPos) {
        LABEL.setLocation(newPos.X(), newPos.Y());
    }
}
