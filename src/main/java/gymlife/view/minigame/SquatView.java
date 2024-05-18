package gymlife.view.minigame;


import gymlife.controller.api.Controller;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import java.awt.*;
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
     */
    public SquatView(final Controller controller) {
        this.setLayout(null);
        JLayeredPane layeredPane = new JLayeredPane();
        this.add(layeredPane);

        JLabel backgroundLabel = new JLabel();
        layeredPane.add(backgroundLabel, 0);
        JLabel characterLabel = new JLabel();
        layeredPane.add(characterLabel, 1);

        final ImageIcon backgroundImage = new ImageIcon("src/main/resources/images/Minigame/background.png");

        backgroundLabel.setIcon(backgroundImage);

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                controller.notifyUserAction();
            }
        });

        this.setVisible(true);

    }


    public void resizeComponents(){
        System.out.println("Resize components");
//        this.setSize(dimensionGetter.getScenarioDimension());
//        progressBar.setPreferredSize(new Dimension(100, dimensionGetter.getScenarioDimension().height));
//        setRandomPositionButton();
//        characterLabel.setSize(dimensionGetter.getCharacterDimension());
//        characterLabel.setLocation(dimensionGetter.getCharacterMinigamePos().width,
//                dimensionGetter.getCharacterMinigamePos().height);
//        characterLabel.setIcon(characterImage);
    }
}
