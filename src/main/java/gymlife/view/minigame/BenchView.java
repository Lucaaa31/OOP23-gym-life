package gymlife.view.minigame;


import javax.swing.*;
import java.awt.*;
import java.io.Serial;

import gymlife.controller.api.Controller;
import gymlife.utility.FontLoader;
import gymlife.utility.minigame.MinigameState;
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
    private final JButton buttonMinigame = new JButton("Press me");
    private final JLabel characterLabel = new JLabel();
    private final JProgressBar progressBar = new JProgressBar();
    private ImageIcon characterImage;
    private final DimensionGetter dimensionGetter;
    private final JLabel timerView = new JLabel();
    private final JLabel backgroundLabel = new JLabel();
    private final ImageIcon backgroundImage;
    private final JLayeredPane layeredPane = new JLayeredPane();
    private final transient Controller controller;


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
        this.setSize(dimensionGetter.getScenarioDimension());


        backgroundImage = new ImageIcon("src/main/resources/images/Minigame/background.png");
        characterImage = getIcon("images/Minigame/bench_press/sprite_0.png");


        progressBar.setOrientation(JProgressBar.VERTICAL);
        progressBar.setPreferredSize(new Dimension(100, dimensionGetter.getScenarioDimension().height));

        this.add(progressBar, BorderLayout.EAST);
        this.add(layeredPane, BorderLayout.CENTER);
        layeredPane.setLayout(new BorderLayout());

        layeredPane.setBounds(0, 0, dimensionGetter.getScenarioDimension().width - 100,
                dimensionGetter.getScenarioDimension().height);

        FontLoader.loadFont();

        buttonMinigame.setSize(100, 100);
        buttonMinigame.setBackground(Color.GREEN);
        setRandomPositionButton();


        timerView.setLocation(0, 0);
        timerView.setFont(FontLoader.getCustomFont(dimensionGetter.getSmallFontSize()));
        timerView.setForeground(Color.YELLOW);


        backgroundImage.setImage(backgroundImage.getImage()
                .getScaledInstance(dimensionGetter.
                                getScenarioDimension().width - 100,
                        dimensionGetter.getScenarioDimension().height,
                        Image.SCALE_SMOOTH));


        progressBar.setBackground(new Color(29, 110, 12));
        progressBar.setForeground(new Color(72, 253, 0));


        backgroundLabel.setIcon(backgroundImage);
        backgroundLabel.setBounds(0, 0,
                dimensionGetter.getScenarioDimension().width - 100,
                dimensionGetter.getScenarioDimension().height);


        characterLabel.setSize(dimensionGetter.getCharacterDimension());

        characterLabel.setLocation(dimensionGetter.getCharacterMinigamePos().width,
                dimensionGetter.getCharacterMinigamePos().height);


        characterLabel.setIcon(characterImage);

        layeredPane.add(buttonMinigame, 0);
        layeredPane.add(backgroundLabel, 1);
        layeredPane.add(characterLabel, BorderLayout.CENTER, 0);
        layeredPane.add(timerView, 0);

        timerView();

        doAnimation();


        buttonMinigame.addActionListener(e -> {
            controller.notifyUserAction();
//            if(controller.checkValidity()){
//                progressBar.setValue(progressBar.getValue() + controller.getDifficulty().getProgress());
//                if (progressBar.getValue() >= 33 && progressBar.getValue() < 66) {
//                    progressBar.setBackground(new Color(165, 78, 32));
//                    progressBar.setForeground(new Color(255, 89, 0));
//                } else if (progressBar.getValue() >= 66) {
//                    progressBar.setBackground(new Color(142, 25, 25));
//                    progressBar.setForeground(new Color(255, 0, 0));
//                }
//
//                if (controller.isRepDone()) {
//                    progressBar.setValue(0);
//                    progressBar.setBackground(new Color(29, 110, 12));
//                    progressBar.setForeground(new Color(72, 253, 0));
//                    doAnimation();
//                }
//                setRandomPositionButton();
//
//            }
            switch (controller.getMinigameState()) {
                case RUNNING -> {
                    progressBar.setValue(progressBar.getValue() + controller.getDifficulty().getProgress());
                }
                case REP_REACHED -> {
                    progressBar.setValue(0);
                    progressBar.setBackground(new Color(29, 110, 12));
                    progressBar.setForeground(new Color(72, 253, 0));
                    doAnimation();
                }
                case MISTAKE_MADE -> {
                    progressBar.setValue(0);
                    progressBar.setBackground(new Color(142, 25, 25));
                    progressBar.setForeground(new Color(255, 0, 0));
                    doAnimation();
                    progressBar.setBackground(new Color(29, 110, 12));
                    progressBar.setForeground(new Color(72, 253, 0));
                }
                case ENDED_WON, ENDED_LOST -> {
                    this.setVisible(false);
                }
            }
            progressbarHandler();
            setRandomPositionButton();
        });

        this.setFocusable(true);
        this.setVisible(true);
    }

    private void progressbarHandler() {

        if(progressBar.getValue() < 33){
            progressBar.setBackground(new Color(165, 78, 32));
            progressBar.setForeground(new Color(255, 89, 0));
        }
        else if(progressBar.getValue() >= 33 && progressBar.getValue() < 66){
            progressBar.setBackground(new Color(142, 25, 25));
            progressBar.setForeground(new Color(255, 0, 0));
        }
        else if (progressBar.getValue() >= 66){
            progressBar.setValue(0);
            progressBar.setBackground(new Color(29, 110, 12));
            progressBar.setForeground(new Color(72, 253, 0));
        }


    }

    /**
     * Sets a random position for the button within the panel.
     */
    private void setRandomPositionButton() {
        int x, y;
        do {
            x = (int) (Math.random() * (dimensionGetter.getScenarioDimension().width -
                    buttonMinigame.getWidth() - progressBar.getWidth()));
            y = (int) (Math.random() * (dimensionGetter.getScenarioDimension().height));
        } while (characterLabel.getBounds()
                .intersects(new Rectangle(x, y, buttonMinigame.getWidth(), buttonMinigame.getHeight())));
        this.buttonMinigame.setBounds(x, y, buttonMinigame.getWidth(), buttonMinigame.getHeight());
    }

    /**
     * Performs the animation of the character.
     */
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
                characterImage = getIcon("images/Minigame/bench_press/sprite_" + state + ".png");
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


    public ImageIcon getIcon(final String path) {
        return new ImageIcon(new ImageIcon(ClassLoader
                .getSystemResource(path))
                .getImage()
                .getScaledInstance(dimensionGetter.getCharacterDimension().width,
                        dimensionGetter.getCharacterDimension().height,
                        Image.SCALE_FAST));
    }

    public void resizeComponents() {
        System.out.println("Resize components");
        this.setSize(dimensionGetter.getScenarioDimension());
        progressBar.setPreferredSize(new Dimension(100, dimensionGetter.getScenarioDimension().height));
        characterLabel.setSize(dimensionGetter.getCharacterDimension());
        characterLabel.setLocation(dimensionGetter.getCharacterMinigamePos().width,
                dimensionGetter.getCharacterMinigamePos().height);
        characterLabel.setIcon(characterImage);

        backgroundLabel.setSize(dimensionGetter.getScenarioDimension());
        backgroundImage.setImage(backgroundImage.getImage()
                .getScaledInstance(dimensionGetter.getScenarioDimension().width,
                        dimensionGetter.getScenarioDimension().height,
                        Image.SCALE_SMOOTH));
        backgroundLabel.setIcon(backgroundImage);
    }

}
