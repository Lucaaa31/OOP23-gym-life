package gymlife.view.minigame;

import javax.swing.JButton;
import javax.swing.JPanel;

import gymlife.controller.api.Controller;
import gymlife.utility.MinigameDifficulty;

/**
 * The MinigameDifficultyView class represents a JPanel that allows the user to
 * select the difficulty level for the minigame.
 */
public class MinigameDifficultyView extends JPanel {
    private JButton easyButton;
    private JButton normalButton;
    private JButton hardButton;

    /**
     * Constructs a MinigameDifficultyView object with the specified controller.
     *
     * @param controller the controller object used to handle user interactions
     */
    public MinigameDifficultyView(final Controller controller) {
        this.easyButton = new JButton("Easy");
        this.normalButton = new JButton("Normal");
        this.hardButton = new JButton("Hard");

        this.add(easyButton);
        this.add(normalButton);
        this.add(hardButton);

        easyButton.addActionListener(e -> {
            controller.setDifficulty(MinigameDifficulty.EASY);
            this.setVisible(false);
        });

        normalButton.addActionListener(e -> {
            controller.setDifficulty(MinigameDifficulty.MEDIUM);
            this.setVisible(false);
        });

        hardButton.addActionListener(e -> {
            controller.setDifficulty(MinigameDifficulty.HARD);
            this.setVisible(false);
        });
    }

}
