package gymlife.view.minigame;

import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serial;

import gymlife.controller.api.Controller;
import gymlife.utility.DimensionGetter;
import gymlife.view.api.MinigamePanel;

/**
 * The BenchView class represents a panel that displays the bench press
 * mini-game in the GymLife application.
 * It extends the JPanel class and contains a button, an image label, and a
 * timer view.
 */
public class BenchView extends AbstractMinigameView implements MinigamePanel {
    @Serial
    private static final long serialVersionUID = -2554575966007368L;
    private final JButton buttonMinigame = new JButton("START");
    private final transient DimensionGetter dimensionGetter;

    /**
     * Constructs a BenchView object with the specified controller.
     * Initializes the timer view and sets the layout of the panel.
     * Sets up the button action listener and makes the panel focusable.
     *
     * @param dimensionGetter the dimension getter
     * @param controller      the controller implementation
     */
    public BenchView(final Controller controller, final DimensionGetter dimensionGetter) {
        super(controller, dimensionGetter, "bench_press");
        this.dimensionGetter = dimensionGetter;
        final int borderSize = 5;

        buttonMinigame.setSize(dimensionGetter.getButtonMinigameDimension());
        buttonMinigame.setBackground(Color.GREEN);
        buttonMinigame.setBorder(new LineBorder(Color.WHITE, borderSize));


        addLayeredPanel(buttonMinigame);
        buttonMinigame.setLocation(
                dimensionGetter.getScenarioDimension().width / 2 - buttonMinigame.getWidth(),
                dimensionGetter.getScenarioDimension().height / 2 - buttonMinigame.getHeight());


        buttonMinigame.addActionListener(e -> {
            controller.notifyUserAction("0");
            super.handleMinigameState();
            super.progressBarHandler();
            buttonMinigame.setLocation(getRandomPositionButton(buttonMinigame));
            buttonMinigame.setOpaque(true);
        });
        this.setFocusable(true);
        this.setVisible(true);
    }

    /**
     * Animates the character image by changing the sprite every second.
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
                setCharacterLabelIcon(super.getCharacterImage("images/Minigame/bench_press/sprite_" + state + ".png"));
            }
            buttonMinigame.setText("Press me!");
            buttonMinigame.setEnabled(true);
            buttonMinigame.setBackground(Color.GREEN);
        }).start();
    }

    /**
     * Method that sets the position of a button in a random position.
     *
     * @param buttonMinigame the button to set the position.
     * @return the position of the button.
     */
    public Point getRandomPositionButton(final JButton buttonMinigame) {
        int x, y;
        do {
            x = (int) (Math.random() * (dimensionGetter.getMinigameScenarioWidht()
                    - buttonMinigame.getWidth()));
            y = (int) (Math.random() * (dimensionGetter.getScenarioDimension().height
                    - buttonMinigame.getHeight()));
        } while (limits(x, y, buttonMinigame));
        return new Point(x, y);
    }

    /**
     * Method that check if the button is out of bounds.
     *
     * @param x              the x position.
     * @param y              the y position.
     * @param buttonMinigame the button to set the position.
     * @return the height of the progressBar.
     */
    public boolean limits(final int x, final int y, final JButton buttonMinigame) {
        return getCharacterLabelDimension().getBounds()
                .intersects(new Rectangle(x, y, buttonMinigame.getWidth(), buttonMinigame.getHeight()));
    }


    /**
     * Resizes the components of the panel.
     */
    @Override
    public void resizeComponents() {
        super.resizeComponents();
        buttonMinigame.setSize(dimensionGetter.getButtonMinigameDimension());
    }

}


