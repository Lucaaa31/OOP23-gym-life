package gymlife.view.bankgame;

import javax.swing.JLayeredPane;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class handles the animations of the plane in the game.
 */
public class PlaneAnimationView {

    private final Timer animationTimerStart;
    private final Timer animationTimerUpDown;
    private final Timer animationTimerExit;
    private int planeTargetX;
    private int planeTargetY;
    private boolean flagUpDownAnimation;
    private static final int PLANE_SPEED = 50;

    /**
     * Constructor to initialize the timers for the plane animations.
     */
    public PlaneAnimationView() {
        final int timerDelay = 10;
        animationTimerStart = new Timer(timerDelay, null);
        animationTimerUpDown = new Timer(timerDelay, null);
        animationTimerExit = new Timer(timerDelay, null);
    }

    /**
     * Starts the animation of the plane moving from outside the screen to the center.
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

        for (ActionListener listener : animationTimerStart.getActionListeners()) {
            animationTimerStart.removeActionListener(listener);
        }

        animationTimerStart.addActionListener(new ActionListener() {
            private int x = startX;

            @Override
            public void actionPerformed(final ActionEvent e) {
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

    /**
     * Starts the up and down animation of the plane.
     *
     * @param planeLayer The plane image label.
     */
    public void planeUpDownAnimation(final ImageLabelView planeLayer) {
        final int initialY = planeLayer.getY();
        final int amplitude = 20;
        final int frequency = 2;
        final double stepTime = 0.06;

        for (ActionListener listener : animationTimerUpDown.getActionListeners()) {
            animationTimerUpDown.removeActionListener(listener);
        }

        animationTimerUpDown.addActionListener(new ActionListener() {
            private int time;

            @Override
            public void actionPerformed(final ActionEvent e) {
                final int y = (int) (initialY + amplitude * Math.sin(frequency * time * stepTime));
                if (!flagUpDownAnimation) {
                    animationTimerUpDown.stop();
                }
                planeLayer.setLocation(planeLayer.getX(), y);
                time++;
            }
        });
        animationTimerUpDown.start();
    }

    /**
     * Stops the up and down animation of the plane.
     */
    public void stopUpDownAnimation() {
        flagUpDownAnimation = false;
    }

    /**
     * Starts the animation of the plane exiting the screen.
     *
     * @param layeredPane The parent layered pane.
     * @param planeLayer  The plane image label.
     */
    public void planeExitAnimation(final JLayeredPane layeredPane, final ImageLabelView planeLayer) {
        final int planeTargetX = layeredPane.getWidth();
        final int planeTargetY = planeLayer.getY();

        for (ActionListener listener : animationTimerExit.getActionListeners()) {
            animationTimerExit.removeActionListener(listener);
        }

        animationTimerExit.addActionListener(new ActionListener() {
            private int x = planeLayer.getX();

            @Override
            public void actionPerformed(final ActionEvent e) {
                if (x < planeTargetX) {
                    x += PLANE_SPEED;
                    planeLayer.setLocation(x, planeTargetY);
                } else {
                    animationTimerExit.stop();
                }
            }
        });
        animationTimerExit.start();
    }
}

