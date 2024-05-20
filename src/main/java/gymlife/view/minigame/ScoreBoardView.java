package gymlife.view.minigame;

import gymlife.controller.api.Controller;
import gymlife.utility.minigame.MinigameDifficulty;


import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Component;
import java.io.Serial;
import java.util.List;

/**
 * The ScoreBoardView class represents the view component for the ScoreBoard mini-game.
 * It extends the JPanel class and provides the necessary UI elements and functionality
 * for the ScoreBoard mini-game.
 */
public class ScoreBoardView extends JPanel {
    @Serial
    private static final long serialVersionUID = -8662696018025077116L;
    private final JPanel easyPanel = createPanel();
    private final JPanel mediumPanel = createPanel();
    private final JPanel hardPanel = createPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final JButton easyButton = new JButton("Easy");
    private final JButton mediumButton = new JButton("Medium");
    private final JButton hardButton = new JButton("Hard");
    private static final int MAX_TIMES = 5;

    /**
     * Creates a new ScoreBoardView object.
     *
     * @param controller the controller
     */
    public ScoreBoardView(final Controller controller) {
        this.setLayout(new BorderLayout());
        final JPanel buttonPanel = new JPanel(new FlowLayout());

        buttonPanel.add(easyButton);
        buttonPanel.add(mediumButton);
        buttonPanel.add(hardButton);

        this.add(buttonPanel, BorderLayout.NORTH);

        for (int i = 0; i < MAX_TIMES; i++) {
            easyPanel.add(new JLabel());
            mediumPanel.add(new JLabel());
            hardPanel.add(new JLabel());
        }

        final JPanel cards = new JPanel(cardLayout);
        cards.add(easyPanel, "Easy");
        cards.add(mediumPanel, "Medium");
        cards.add(hardPanel, "Hard");

        this.add(cards, BorderLayout.CENTER);

        easyButton.addActionListener(e -> {
            cardLayout.show(cards, "Easy");
            updatePanel(easyPanel, controller.getScores(controller.getMinigameType(), MinigameDifficulty.EASY));
            updateButton(MinigameDifficulty.EASY);
        });

        mediumButton.addActionListener(e -> {
            cardLayout.show(cards, "Medium");
            updatePanel(mediumPanel, controller.getScores(controller.getMinigameType(), MinigameDifficulty.MEDIUM));
            updateButton(MinigameDifficulty.MEDIUM);
        });

        hardButton.addActionListener(e -> {
            cardLayout.show(cards, "Hard");
            updatePanel(hardPanel, controller.getScores(controller.getMinigameType(), MinigameDifficulty.HARD));
            updateButton(MinigameDifficulty.HARD);
        });

        this.setVisible(true);
    }

    /**
     * Creates a new JPanel object.
     *
     * @return a new JPanel object.
     */
    private JPanel createPanel() {
        final JPanel panel = new JPanel(new GridLayout(MAX_TIMES, 0));
        for (int i = 0; i < MAX_TIMES; i++) {
            final JTextArea textArea = new JTextArea();
            textArea.setEditable(false);
            panel.add(textArea);
        }
        return panel;
    }

    /**
     * Updates the panel with the given scores.
     *
     * @param panel  the panel to update.
     * @param scores the scores to display.
     */
    private void updatePanel(final JPanel panel, final List<Integer> scores) {
        int i = 0;
        for (final Component component : panel.getComponents()) {
            if (component instanceof JTextArea textArea) {
                if (scores == null) {
                    textArea.setText("--:--");
                } else {
                    if (i < scores.size()) {
                        textArea.setText(String.format("%3d:%02d secs", scores.get(i) / 1000, scores.get(i) % 1000));
                    } else {
                        textArea.setText("--:--");
                    }
                }
                i++;
            }
        }
    }

    /**
     * Updates the button to disable the given button.
     *
     * @param buttonToDisable the button to disable.
     */
    private void updateButton(final MinigameDifficulty buttonToDisable) {
        easyButton.setEnabled(true);
        mediumButton.setEnabled(true);
        hardButton.setEnabled(true);

        switch (buttonToDisable) {
            case EASY -> easyButton.setEnabled(false);
            case MEDIUM -> mediumButton.setEnabled(false);
            case HARD -> hardButton.setEnabled(false);
            default -> throw new IllegalArgumentException("Invalid difficulty level");
        }
    }

}
