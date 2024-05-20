package gymlife.view.bankgame;

import javax.swing.JLayeredPane;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class handles the animation of the plane moving from outside the screen
 * to the center.
 */
public class PlaneAnimationView {

    private Timer animationTimerStart;
    private Timer animationTimerUpDown;
    private Timer animationTimerExit;
    private int planeTargetX;
    private int planeTargetY;
    boolean flagUpDownAnimation;

    public PlaneAnimationView() {
        animationTimerStart = new Timer(10, null);
        animationTimerUpDown = new Timer(10, null);
        animationTimerExit = new Timer(10, null);
    }

    /**
     * Starts the animation of the plane moving from outside the screen to the
     * center.
     *
     * @param layeredPane The parent layered pane.
     * @param planeLayer  The plane image label.
     */
    public void startPlaneAnimation(final JLayeredPane layeredPane, final ImageLabelView planeLayer) {
        flagUpDownAnimation = true;
        final int startX = -planeLayer.getWidth();
        final int startY = layeredPane.getHeight() / 2 - planeLayer.getHeight() / 2;

        planeTargetX = layeredPane.getWidth() / 2 - planeLayer.getWidth() / 2;
        planeTargetY = startY;

        planeLayer.setLocation(startX, startY);

        animationTimerStart.addActionListener(new ActionListener() {
            int x = startX;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (x < planeTargetX) {
                    x += 10;
                    planeLayer.setLocation(x, planeTargetY);
                } else {
                    animationTimerStart.stop();
                }
            }
        });
        animationTimerStart.start();
    }

    public void planeUpDownAnimation(final ImageLabelView planeLayer) {
        final int initialY = planeLayer.getY();
        final int amplitude = 20;
        final int frequency = 2;

        animationTimerUpDown.addActionListener(new ActionListener() {
            int time = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                int y = (int) (initialY + amplitude * Math.sin(frequency * time * 0.06));
                if (flagUpDownAnimation == false) {
                    ((Timer) e.getSource()).stop();
                }
                planeLayer.setLocation(planeLayer.getX(), y);
                time++;
            }
        });
        animationTimerUpDown.start();
    }

    public void stopUpDownAnimation() {
        flagUpDownAnimation = false;
    }

    public void planeExitAnimation(final JLayeredPane layeredPane, final ImageLabelView planeLayer) {
        final int planeTargetX = layeredPane.getWidth();
        final int planeTargetY = planeLayer.getY();

        animationTimerExit.addActionListener(new ActionListener() {
            int x = planeLayer.getX();

            @Override
            public void actionPerformed(ActionEvent e) {
                if (x < planeTargetX) {
                    x += 50;
                    planeLayer.setLocation(x, planeTargetY);
                } else {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        animationTimerExit.start();
    }
}
