package gymlife.view.minigame;


import gymlife.controller.api.Controller;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.Serial;

/**
 * The SquatView class represents the view component for the Squat mini-game.
 * It extends the JPanel class and provides the necessary UI elements and
 * functionality
 * for the Squat mini-game.
 */
public class SquatView extends JPanel {
    @Serial
    private static final long serialVersionUID = -5624416626690898281L;

    /**
     * Creates a new SquatView object.
     *
     * @param controller the controller for the mini-game
     */
    public SquatView(final Controller controller) {
        this.setLayout(null);
        final JLayeredPane layeredPane = new JLayeredPane();
        this.add(layeredPane);

        final JLabel backgroundLabel = new JLabel();
        layeredPane.add(backgroundLabel, 0);
        final JLabel characterLabel = new JLabel();
        layeredPane.add(characterLabel, 1);

        final ImageIcon backgroundImage = new ImageIcon("src/main/resources/images/Minigame/background.png");

        backgroundLabel.setIcon(backgroundImage);

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(final KeyEvent e) {
                controller.notifyUserAction();
            }
        });
        this.setVisible(true);
    }


    /**
     * Resizes the components of the view.
     */
    public void resizeComponents() {
    }
}
