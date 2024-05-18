package gymlife.view.minigame;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

import gymlife.controller.api.Controller;
import gymlife.utility.FontLoader;
import gymlife.utility.minigame.MinigameDifficulty;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
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
        JPanel difficultyButtons = new JPanel();
        JLabel difficultyLabel = new JLabel();
        final JButton easyButton = new JButton("Easy");
        final JButton normalButton = new JButton("Normal");
        final JButton hardButton = new JButton("Hard");

        FontLoader.loadFont();

        difficultyLabel.setText("Choose the difficulty level for the minigame, " +
                "once you have choosen press f to start the minigame.");
        difficultyLabel.setFont(FontLoader.getCustomFont(25));

        this.setLayout(new GridLayout(2, 1));

        this.setBackground(Color.BLACK);
        this.setForeground(Color.WHITE);


        this.add(difficultyLabel, BorderLayout.NORTH);
        this.add(difficultyButtons, BorderLayout.SOUTH);

        Dimension buttonSize = new Dimension(100, 100);
        easyButton.setPreferredSize(buttonSize);
        normalButton.setPreferredSize(buttonSize);
        hardButton.setPreferredSize(buttonSize);

        difficultyButtons.add(easyButton);
        difficultyButtons.add(normalButton);
        difficultyButtons.add(hardButton);

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

    public void resizeComponents() {
    }
}
