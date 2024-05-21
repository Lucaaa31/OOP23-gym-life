package gymlife.view.minigame;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JLayeredPane;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.Serial;

import gymlife.controller.api.Controller;
import gymlife.utility.FontLoader;
import gymlife.view.DimensionGetter;
import gymlife.view.api.MinigamePanel;

/**
 * The BenchView class represents a panel that displays the bench press
 * mini-game in the GymLife application.
 * It extends the JPanel class and contains a button, an image label, and a
 * timer view.
 */
public class BenchView extends JPanel implements MinigamePanel {
    @Serial
    private static final long serialVersionUID = -2554575966007368L;
    private final JButton buttonMinigame = new JButton("START");
    private final JLabel characterLabel = new JLabel();
    private final JProgressBar progressBar = new JProgressBar();
    private ImageIcon characterImage;
    private final transient DimensionGetter dimensionGetter;
    private final JLabel timerView = new JLabel();
    private final JLabel backgroundLabel = new JLabel();
    private final ImageIcon backgroundImage;
    private final JLayeredPane layeredPane = new JLayeredPane();

    private final Color backgroundColorGreen = new Color(29, 110, 12);
    private final Color foregroundColorGreen = new Color(72, 253, 0);
    private final Color backgroundColorRed = new Color(142, 25, 25);
    private final Color foregroundColorRed = new Color(255, 0, 0);
    private final Color backgroundColorYellow = new Color(118, 119, 34);
    private final Color foregroundColorYellow = new Color(248, 255, 0);

    /**
     * Constructs a BenchView object with the specified controller.
     * Initializes the timer view and sets the layout of the panel.
     * Sets up the button action listener and makes the panel focusable.
     *
     * @param dimensionGetter the dimension getter
     * @param controller      the controller implementation
     */
    public BenchView(final Controller controller, final DimensionGetter dimensionGetter) {
        final int borderSize = 5;


        FontLoader.loadFont();
        this.dimensionGetter = dimensionGetter;
        this.setLayout(new BorderLayout());
        this.setSize(dimensionGetter.getScenarioDimension());

        backgroundImage = new ImageIcon("src/main/resources/images/Minigame/background.png");
        characterImage = new ImageIcon("images/Minigame/bench_press/sprite_0.png");

        progressBar.setOrientation(JProgressBar.VERTICAL);
        progressBar.setPreferredSize(new Dimension(100, dimensionGetter.getScenarioDimension().height));

        this.add(progressBar, BorderLayout.EAST);
        this.add(layeredPane, BorderLayout.CENTER);
        layeredPane.setLayout(null);

        layeredPane.setBounds(0, 0, dimensionGetter
                        .getScenarioDimension().width - progressBar.getWidth(),
                dimensionGetter.getScenarioDimension().height);


        buttonMinigame.setSize(dimensionGetter.getButtonMinigameDimension());
        buttonMinigame.setBackground(Color.GREEN);
        buttonMinigame.setBorder(new LineBorder(Color.WHITE, borderSize));


        timerView.setFont(FontLoader.getCustomFont(dimensionGetter.getSmallFontSize()));
        timerView.setForeground(Color.YELLOW);

        timerView.setBounds(10, 10,
                dimensionGetter.getTimerMinigameDimension().width,
                dimensionGetter.getTimerMinigameDimension().height);

        backgroundImage.setImage(backgroundImage.getImage()
                .getScaledInstance(dimensionGetter.getScenarioDimension().width - progressBar.getWidth(),
                        dimensionGetter.getScenarioDimension().height,
                        Image.SCALE_SMOOTH));

        progressBar.setBackground(backgroundColorGreen);
        progressBar.setForeground(foregroundColorGreen);

        backgroundLabel.setIcon(backgroundImage);
        backgroundLabel.setBounds(0, 0, dimensionGetter
                        .getScenarioDimension().width - progressBar.getWidth(),
                dimensionGetter.getScenarioDimension().height);

        characterLabel.setSize(dimensionGetter.getCharacterMinigameDimension());

        characterLabel.setLocation(dimensionGetter.getCharacterMinigamePos().width,
                dimensionGetter.getCharacterMinigamePos().height);

        characterLabel.setIcon(characterImage);

        layeredPane.add(buttonMinigame, Integer.valueOf(1));
        layeredPane.add(backgroundLabel, Integer.valueOf(0));
        layeredPane.add(characterLabel, Integer.valueOf(1));
        layeredPane.add(timerView, Integer.valueOf(1));

        setRandomPositionButton();


        buttonMinigame.addActionListener(e -> {
            controller.notifyUserAction();
            switch (controller.getMinigameState()) {
                case NOT_STARTED -> {
                    doAnimation();
                    timerView();
                }
                case RUNNING -> {
                    progressBar.setValue(progressBar.getValue() + controller.getDifficulty().getProgress());
                }
                case REP_REACHED -> {
                    progressBar.setValue(0);
                    doAnimation();
                }
                case MISTAKE_MADE -> {
                    progressBar.setValue(0);
                    progressBar.setBackground(backgroundColorRed);
                    progressBar.setForeground(foregroundColorRed);
                    doAnimation();
                    progressBar.setBackground(backgroundColorGreen);
                    progressBar.setForeground(foregroundColorGreen);
                }
                case ENDED_WON, ENDED_LOST -> this.setVisible(false);
                default -> {
                }
            }
            progressBarHandler();
            setRandomPositionButton();
        });

        this.setFocusable(true);
        this.setVisible(true);
    }

