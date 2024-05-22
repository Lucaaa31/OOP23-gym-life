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
import gymlife.utility.minigame.MinigameState;
import gymlife.view.DimensionGetter;
import gymlife.view.api.MinigamePanel;

/**
 * The BenchView class represents a panel that displays the bench press
 * mini-game in the GymLife application.
 * It extends the JPanel class and contains a button, an image label, and a
 * timer view.
 */
public class BenchView extends AbstractMinigameView implements MinigamePanel{
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

        setRandomPositionButton();
        


        buttonMinigame.addActionListener(e -> {
            controller.notifyUserAction();
            switch (controller.getMinigameState()) {
                case NOT_STARTED -> {
                    doAnimation();
                    timerView();
                }
                case RUNNING -> {
                   super.setValueProgressBar(super.getValueProgressBar() + controller.getDifficulty().getProgress());
                }
                case REP_REACHED -> {
                    super.setValueProgressBar(0);
                    doAnimation();
                }
                case MISTAKE_MADE -> {
                    super.setValueProgressBar(0);
                    super.setColorBackground("backgroundColorRed");
                    super.setColorForeground("foregroundColorRed");
                    doAnimation();
                    super.setColorBackground("backgroundColorGreen");
                    super.setColorForeground("foregroundColorGreen");
                }
                case ENDED_WON, ENDED_LOST -> this.setVisible(false);
                default -> {
                }
            }
            super.progressBarHandler();
            setRandomPositionButton();
        });

        this.setFocusable(true);
        this.setVisible(true);
    }


    /**
     * Sets a random position for the button within the panel.
     */
    private void setRandomPositionButton() {
        int x, y;
        do {
            x = (int) (Math.random() * (dimensionGetter.getScenarioDimension().width
                    - buttonMinigame.getWidth()
                    - super.getWidhtProgressBar()));
            y = (int) (Math.random() * (dimensionGetter.getScenarioDimension().height
                    - buttonMinigame.getHeight()));
        } while (super.limits(x, y, buttonMinigame));
        this.buttonMinigame.setBounds(x, y, buttonMinigame.getWidth(), buttonMinigame.getHeight());
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
            super.doAnimation("bench_press");
            buttonMinigame.setText("Press me!");
            buttonMinigame.setEnabled(true);
            buttonMinigame.setBackground(Color.GREEN);
        }).start();
    }




}
