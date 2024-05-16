package gymlife.view.minigame;

import gymlife.controller.api.Controller;
import gymlife.utility.minigame.MinigameDifficulty;
import gymlife.utility.minigame.MinigameType;

import javax.swing.*;
import java.awt.*;

public class ScoreBoardView extends JPanel {
    private final JLabel easyPanel = new JLabel();
    private final JLabel mediumPanel = new JLabel();
    private final JLabel hardPanel = new JLabel();
    private final CardLayout cardLayout = new CardLayout();

    public ScoreBoardView(final Controller controller) {
        this.setLayout(new BorderLayout());
        JButton easyButton = new JButton("Easy");
        JButton mediumButton = new JButton("Medium");
        JButton hardButton = new JButton("Hard");

        JPanel buttonPanel = new JPanel(new FlowLayout());

        buttonPanel.add(easyButton);
        buttonPanel.add(mediumButton);
        buttonPanel.add(hardButton);

        this.add(buttonPanel, BorderLayout.NORTH);

        JPanel cards = new JPanel(cardLayout);
        cards.add(easyPanel, "Easy");
        cards.add(mediumPanel, "Medium");
        cards.add(hardPanel, "Hard");

        this.add(cards, BorderLayout.CENTER);

        easyButton.addActionListener(e -> {
            cardLayout.show(cards, "Easy");
            controller.getScores(controller.getMinigameType(), MinigameDifficulty.EASY);
            for (int elem : controller.getScores(controller.getMinigameType(), MinigameDifficulty.EASY)){
                System.out.println(elem);
                easyPanel.setText(easyPanel.getText() + elem + "\n");
            }


        });

        mediumButton.addActionListener(e -> {
            cardLayout.show(cards, "Medium");
            controller.getScores(controller.getMinigameType(), MinigameDifficulty.MEDIUM);
        });

        hardButton.addActionListener(e -> {
            cardLayout.show(cards, "Hard");
            controller.getScores(controller.getMinigameType(), MinigameDifficulty.HARD);
        });

        this.setVisible(true);
    }
}