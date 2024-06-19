package gymlife.view.minigame;

import gymlife.controller.api.Controller;
import gymlife.utility.FontLoader;
import gymlife.utility.minigame.MinigameDifficulty;
import gymlife.utility.minigame.MinigameType;
import gymlife.view.DimensionGetter;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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
    private static final int MAX_TIMES = 5;
    private static final String EASY = "Easy";
    private static final String MEDIUM = "Medium";
    private static final String HARD = "Hard";
    private final JLabel easyButton = new JLabel(EASY);
    private final JLabel mediumButton = new JLabel(MEDIUM);
    private final JLabel hardButton = new JLabel(HARD);

    /**
     * Creates a new ScoreBoardView object.
     *
     * @param controller          the controller
     * @param currentMinigameType the current minigame type
     * @param backButton          the back button
     * @param dimensionGetter     the dimension getter
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


        for (final Component button : buttonPanel.getComponents()) {
            button.setFont(FontLoader.getCustomFont(dimensionGetter.getSmallFontSize() / 2));
            button.setBackground(getColor(((JLabel) button).getText()));
            ((JLabel) button).setOpaque(true);
            button.setPreferredSize(new Dimension(
                    dimensionGetter.getSideDimension().width / 4,
                    dimensionGetter.getScenarioDimension().height / DimensionGetter.
                            getMinigameButtonFontProportion()));
            ((JLabel) button).setHorizontalAlignment(SwingConstants.CENTER);
            ((JLabel) button).setBorder(new MatteBorder(3, 3, 3, 3, Color.BLACK));
        }

        final JLabel back = new JLabel("Back");

        back.setFont(FontLoader.getCustomFont(dimensionGetter.getSmallFontSize() / 2));
        back.setHorizontalAlignment(SwingConstants.CENTER);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setOpaque(true);


        final JPanel backPanel = new JPanel(new BorderLayout());
        backPanel.add(back, BorderLayout.WEST);
        this.add(backPanel, BorderLayout.SOUTH);


        back.addMouseListener(backButton);


        this.add(buttonPanel, BorderLayout.NORTH);

        for (int i = 0; i < MAX_TIMES; i++) {
            easyPanel.add(new JLabel());
            mediumPanel.add(new JLabel());
            hardPanel.add(new JLabel());
        }

        final JPanel cards = new JPanel(cardLayout);
        cards.add(easyPanel, EASY);
        cards.add(mediumPanel, MEDIUM);
        cards.add(hardPanel, HARD);


        this.add(back, BorderLayout.SOUTH);
        this.add(cards, BorderLayout.CENTER);

        easyButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                cardLayout.show(cards, EASY);
                updatePanel(easyPanel, controller.getScores(currentMinigameType, MinigameDifficulty.EASY));
                updateButton(MinigameDifficulty.EASY);
            }
        });

        mediumButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                cardLayout.show(cards, MEDIUM);
                updatePanel(mediumPanel, controller.getScores(currentMinigameType, MinigameDifficulty.MEDIUM));
                updateButton(MinigameDifficulty.MEDIUM);
            }
        });

        hardButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                cardLayout.show(cards, HARD);
                updatePanel(hardPanel, controller.getScores(currentMinigameType, MinigameDifficulty.HARD));
                updateButton(MinigameDifficulty.HARD);
            }
        });

        this.setVisible(true);
    }

    /**
     * Returns the color based on the difficulty.
     *
     * @param difficulty the difficulty level.
     * @return the color based on the difficulty level.
     */
    private Color getColor(final String difficulty) {
        return switch (difficulty) {
            case EASY -> Color.GREEN;
            case MEDIUM -> Color.YELLOW;
            case HARD -> Color.RED;
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
