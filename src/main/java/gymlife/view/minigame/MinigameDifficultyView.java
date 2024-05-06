package gymlife.view.minigame;

import gymlife.controller.api.Controller;
import gymlife.utility.MinigameDifficulty;

import javax.swing.*;

public class MinigameDifficultyView extends JPanel{
    private Controller controller;
    private JButton easyButton;
    private JButton normalButton;
    private JButton hardButton;

    
    public MinigameDifficultyView(final Controller controller) {
        this.controller = controller;
        this.easyButton = new JButton("Easy");
        this.normalButton = new JButton("Normal");
        this.hardButton = new JButton("Hard");

        this.add(easyButton);
        this.add(normalButton);
        this.add(hardButton);

        easyButton.addActionListener(e -> {
            controller.setMinigame(MinigameDifficulty.EASY);
        });

        normalButton.addActionListener(e -> {
            controller.setMinigame(MinigameDifficulty.MEDIUM);
        });

        hardButton.addActionListener(e -> {
            controller.setMinigame(MinigameDifficulty.HARD);
        });
    }



}