    /**
     * Handles the progress bar color change based on the progress value.
     */
    private void progressBarHandler() {
        final int lowProgress = 33;
        final int highProgress = 66;
        if (progressBar.getValue() < lowProgress) {
            progressBar.setBackground(backgroundColorGreen);
            progressBar.setForeground(foregroundColorGreen);

        } else if (progressBar.getValue() >= lowProgress && progressBar.getValue() < highProgress) {
            progressBar.setBackground(backgroundColorYellow);
            progressBar.setForeground(foregroundColorYellow);
        } else if (progressBar.getValue() >= highProgress) {
            progressBar.setBackground(backgroundColorRed);
            progressBar.setForeground(foregroundColorRed);
        }
    }

    /**
     * Sets a random position for the button within the panel.
     */
    private void setRandomPositionButton() {
        int x, y;
        do {
            x = (int) (Math.random() * (dimensionGetter.getScenarioDimension().width
                    - buttonMinigame.getWidth()
                    - progressBar.getWidth()));
            y = (int) (Math.random() * (dimensionGetter.getScenarioDimension().height
                    - buttonMinigame.getHeight()));
        } while (characterLabel.getBounds()
                .intersects(new Rectangle(x, y, buttonMinigame.getWidth(), buttonMinigame.getHeight())));
        this.buttonMinigame.setBounds(x, y, buttonMinigame.getWidth(), buttonMinigame.getHeight());
    }

    /**
     * Performs the animation of the character.
     */
    @Override
    public void doAnimation() {
        new Thread(() -> {
            buttonMinigame.setEnabled(false);
            buttonMinigame.setBackground(Color.RED);
            buttonMinigame.setText("WAIT...");
            for (int state = 3; state >= 0; state--) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }
                characterImage = getCharacterImage("images/Minigame/bench_press/sprite_" + state + ".png");
                characterLabel.setIcon(characterImage);
            }
            buttonMinigame.setText("Press me!");
            buttonMinigame.setEnabled(true);
            buttonMinigame.setBackground(Color.GREEN);
        }).start();
    }

    /**
     * Updates the timer view.
     */
    @Override
    public void timerView() {
        new Thread(() -> {
            int i = 0;
            while (true) {
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
     * Returns the image icon of the character.
     *
     * @param path the path of the image
     * @return an ImageIcon of the character
     */
    @Override
    public ImageIcon getCharacterImage(final String path) {
        return new ImageIcon(new ImageIcon(ClassLoader
                .getSystemResource(path))
                .getImage()
                .getScaledInstance(dimensionGetter.getCharacterMinigameDimension().width,
                        dimensionGetter.getCharacterMinigameDimension().height,
                        Image.SCALE_DEFAULT));
    }

    /**
     * Resizes the components of the panel based on the current dimension.
     */
    @Override
    public void resizeComponents() {
        this.setSize(dimensionGetter.getScenarioDimension());
        progressBar.setPreferredSize(new Dimension(100, dimensionGetter.getScenarioDimension().height));
        progressBar.setSize(100, dimensionGetter.getScenarioDimension().height);

        characterLabel.setSize(dimensionGetter.getCharacterMinigameDimension());
        characterLabel.setLocation(dimensionGetter
                        .getCharacterMinigamePos().width,
                dimensionGetter.getCharacterMinigamePos().height);
        characterImage.setImage(characterImage.getImage()
                .getScaledInstance(dimensionGetter.getCharacterMinigameDimension().width,
                        dimensionGetter.getCharacterMinigameDimension().height, Image.SCALE_DEFAULT));
        characterLabel.setIcon(characterImage);

        backgroundLabel.setSize(dimensionGetter.getScenarioDimension().width - progressBar.getWidth(),
                dimensionGetter.getScenarioDimension().height);
        backgroundImage.setImage(backgroundImage.getImage()
                .getScaledInstance(dimensionGetter.getScenarioDimension().width - progressBar.getWidth(),
                        dimensionGetter.getScenarioDimension().height, Image.SCALE_DEFAULT));
        backgroundLabel.setIcon(backgroundImage);


        FontLoader.loadFont();

        buttonMinigame.setSize(dimensionGetter.getButtonMinigameDimension());

        if (!buttonMinigame.getBounds().intersects(layeredPane.getBounds())
                || buttonMinigame.getBounds().intersects(progressBar.getBounds())) {
            setRandomPositionButton();
        }

        timerView.setFont(FontLoader.getCustomFont(dimensionGetter.getSmallFontSize()));

        timerView.setSize(dimensionGetter.getTimerMinigameDimension());
    }
}
