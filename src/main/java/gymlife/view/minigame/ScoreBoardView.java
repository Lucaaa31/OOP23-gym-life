package gymlife.view.minigame;

import gymlife.controller.api.Controller;
import gymlife.utility.FontLoader;
import gymlife.utility.minigame.MinigameDifficulty;
import gymlife.utility.minigame.MinigameType;
import gymlife.view.DimensionGetter;


import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private final JLabel easyButton = new JLabel("Easy");
    private final JLabel mediumButton = new JLabel("Medium");
    private final JLabel hardButton = new JLabel("Hard");
    private static final int MAX_TIMES = 5;

    /**
     * Creates a new ScoreBoardView object.
     *
     * @param controller the controller
     * @param backButton the back button
     */
    public ScoreBoardView(final Controller controller,
                          final MinigameType currentMinigameType,
                          final MouseAdapter backButton,
                          final DimensionGetter dimensionGetter) {
        FontLoader.loadFont();
        this.setLayout(new BorderLayout());

        final JPanel buttonPanel = new JPanel(new FlowLayout());

        buttonPanel.add(easyButton);
        buttonPanel.add(mediumButton);
        buttonPanel.add(hardButton);


        for(Component button : buttonPanel.getComponents()){
            button.setFont(FontLoader.getCustomFont(20));
            button.setBackground(getColor(((JLabel)button).getText()));
            ((JLabel)button).setOpaque(true);
            button.setPreferredSize(new Dimension(70, 30));
            ((JLabel) button).setHorizontalAlignment(SwingConstants.CENTER);
            ((JLabel) button).setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
        }

        final JLabel back = new JLabel("Back");

        back.setFont(FontLoader.getCustomFont(20));
        back.setHorizontalAlignment(SwingConstants.CENTER);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setOpaque(true);



        JPanel backPanel = new JPanel(new BorderLayout());
        backPanel.add(back, BorderLayout.WEST);
        this.add(backPanel, BorderLayout.SOUTH);


        back.addMouseListener(backButton);


        back.setBorder(BorderFactory.createLineBorder(Color.BLACK));


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


        this.add(back, BorderLayout.SOUTH);
        this.add(cards, BorderLayout.CENTER);

        easyButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(cards, "Easy");
                updatePanel(easyPanel, controller.getScores(currentMinigameType, MinigameDifficulty.EASY));
                updateButton(MinigameDifficulty.EASY);
            }
        });

        mediumButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(cards, "Medium");
                updatePanel(mediumPanel, controller.getScores(currentMinigameType, MinigameDifficulty.MEDIUM));
                updateButton(MinigameDifficulty.MEDIUM);
            }
        });

        hardButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(cards, "Hard");
                updatePanel(hardPanel, controller.getScores(currentMinigameType, MinigameDifficulty.HARD));
                updateButton(MinigameDifficulty.HARD);
            }
        });

        this.setVisible(true);
    }

    private Color getColor(final String difficulty){
        return switch (difficulty) {
            case "Easy" -> Color.GREEN;
            case "Medium" -> Color.YELLOW;
            case "Hard" -> Color.RED;
            default -> Color.BLACK;
        };

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
