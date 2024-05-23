package gymlife.view.minigame;

import gymlife.utility.FontLoader;
import gymlife.utility.GameDifficulty;
import gymlife.utility.minigame.MinigameDifficulty;
import gymlife.view.DimensionGetter;
import gymlife.view.api.GamePanel;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.Serial;

/**
 * The DifficultyMenu class represents the menu where the user can select the difficulty level of the minigame.
 * Used in the start of the minigame amd the game.
 */
public class DifficultyMenu extends GamePanel {
    @Serial
    private static final long serialVersionUID = 2098722838461593595L;
    private final transient DimensionGetter dimensionGetter;
    private final JLabel descriptionText = new JLabel();
    private JButton buttonEasy;
    private JButton buttonMedium;
    private JButton buttonHard;
    private final JLabel titleLabel = new JLabel();
    private JPanel panelEasy;
    private JPanel panelMedium;
    private JPanel panelHard;
    private final JLabel buttonLabel = new JLabel();


    /**
     * Constructs a DifficultyMenu object with the specified action listeners.
     *
     * @param dimensionGetter the dimension getter
     * @param easyListener    the action listener for the easy button
     * @param mediumListener  the action listener for the medium button
     * @param hardListener    the action listener for the hard button
     */
    public DifficultyMenu(final DimensionGetter dimensionGetter,
                          final ActionListener easyListener,
                          final ActionListener mediumListener,
                          final ActionListener hardListener) {
        this.dimensionGetter = dimensionGetter;

        titleLabel.setText("New Game");
        titleLabel.setBackground(Color.DARK_GRAY);
        descriptionText.setBackground(Color.DARK_GRAY);
        buttonLabel.setBackground(Color.DARK_GRAY);

        this.buildMenu(easyListener, mediumListener, hardListener);
    }

    /**
     * Builds the menu with the specified action listeners.
     *
     * @param dimensionGetter the dimension getter
     * @param easyListener    the action listener for the easy button
     * @param mediumListener  the action listener for the medium button
     * @param hardListener    the action listener for the hard button
     * @param mouseListener   the mouse listener for the buttons
     */
    public DifficultyMenu(final DimensionGetter dimensionGetter,
                          final ActionListener easyListener,
                          final ActionListener mediumListener,
                          final ActionListener hardListener,
                          final MouseListener mouseListener) {
        final Color backgroundColor = new Color(33, 133, 216);
        this.dimensionGetter = dimensionGetter;

        this.buildMenu(easyListener, mediumListener, hardListener);

        titleLabel.setText("Minigame");
        titleLabel.setBackground(backgroundColor);
        descriptionText.setBackground(backgroundColor);

        panelEasy.setBackground(backgroundColor);
        panelMedium.setBackground(backgroundColor);
        panelHard.setBackground(backgroundColor);

        this.buildMouseListener(mouseListener);

    }

    /**
     * Builds the action listeners for the buttons.
     *
     * @param mouseListener the mouse listener for the buttons
     */
    private void buildMouseListener(final MouseListener mouseListener) {
        buttonEasy.addMouseListener(mouseListener);
        buttonMedium.addMouseListener(mouseListener);
        buttonHard.addMouseListener(mouseListener);
    }

    /**
     * Builds the menu with the specified action listeners.
     *
     * @param easyListener   the action listener for the easy button
     * @param mediumListener the action listener for the medium button
     * @param hardListener   the action listener for the hard button
     */
    private void buildMenu(final ActionListener easyListener,
                           final ActionListener mediumListener,
                           final ActionListener hardListener) {

        FontLoader.loadFont();
        final int borderSize = 15;
        final int fontDescriptionSize = 55;

        buttonEasy = this.buildButton(Color.GREEN, GameDifficulty.EASY);
        buttonMedium = this.buildButton(Color.YELLOW, GameDifficulty.MEDIUM);
        buttonHard = this.buildButton(Color.RED, GameDifficulty.HARD);

        panelEasy = buildPanel(buttonEasy);
        panelMedium = buildPanel(buttonMedium);
        panelHard = buildPanel(buttonHard);

        buttonLabel.setLayout(new GridLayout(1, 3));
        buttonLabel.setSize(new Dimension(dimensionGetter.getButtonLabelDimension().width * 3,
                dimensionGetter.getButtonLabelDimension().height));

        buttonLabel.add(panelEasy);
        buttonLabel.add(panelMedium);
        buttonLabel.add(panelHard);


        titleLabel.setOpaque(true);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(FontLoader.getCustomFont(100));
        titleLabel.setForeground(Color.WHITE);


        descriptionText.setText("Select the difficulty level of the game");
        descriptionText.setOpaque(true);
        descriptionText.setHorizontalAlignment(SwingConstants.CENTER);
        descriptionText.setVerticalAlignment(SwingConstants.NORTH);
        descriptionText.setFont(FontLoader.getCustomFont(fontDescriptionSize));
        descriptionText.setForeground(Color.WHITE);


        buttonEasy.addActionListener(easyListener);
        buttonMedium.addActionListener(mediumListener);
        buttonHard.addActionListener(hardListener);

        this.setLayout(new GridLayout(3, 1));
        this.add(titleLabel);
        this.add(descriptionText);
        this.add(buttonLabel);
        this.setBorder(new LineBorder(Color.WHITE, borderSize));
    }


    /**
     * Builds a button with the specified color and difficulty level.
     *
     * @param color      the color of the button
     * @param difficulty the difficulty level of the minigame
     * @return the button with the specified color and difficulty level
     */
    private JButton buildButton(final Color color, final GameDifficulty difficulty) {
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
     * Builds a panel containing the specified button.
     *
     * @param button the button to add to the panel
     * @return the panel containing the button
     */
    private JPanel buildPanel(final JButton button) {
        final JPanel panel = new JPanel();
        panel.setOpaque(true);
        panel.setBackground(Color.DARK_GRAY);
        panel.setPreferredSize(dimensionGetter.getButtonLabelDimension());
        panel.setSize(dimensionGetter.getButtonLabelDimension());
        panel.add(button);
        return panel;
    }

    /**
     * Updates the description text based on the selected difficulty level.
     *
     * @param source the button that was clicked
     */
    public void updateDescription(final JButton source) {
        switch (source.getText()) {
            case "EASY" -> descriptionText.setText(getText(MinigameDifficulty.EASY));
            case "MEDIUM" -> descriptionText.setText(getText(MinigameDifficulty.MEDIUM));
            case "HARD" -> descriptionText.setText(getText(MinigameDifficulty.HARD));
            default -> throw new IllegalArgumentException("Invalid difficulty level");
        }
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

    /**
     * Formats a line of text with the specified text and value.
     *
     * @param text  the text to display
     * @param value the value to display
     * @return the formatted line of text
     */
    private static String formatLine(final String text, final int value) {
        return text + value + "<br>";
    }


    /**
     * Resizes the fonts.
     */
    @Override
    public void resizeComponents() {
        final float titleSize = dimensionGetter.getBigFontSize() + 50;

        descriptionText.setFont(FontLoader.getCustomFont(dimensionGetter.getSmallFontSize()));
        titleLabel.setFont(FontLoader.getCustomFont(titleSize));

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
        return "difficultyMenu";
    }
}
