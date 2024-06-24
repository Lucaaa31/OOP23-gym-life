package gymlife.view.minigame;

import gymlife.controller.api.Controller;
import gymlife.utility.FontLoader;
import gymlife.utility.minigame.MinigameState;
import gymlife.utility.DimensionGetter;
import gymlife.view.api.GamePanel;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.io.Serial;
import java.util.Map;

/**
 * Abstract class that represents the view of a minigame.
 */
public abstract class MinigameView extends GamePanel {
    @Serial
    private static final long serialVersionUID = 4324743;
    private final JLabel characterLabel = new JLabel();
    private final transient DimensionGetter dimensionGetter;
    private final JLabel timerView = new JLabel();
    private final JLayeredPane layeredPane = new JLayeredPane();
    private final transient Controller controller;
    private final JProgressBar progressBar = new JProgressBar();
    private final Map<String, Color> colorMap = Map.of(
            "backgroundColorGreen", new Color(29, 110, 12),
            "foregroundColorGreen", new Color(72, 253, 0),
            "backgroundColorRed", new Color(142, 25, 25),
            "foregroundColorRed", new Color(255, 0, 0),
            "backgroundColorYellow", new Color(118, 119, 34),
            "foregroundColorYellow", new Color(248, 255, 0)
    );
    private ImageIcon characterImage;
    private final JLabel backgroundLabel = new JLabel();
    private final ImageIcon backgroundImage;
    private static final String BACKGROUND_COLOR_GREEN = "backgroundColorGreen";
    private static final String FOREGROUND_COLOR_GREEN = "foregroundColorGreen";
    private final JLabel orderLabel = new JLabel("Press button to start!");

    /**
     * Constructor of the class.
     *
     * @param controller      the controller of the game.
     * @param dimensionGetter the dimension of the game.
     * @param minigameType    the type of the minigame.
     */
    public MinigameView(final Controller controller,
                        final DimensionGetter dimensionGetter,
                        final String minigameType) {
        this.controller = controller;
        this.dimensionGetter = dimensionGetter;


        this.setLayout(new BorderLayout());
        this.setSize(dimensionGetter.getScenarioDimension());

        backgroundImage = new ImageIcon(ClassLoader
                .getSystemResource("images/minigame/background.png"));
        characterImage = new ImageIcon(ClassLoader
                .getSystemResource("images/minigame/"
                        + minigameType
                        + "/sprite_0.png"));

        this.add(progressBar, BorderLayout.EAST);

        progressBar.setOrientation(JProgressBar.VERTICAL);
        progressBar.setPreferredSize(new Dimension(100, dimensionGetter.getScenarioDimension().height));

        progressBar.setBackground(colorMap.get(BACKGROUND_COLOR_GREEN));
        progressBar.setForeground(colorMap.get(FOREGROUND_COLOR_GREEN));


        this.add(layeredPane, BorderLayout.CENTER);
        layeredPane.setLayout(null);

        layeredPane.setBounds(0, 0, dimensionGetter
                        .getScenarioDimension().width - progressBar.getWidth(),
                dimensionGetter.getScenarioDimension().height);

        timerView.setFont(FontLoader.getCustomFont(dimensionGetter.getSmallFontSize()));
        timerView.setForeground(Color.YELLOW);

        timerView.setBounds(10, 10,
                dimensionGetter.getTimerMinigameDimension().width,
                dimensionGetter.getTimerMinigameDimension().height);

        backgroundImage.setImage(backgroundImage.getImage()
                .getScaledInstance(
                        dimensionGetter.getMinigameScenarioWidht(),
                        dimensionGetter.getScenarioDimension().height,
                        Image.SCALE_SMOOTH));


        backgroundLabel.setIcon(backgroundImage);
        backgroundLabel.setBounds(0, 0, dimensionGetter.getMinigameScenarioWidht(),
                dimensionGetter.getScenarioDimension().height);

        characterLabel.setSize(dimensionGetter.getCharacterMinigameDimension());

        characterLabel.setLocation(dimensionGetter.getMinigameScenarioWidht() / 2,
                dimensionGetter.getCharacterMinigamePos().height);

        characterLabel.setIcon(characterImage);
        characterLabel.setVisible(true);

        layeredPane.add(backgroundLabel, Integer.valueOf(0));
        layeredPane.add(characterLabel, Integer.valueOf(1));
        layeredPane.add(timerView, Integer.valueOf(1));


        orderLabel.setFont(FontLoader.getCustomFont(dimensionGetter.getSmallFontSize()));
        orderLabel.setForeground(Color.YELLOW);
        orderLabel.setBounds(
                0,
                dimensionGetter.getScenarioDimension().height / 4,
                dimensionGetter.getMinigameScenarioWidht(),
                dimensionGetter.getTimerMinigameDimension().height);
        orderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        layeredPane.add(orderLabel, Integer.valueOf(1));

    }

    /**
     * Method that starts the animation of the character.
     */
    public abstract void doAnimation();

    /**
     * Method used by the subclasses for adding a component at the layered panel.
     *
     * @param component the component to add.
     */
    public void addLayeredPanel(final JComponent component) {
        layeredPane.add(component, Integer.valueOf(1000));
    }

