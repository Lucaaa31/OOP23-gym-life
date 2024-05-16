package gymlife.view.minigame;

import javax.swing.JButton;
import javax.swing.JPanel;

import gymlife.controller.api.Controller;
import gymlife.utility.minigame.MinigameDifficulty;

import java.awt.*;
import java.io.Serial;

/**
 * The MinigameDifficultyView class represents a JPanel that allows the user to
 * select the difficulty level for the minigame.
 */
public class MinigameDifficultyView extends JPanel {
    @Serial
    private static final long serialVersionUID = -6009318744152514288L;

    /**
     * Constructs a MinigameDifficultyView object with the specified controller.
     *
     * @param controller the controller object used to handle user interactions
     */
    public MinigameDifficultyView(final Controller controller) {
        final JButton easyButton = new JButton("Easy");
        final JButton normalButton = new JButton("Normal");
        final JButton hardButton = new JButton("Hard");

        this.setBackground(Color.CYAN);

        this.add(easyButton);
        this.add(normalButton);
        this.add(hardButton);

        easyButton.addActionListener(e -> {
            controller.setDifficulty(MinigameDifficulty.EASY);
        });

        normalButton.addActionListener(e -> {
            controller.setDifficulty(MinigameDifficulty.MEDIUM);
        });

        hardButton.addActionListener(e -> {
            controller.setDifficulty(MinigameDifficulty.HARD);
        });
    }

}
