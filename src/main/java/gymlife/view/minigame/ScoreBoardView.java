package gymlife.view.minigame;

import gymlife.controller.api.Controller;
import gymlife.utility.minigame.MinigameDifficulty;


import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.List;


public class ScoreBoardView extends JPanel {
    private final JPanel easyPanel = createPanel();
    private final JPanel mediumPanel = createPanel();
    private final JPanel hardPanel = createPanel();
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


        for (int i = 0; i < 5; i++) {
            easyPanel.add(new JLabel());
            mediumPanel.add(new JLabel());
            hardPanel.add(new JLabel());
        }

        JPanel cards = new JPanel(cardLayout);
        cards.add(easyPanel, "Easy");
        cards.add(mediumPanel, "Medium");
        cards.add(hardPanel, "Hard");

        this.add(cards, BorderLayout.CENTER);

        easyButton.addActionListener(e -> {
            cardLayout.show(cards, "Easy");
            updatePanel(easyPanel, controller.getScores(controller.getMinigameType(), MinigameDifficulty.EASY));

        });

        mediumButton.addActionListener(e -> {
            cardLayout.show(cards, "Medium");
            updatePanel(mediumPanel, controller.getScores(controller.getMinigameType(), MinigameDifficulty.MEDIUM));
        });

        hardButton.addActionListener(e -> {
            cardLayout.show(cards, "Hard");
            updatePanel(hardPanel, controller.getScores(controller.getMinigameType(), MinigameDifficulty.HARD));
        });

        this.setVisible(true);
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 0));
        for (int i = 0; i < 5; i++) {
            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);
            panel.add(textArea);
        }
        return panel;
    }

    private void updatePanel(final JPanel panel, final List<Integer> scores) {
        int i = 0;
        for (Component component : panel.getComponents()) {
            if (component instanceof JTextArea textArea) {
                if (scores == null) {
                    textArea.setText("--:--");
                } else {
                    if (i < scores.size()) {
                        textArea.setText(String.format("%3d:%02d secs", scores.get(i)/1000, scores.get(i)%1000));
                    } else {
                        textArea.setText("--:--");
                    }
                }
                i++;
            }
        }
    }

}