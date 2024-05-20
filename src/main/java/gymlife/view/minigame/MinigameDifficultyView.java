package gymlife.view.minigame;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import gymlife.utility.FontLoader;
import gymlife.utility.minigame.MinigameDifficulty;
import gymlife.view.DimensionGetter;
import gymlife.view.api.GamePanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serial;

/**
 * The MinigameDifficultyView class represents a JPanel that allows the user to
 * select the difficulty level for the minigame.
 */
public class MinigameDifficultyView extends GamePanel {
    @Serial
    private static final long serialVersionUID = -6009318744152514288L;

    private final JButton easyButton;
    private final JButton mediumButton;
    private final JButton hardButton;
    private final transient DimensionGetter dimensionGetter;
    private final JPanel titlePanel;
    private final JLabel descriptionLabel = new JLabel("Go on the button for see the description");

    /**
     * Constructs a MinigameDifficultyView object with the specified controller.
     *
     * @param dimensionGetter the dimension getter for screen size adjustments
     */
    public MinigameDifficultyView(final DimensionGetter dimensionGetter) {
        final int titleFontSize = 80;
        final int descriptionFontSize = 25;
        final Color backgroundColor = new Color(33, 133, 216);
        this.dimensionGetter = dimensionGetter;

        this.setLayout(new BorderLayout());


        titlePanel = new JPanel(new GridBagLayout());
        titlePanel.setBackground(backgroundColor);

        final JLabel titleLabel = new JLabel("Choose the difficulty");
        titleLabel.setFont(FontLoader.getCustomFont(titleFontSize));
        titleLabel.setForeground(Color.WHITE);

        descriptionLabel.setFont(FontLoader.getCustomFont(descriptionFontSize));
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        descriptionLabel.setForeground(Color.WHITE);

        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 1; // this will make the component occupy all available vertical space

        titlePanel.add(titleLabel, gbc);
        titlePanel.add(descriptionLabel, gbc);
        titlePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        titlePanel.add(descriptionLabel);
        titlePanel.add(Box.createVerticalGlue());

        final JPanel difficultyButtonsPanel = new JPanel();
        difficultyButtonsPanel.setLayout(new GridLayout(1, 3));
        difficultyButtonsPanel.setBackground(backgroundColor);

        easyButton = buildButton(Color.GREEN, MinigameDifficulty.EASY);
        mediumButton = buildButton(Color.YELLOW, MinigameDifficulty.MEDIUM);
        hardButton = buildButton(Color.RED, MinigameDifficulty.HARD);

        difficultyButtonsPanel.add(easyButton);
        difficultyButtonsPanel.add(mediumButton);
        difficultyButtonsPanel.add(hardButton);

        this.add(titlePanel, BorderLayout.NORTH);
        this.add(difficultyButtonsPanel, BorderLayout.SOUTH);

        this.setBackground(backgroundColor);
    }

    /**
     * Initializes the action listener for the buttons in the view.
     *
     * @param actionListener the action listener to be initialized
     * @param difficulty     the difficulty level of the minigame
     */
    public void initializeActionListener(final ActionListener actionListener, final MinigameDifficulty difficulty) {
        switch (difficulty) {
            case EASY -> {
                easyButton.addActionListener(actionListener);
                easyButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(final MouseEvent e) {
                        descriptionLabel.setText(getText(MinigameDifficulty.EASY));
                    }
                });
            }
            case MEDIUM -> {
                mediumButton.addActionListener(actionListener);
                mediumButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(final MouseEvent e) {
                        descriptionLabel.setText(getText(MinigameDifficulty.MEDIUM));
                    }
                });
            }
            case HARD -> {
                hardButton.addActionListener(actionListener);
                hardButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(final MouseEvent e) {
                        descriptionLabel.setText(getText(MinigameDifficulty.HARD));
                    }
                });
            }
            default -> throw new IllegalArgumentException("Invalid difficulty level");
        }
    }

    /**
     * Builds a JButton with the specified color and difficulty level.
     *
     * @param color      the color of the button
     * @param difficulty the difficulty level of the minigame
     * @return the JButton object with the specified color and difficulty level
     */
    private JButton buildButton(final Color color, final MinigameDifficulty difficulty) {
        final int borderSize = 5;
        final int fontSize = 60;
        final JButton button = new JButton(difficulty.toString());
        button.setBackground(color);
        button.setBorder(new LineBorder(Color.WHITE, borderSize));
        button.setForeground(Color.BLACK);
        button.setBorderPainted(true);
        button.setPreferredSize(dimensionGetter.getButtonStartDimension());
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setFont(FontLoader.getCustomFont(fontSize));
        button.setVerticalAlignment(SwingConstants.CENTER);

        return button;
    }

    /**
     * Resize the components in the view based on the screen size.
     */
    @Override
    public void resizeComponents() {
        easyButton.setPreferredSize(dimensionGetter.getButtonStartDimension());
        mediumButton.setPreferredSize(dimensionGetter.getButtonStartDimension());
        hardButton.setPreferredSize(dimensionGetter.getButtonStartDimension());

        for (final Component comp : titlePanel.getComponents()) {
            if (comp instanceof JLabel) {
                comp.setFont(FontLoader.getCustomFont(dimensionGetter.getSmallFontSize()));
            }
        }
        revalidate();
        repaint();
    }

    /**
     * Returns the name of the panel.
     *
     * @return the name of the panel
     */
    @Override
    public String getPanelName() {
        return "difficultyView";
    }

    /**
     * Returns the text for the specified difficulty level.
     *
     * @param difficulty the difficulty level of the minigame
     * @return the text for the specified difficulty level
     */
    private String getText(final MinigameDifficulty difficulty) {
        return "<html>"
                + formatLine("Reps for complete: ", difficulty.getRequiredReps())
                + formatLine("Experience gained: ", difficulty.getExperienceGained())
                + formatLine("Reaction time: ", difficulty.getReactionTime())
                + formatLine("Touch for lift: ", difficulty.getTouchForLift())
                + formatLine("Max mistakes: ", difficulty.getMaxMistakes())
                + "</html>";
    }

    private static String formatLine(final String text, final int value) {
        return text + value + "<br>";
    }
}
