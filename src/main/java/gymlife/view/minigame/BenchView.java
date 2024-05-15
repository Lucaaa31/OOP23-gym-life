package gymlife.view.minigame;


import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JProgressBar;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import javax.swing.ImageIcon;
import java.io.Serial;

import gymlife.controller.api.Controller;
import gymlife.utility.Constants;
import gymlife.utility.minigame.MinigameType;

/**
 * The BenchView class represents a panel that displays the bench press
 * mini-game in the GymLife application.
 * It extends the JPanel class and contains a button, an image label, and a
 * timer view.
 */
public class BenchView extends JPanel {
    @Serial
    private static final long serialVersionUID = -2554575966007368L;
    private final JButton buttonMinigame = new JButton("Press me!");
    private final JLabel characterLabel = new JLabel();
    private final transient Controller controller;
    private final JLayeredPane layeredPane = new JLayeredPane();
    private final JLabel backgroundLabel = new JLabel();
    private final JProgressBar progressBar = new JProgressBar();

    private final ImageIcon backgroundImage;
    private ImageIcon characterImage;

    /**
     * Constructs a BenchView object with the specified controller.
     * Initializes the timer view and sets the layout of the panel.
     * Sets up the button action listener and makes the panel focusable.
     *
     * @param controller the controller implementation
     */
    public BenchView(final Controller controller) {

        this.controller = controller;
        // Set up the layout
        this.setLayout(null);

        // Set up the progress bar
//        progressBar.setOrientation(JProgressBar.VERTICAL);
//        progressBar.setBounds(1000, 0, 50, Constants.HEIGHT);

        // Add components to the panel
        this.add(layeredPane);
        //this.add(progressBar, BorderLayout.EAST);
        layeredPane.setLayout(null);

        layeredPane.setBounds(0, 0, 1300, 800);


        buttonMinigame.setSize(100, 50);

        backgroundImage = new ImageIcon("src/main/resources/images/Minigame/background.png");
        characterImage = new ImageIcon("src/main/resources/images/Minigame/bench_press/sprite_0.png");

        backgroundImage.setImage(backgroundImage.getImage().getScaledInstance(1000, 800, Image.SCALE_DEFAULT));

        backgroundLabel.setIcon(backgroundImage);
        backgroundLabel.setBounds(0, 0, 1000, 1000);

        characterLabel.setBounds(0, 0, Constants.SCENARIO_WIDTH, Constants.HEIGHT);
        characterLabel.setLayout(new BorderLayout());
        characterLabel.setIcon(characterImage);

        layeredPane.add(buttonMinigame, 0);
        layeredPane.add(backgroundLabel, 1);
        layeredPane.add(characterLabel, 0);

        doAnimation();

        this.setBackground(Color.BLACK);

        buttonMinigame.addActionListener(e -> {
            controller.notifyUserAction();
            if (controller.isRepsDone()) {
                doAnimation();
            }
            setRandomPositionButton();
            //progressBar.setValue(progressBar.getValue() + 100);kk

        });


        this.setFocusable(true);
        this.setVisible(true);
    }


    /**
     * Sets a random position for the button within the panel.
     */
    private void setRandomPositionButton() {
        final int x = (int) (Math.random() * 100);
        final int y = (int) (Math.random() * 200);
        this.buttonMinigame.setBounds(x, y, buttonMinigame.getWidth(), buttonMinigame.getHeight());
    }

    /**
     * Performs the animation of the character.
     */
    private void doAnimation() {
        new Thread(() -> {
            buttonMinigame.setEnabled(false);
            for (int state = 3; state >= 0; state--) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }
                characterImage = new ImageIcon("src/main/resources/images/Minigame/bench_press/sprite_" + state + ".png");
                characterLabel.setIcon(characterImage);
            }
            buttonMinigame.setEnabled(true);
        }).start();
    }

//    private void timerView() {
//        new Thread(() -> {
//            while (controller.getState() != -1) {
//                timerView.setText(String.format("%3d:%02d", controller.getTime() / 1000, controller.getTime() % 1000));
//            }
//        }).start();
//    }


}
