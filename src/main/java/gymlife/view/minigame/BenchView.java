package gymlife.view.minigame;


import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JProgressBar;
import java.awt.*;
import javax.swing.ImageIcon;
import java.io.Serial;

import gymlife.controller.api.Controller;
import gymlife.utility.FontLoader;
import gymlife.view.DimensionGetter;

/**
 * The BenchView class represents a panel that displays the bench press
 * mini-game in the GymLife application.
 * It extends the JPanel class and contains a button, an image label, and a
 * timer view.
 */
public class BenchView extends JPanel {
    @Serial
    private static final long serialVersionUID = -2554575966007368L;
    private final JButton buttonMinigame = new JButton("Press me");
    private final JLabel characterLabel = new JLabel();
    private final transient Controller controller;
    private final JLayeredPane layeredPane = new JLayeredPane();
    private final JLabel backgroundLabel = new JLabel();
    private final JProgressBar progressBar = new JProgressBar();

    private final ImageIcon backgroundImage;
    private ImageIcon characterImage;
    private final DimensionGetter dimensionGetter;

    private JLabel timerView = new JLabel();

    /**
     * Constructs a BenchView object with the specified controller.
     * Initializes the timer view and sets the layout of the panel.
     * Sets up the button action listener and makes the panel focusable.
     *
     * @param controller the controller implementation
     */
    public BenchView(final Controller controller, final DimensionGetter dimensionGetter) {
        this.controller = controller;
        this.dimensionGetter = dimensionGetter;
        this.setLayout(new BorderLayout());

        backgroundImage = new ImageIcon("src/main/resources/images/Minigame/background.png");
        characterImage = new ImageIcon("src/main/resources/images/Minigame/bench_press/sprite_0.png");

        this.setSize(dimensionGetter.getScenarioDimension());

        progressBar.setOrientation(JProgressBar.VERTICAL);
        progressBar.setPreferredSize(new Dimension(100, dimensionGetter.getScenarioDimension().height));

        this.add(progressBar, BorderLayout.EAST);
        this.add(layeredPane, BorderLayout.CENTER);
        layeredPane.setLayout(null);

        layeredPane.setBounds(0, 0, dimensionGetter.getScenarioDimension().width - 100,
                dimensionGetter.getScenarioDimension().height);

        FontLoader.loadFont();

        buttonMinigame.setSize(100, 100);
        //buttonMinigame.setFont(FontLoader.getCustomFont(dimensionGetter.getSmallFontSize()));
        buttonMinigame.setBackground(Color.GREEN);
        setRandomPositionButton();


        timerView.setBounds(0, 0, 130, 50);
        timerView.setFont(FontLoader.getCustomFont(dimensionGetter.getSmallFontSize()));
        timerView.setForeground(Color.YELLOW);




        backgroundImage.setImage(backgroundImage.getImage()
                .getScaledInstance(dimensionGetter.
                                getScenarioDimension().width - 100,
                        dimensionGetter.getScenarioDimension().height,
                        Image.SCALE_SMOOTH));


        progressBar.setBackground(Color.WHITE);
        progressBar.setForeground(Color.BLUE);


        backgroundLabel.setIcon(backgroundImage);
        backgroundLabel.setBounds(0, 0,
                dimensionGetter.getScenarioDimension().width - 100,
                dimensionGetter.getScenarioDimension().height);

        characterLabel.setBounds(300, 300, 320, 320);
        characterLabel.setLayout(new BorderLayout());
        characterLabel.setIcon(characterImage);

        layeredPane.add(buttonMinigame, 0);
        layeredPane.add(backgroundLabel, 1);
        layeredPane.add(characterLabel, 0);
        layeredPane.add(timerView, 2);

        timerView();

        doAnimation();

        this.setBackground(Color.BLACK);

        buttonMinigame.addActionListener(e -> {
            if (controller.getDifficulty() != null){
                progressBar.setValue(progressBar.getValue() + 10);
                controller.notifyUserAction();
                if (controller.isRepsDone()) {
                    doAnimation();
                }
                if (controller.isMinigameEnded()){
                    this.removeAll();
                    this.add(new MinigameEndView(controller));
                }
                setRandomPositionButton();

            }
        });


        this.setFocusable(true);
        this.setVisible(true);
    }


    /**
     * Sets a random position for the button within the panel.
     */
    private void setRandomPositionButton() {
        final int x = (int) (Math.random() * Math.abs(dimensionGetter.getScenarioDimension().width - buttonMinigame.getWidth()));
        final int y = (int) (Math.random() * Math.abs(dimensionGetter.getScenarioDimension().height - buttonMinigame.getHeight()));
        this.buttonMinigame.setBounds(x, y, buttonMinigame.getWidth(), buttonMinigame.getHeight());
    }

    /**
     * Performs the animation of the character.
     */
    private void doAnimation() {
        new Thread(() -> {
            buttonMinigame.setEnabled(false);
            buttonMinigame.setBackground(Color.RED);
            buttonMinigame.setText("WAIT...");
            for (int state = 3; state >= 0; state--) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }
                characterImage = new ImageIcon("src/main/resources/images/Minigame/bench_press/sprite_" + state + ".png");
                characterLabel.setIcon(characterImage);
            }
            buttonMinigame.setText("Press me!");
            buttonMinigame.setEnabled(true);
            buttonMinigame.setBackground(Color.GREEN);
        }).start();
    }

    private void timerView() {
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




}
