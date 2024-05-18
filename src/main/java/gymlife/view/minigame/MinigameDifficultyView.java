package gymlife.view.minigame;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

import gymlife.controller.api.Controller;
import gymlife.utility.FontLoader;
import gymlife.utility.minigame.MinigameDifficulty;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.Serial;

/**
 * The MinigameDifficultyView class represents a JPanel that allows the user to
 * select the difficulty level for the minigame.
 */
public class MinigameDifficultyView extends JPanel {
    @Serial
    private static final long serialVersionUID = -6009318744152514288L;

    final JButton easyButton = new JButton("Easy");
    final JButton normalButton = new JButton("Normal");
    final JButton hardButton = new JButton("Hard");
    /**
     * Constructs a MinigameDifficultyView object with the specified controller.
     *
     * @param controller the controller object used to handle user interactions
     */
    public MinigameDifficultyView(final Controller controller, final ActionListener deca) {
        JPanel difficultyButtons = new JPanel();
        JLabel difficultyLabel = new JLabel();


        FontLoader.loadFont();

        difficultyLabel.setText("Choose the difficulty level for the minigame, " +
                "once you have choosen press f to start the minigame.");
        difficultyLabel.setFont(FontLoader.getCustomFont(25));

        this.setLayout(new GridLayout(2, 0));

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

        easyButton.addActionListener(deca);
        normalButton.addActionListener(deca);
        hardButton.addActionListener(deca);
    }



    public void resizeComponents() {
    }
}
