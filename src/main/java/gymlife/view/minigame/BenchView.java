package gymlife.view.minigame;


import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JLayeredPane;
import javax.swing.ImageIcon;
import java.awt.Dimension;

import gymlife.controller.api.Controller;
import gymlife.utility.Constants;

/**
 * The BenchView class represents a panel that displays the bench press
 * mini-game in the GymLife application.
 * It extends the JPanel class and contains a button, an image label, and a
 * timer view.
 */
public class BenchView extends JPanel {
    private final JButton button = new JButton("Press me!");
    private final JLabel label = new JLabel();
    private final JLabel timerView = new JLabel();
    private final transient Controller controller;

    private final JLayeredPane layeredPane = new JLayeredPane();
    private final JLabel backgroundLabel = new JLabel();

    /**
     * Constructs a BenchView object with the specified controller.
     * Initializes the timer view and sets the layout of the panel.
     * Sets up the button action listener and makes the panel focusable.
     *
     * @param controller the controller implementation
     */
    public BenchView(final Controller controller) {
        this.controller = controller;
        //this.setBackground(Color.CYAN);

        //this.setLayout(null);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        updateImage(controller.getState());
        label.setPreferredSize(new Dimension(Constants.SCENARIO_WIDTH, Constants.HEIGHT));
        button.addActionListener(e -> {
            controller.notifyButtonPressed();
            updateImage(controller.getState());
        });
        timerView();

        this.setFocusable(true);
        this.setVisible(true);
    }

    /**
     * Updates the image displayed on the panel based on the current state.
     * Sets the image icon, layout, and position of the button.
     * Adds the button, timer view, and image label to the panel.
     * Repaints the panel to reflect the changes.
     *
     * @param state the current state of the mini-game
     */
    private void updateImage(final int state) {
//        ImageIcon background = new ImageIcon(Objects
//                .requireNonNull(getClass()
//                        .getResource("/images/Minigame/bench_press/gymBackground.png")));


        ImageIcon image = new ImageIcon("src/main/resources/images/Minigame/bench_press/sprite_"
                + state + ".png");

        //this.backgroundLabel.setIcon(background);
        this.label.setIcon(image);

        //image.setImage(image.getImage()
        // .getScaledInstance(Constants.FRAME_WIDTH, Constants.HEIGHT, Image.SCALE_SMOOTH));
        //background.setImage(background.getImage()
        // .getScaledInstance(Constants.FRAME_WIDTH, Constants.HEIGHT, Image.SCALE_SMOOTH));

        //this.backgroundLabel.setBounds(0, 0, Constants.FRAME_WIDTH, Constants.HEIGHT);
        //this.label.setBounds(0, 0, Constants.FRAME_WIDTH, Constants.HEIGHT);

        setRandomPositionButton();
        this.add(button);

        //timerView.setBounds(160, 50, 400, 100);
        this.label.add(timerView);

        layeredPane.add(label, 0);
        layeredPane.add(backgroundLabel, 1);


        //layeredPane.setBounds(0, 0, Constants.FRAME_WIDTH, Constants.HEIGHT);

        label.setVisible(true);
        //layeredPane.setVisible(true);


        this.add(label);
        this.setVisible(true);
        this.repaint();
    }

    /**
     * Sets a random position for the button within the panel.
     */
    private void setRandomPositionButton() {
        int x = (int) (Math.random() * Constants.FRAME_WIDTH - button.getWidth());
        int y = (int) (Math.random() * Constants.HEIGHT - button.getHeight());
        this.button.setBounds(x, y, button.getWidth(), button.getHeight());
    }

    private void timerView() {
        new Thread(() -> {
            while (true) {
                timerView.setText(String.format("%3d:%02d", controller.getTime() / 1000, controller.getTime() % 1000));
            }
        }).start();
    }

}
