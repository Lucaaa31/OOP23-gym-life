package gymlife.view.minigame;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gymlife.controller.ControllerImpl;
import gymlife.utility.Constants;

/**
 * The BenchView class represents a panel that displays the bench press
 * mini-game in the GymLife application.
 * It extends the JPanel class and contains a button, an image label, and a
 * timer view.
 */
public class BenchView extends JPanel {
    private final JButton button = new JButton("Press me!");
    private ImageIcon image;
    private JLabel label = new JLabel();
    private TimerView timerView;

    /**
     * Constructs a BenchView object with the specified controller.
     * Initializes the timer view and sets the layout of the panel.
     * Sets up the button action listener and makes the panel focusable.
     * 
     * @param controller the controller implementation
     */
    public BenchView(final ControllerImpl controller) {
        timerView = new TimerView(controller);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        updateImage(controller.getState());
        label.setPreferredSize(new Dimension(Constants.SCENARIO_WIDTH, Constants.HEIGHT));
        button.addActionListener(e -> {
            controller.notifyButtonPressed();
            updateImage(controller.getState());
        });
        this.setFocusable(true);
        this.requestFocusInWindow();
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
        this.image = new ImageIcon("src/main/resources/images/Minigame/bench_press/sprite_" + state + ".png");
        this.label.setIcon(image);
        this.label.setLayout(new FlowLayout());
        setRandomPositionButton();
        this.add(button);
        this.label.add(timerView, BoxLayout.X_AXIS);
        this.add(label);
        this.repaint();
    }

    /**
     * Sets a random position for the button within the panel.
     */
    private void setRandomPositionButton() {
        int x = (int) (Math.random() * 250);
        int y = (int) (Math.random() * 300);
        this.button.setBounds(x, y, button.getWidth(), button.getHeight());
    }
}