    /**
     * Method that starts the timer of the minigame.
     */
    public void timerView() {
        new Thread(() -> {
            int i = 0;
            while (controller.getMinigameState() != MinigameState.ENDED_WON
                    && controller.getMinigameState() != MinigameState.ENDED_LOST) {
                i++;
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ignored) {
                }
                timerView.setText(String.format("%02d:%02d", i / 1000, (i % 1000) / 10));
            }
        }).start();
    }

    /**
     * Method that gets the character image.
     *
     * @param path the path of the image.
     * @return the image of the character.
     */
    public ImageIcon getCharacterImage(final String path) {
        return new ImageIcon(new ImageIcon(ClassLoader.getSystemResource(path))
                .getImage()
                .getScaledInstance(dimensionGetter.getCharacterMinigameDimension().width,
                        dimensionGetter.getCharacterMinigameDimension().height,
                        Image.SCALE_DEFAULT));
    }

    /**
     * Method that updates the progressBar state based on the minigame player action.
     */
    public void progressBarHandler() {
        final int lowProgress = 33;
        final int highProgress = 66;
        if (progressBar.getValue() < lowProgress) {
            progressBar.setBackground(colorMap.get(BACKGROUND_COLOR_GREEN));
            progressBar.setForeground(colorMap.get(FOREGROUND_COLOR_GREEN));

        } else if (progressBar.getValue() >= lowProgress && progressBar.getValue() < highProgress) {
            progressBar.setBackground(colorMap.get("backgroundColorYellow"));
            progressBar.setForeground(colorMap.get("foregroundColorYellow"));
        } else if (progressBar.getValue() >= highProgress) {
            progressBar.setBackground(colorMap.get("backgroundColorRed"));
            progressBar.setForeground(colorMap.get("foregroundColorRed"));
        }

    }


    /**
     * Method that updates the view based on the minigame player action.
     */
    public void handleMinigameState() {
        orderLabel.setText(controller.getMinigameState().getText());
        switch (controller.getMinigameState()) {
            case PRESSED_START -> {
                timerView();
                doAnimation();
            }
            case RUNNING, VALID_PRESS -> {
                progressBar.setValue(progressBar.getValue() + controller.getDifficulty().getProgress());
                orderLabel.setText(controller.getMinigameType().getDescription());
            }
            case REP_REACHED -> {
                progressBar.setValue(0);
                progressBar.setBackground(colorMap.get(FOREGROUND_COLOR_GREEN));
                doAnimation();
            }
            case INVALID_PRESS -> {
                progressBar.setValue(0);
                progressBar.setBackground(colorMap.get("foregroundColorRed"));
                doAnimation();
            }
            case ENDED_WON, ENDED_LOST -> this.setVisible(false);
            default -> {
            }
        }

    }

    /**
     * Method that sets the icon of the character.
     *
     * @param icon the icon of the character.
     */
    public void setCharacterLabelIcon(final ImageIcon icon) {
        setCharacterImageIcon(icon);
        characterLabel.setIcon(characterImage);
    }

    /**
     * Method that sets the icon of the character.
     *
     * @param icon the icon of the character.
     */
    private void setCharacterImageIcon(final ImageIcon icon) {
        characterImage = icon;
    }

    /**
     * Method that sets the text of the order label for the squat minigame.
     *
     * @param text  the text of the order label.
     * @param color the color of the order label.
     */
    public void setText(final String text, final Color color) {
        orderLabel.setForeground(color);
        orderLabel.setText(text);
    }

    /**
     * Method that gets the character label dimension.
     *
     * @return the character label dimension.
     */
    public Rectangle getCharacterLabelDimension() {
        return characterLabel.getBounds();
    }

    /**
     * Method that updates the view based on the minigame player action.
     */
    @Override
    public void resizeComponents() {
        this.setSize(dimensionGetter.getScenarioDimension());
        progressBar.setPreferredSize(new Dimension(100, dimensionGetter.getScenarioDimension().height));
        progressBar.setSize(100, dimensionGetter.getScenarioDimension().height);

        characterLabel.setSize(dimensionGetter.getCharacterMinigameDimension());
        characterLabel.setLocation(dimensionGetter
                        .getCharacterMinigamePos().width,
                dimensionGetter
                        .getCharacterMinigamePos().height);
        characterImage.setImage(characterImage.getImage()
                .getScaledInstance(dimensionGetter.getCharacterMinigameDimension().width,
                        dimensionGetter.getCharacterMinigameDimension().height,
                        Image.SCALE_DEFAULT));
        characterLabel.setIcon(characterImage);

        backgroundLabel.setSize(dimensionGetter.getScenarioDimension().width - progressBar.getWidth(),
                dimensionGetter.getScenarioDimension().height);
        backgroundImage.setImage(backgroundImage.getImage()
                .getScaledInstance(dimensionGetter.getScenarioDimension().width - progressBar.getWidth(),
                        dimensionGetter.getScenarioDimension().height, Image.SCALE_DEFAULT));
        backgroundLabel.setIcon(backgroundImage);
        orderLabel.setFont(FontLoader.getCustomFont(dimensionGetter.getSmallFontSize()));
        orderLabel.setForeground(Color.YELLOW);
        orderLabel.setBounds(
                0,
                dimensionGetter.getScenarioDimension().height / 4,
                dimensionGetter.getMinigameScenarioWidht(),
                dimensionGetter.getTimerMinigameDimension().height);
        timerView.setBounds(10, 10,
                dimensionGetter.getTimerMinigameDimension().width,
                dimensionGetter.getTimerMinigameDimension().height);
        timerView.setFont(FontLoader.getCustomFont(dimensionGetter.getSmallFontSize()));
    }

    /**
     * The game of the GamePanel.
     *
     * @return the game of the GamePanel.
     */
    @Override
    public String getPanelName() {
        return this.getClass().getSimpleName();
    }


}
