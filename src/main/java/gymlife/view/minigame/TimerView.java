package gymlife.view.minigame;

import java.awt.Color;

import javax.swing.JLabel;

import gymlife.controller.ControllerImpl;
import gymlife.controller.api.Controller;

/**
 * The TimerView class represents a JPanel that displays a timer.
 * It updates the timer labels based on the time received from the controller.
 */
public class TimerView extends JLabel {

    // private JLabel label1 = new JLabel("");
    private Controller controller;

    /**
     * Constructs a TimerView object with the specified controller.
     * 
     * @param controller the controller object used to get the time
     */
    public TimerView(final ControllerImpl controller) {
        this.controller = controller;

        setBackground(Color.PINK);
        this.setSize(200, 100);
        // this.setLayout();

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {

                }
                if (controller.getVisibilityTimer()) {
                    System.out.println("START kmnk n");
                    startTimer();
                    break;
                }
            }
        }).start();

    }

    /**
     * Updates the timer label based on the time received from the controller.
     */
    public void startTimer() {
        while (controller.getVisibilityTimer()) {
            this.setText(String.format("%d:%02d", controller.getTime() / 100, controller.getTime() % 100));
        }

    }

}
