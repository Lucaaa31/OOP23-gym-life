package gymlife.view.minigame;

import gymlife.controller.api.Controller;
import gymlife.utility.FontLoader;
import gymlife.view.DimensionGetter;
import gymlife.view.api.MinigamePanel;

import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

/**
 * The LatMachineView class represents the view component for the Lat Machine mini-game.
 */
public class LatView extends MinigameView implements MinigamePanel {
    @Serial
    private static final long serialVersionUID = -2554575966007368L;
    private final transient Controller controller;
    private final transient DimensionGetter dimensionGetter;
    private final List<JButton> buttons = new ArrayList<>();



    /**
     * Constructs a LatMachineView object with the specified controller and dimension getter.
     * Initializes the buttons and sets the layout of the panel.
     * Sets up the button action listeners and makes the panel focusable.
     *
     * @param controller      the controller implementation
     * @param dimensionGetter the dimension getter
     */
    public LatView(final Controller controller, final DimensionGetter dimensionGetter) {
        super(controller, dimensionGetter, "lat_pulldown");
        this.controller = controller;
        this.dimensionGetter = dimensionGetter;
        final int borderSize = 5;


        final JButton button1 = new JButton("1");
        final JButton button2 = new JButton("2");
        final JButton button3 = new JButton("3");
        final JButton button4 = new JButton("4");

        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        buttons.add(button4);


        for (final JButton button : buttons) {
            button.setBackground(Color.GRAY);
            button.setBorder(new LineBorder(Color.WHITE, borderSize));
            button.setOpaque(true);
            button.setVisible(true);
            addLayeredPanel(button);
            button.setFont(FontLoader.getCustomFont(dimensionGetter.getSmallFontSize()));
            button.setForeground(Color.WHITE);
            buildButton(button);

            button.addActionListener(e -> {
                controller.notifyUserAction(button.getText());
                progressBarHandler();
                super.handleMinigameState();
            });

        }

    }


    /**
     * Performs the animation for the Lat Machine mini-game.
     */
    @Override
    public void doAnimation() {
        new Thread(() -> {
            buttons.forEach(e -> {
                e.setEnabled(false);
                e.setBackground(Color.DARK_GRAY);
            });
            for (int state = 3; state >= 0; state--) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }
                setCharacterLabelIcon(super.getCharacterImage("images/minigame/lat_pulldown/sprite_" + state + ".png"));

            }
            memoryAnimation();
        }).start();

    }

    /**
     * Performs the animation for the memory sequence.
     */
    private void memoryAnimation() {
        new Thread(() -> {
            final List<Integer> tmpList = new ArrayList<>();
            final int sleepTime = 500;
            for (final String s : controller.getSequence()) {
                tmpList.add(Integer.parseInt(s));
            }

            buttons.forEach(e -> e.setEnabled(false));
            for (int i = 0; i < controller.getDifficulty().getTouchForLift(); i++) {
                buttons.get(tmpList.get(i) - 1).setBackground(Color.MAGENTA);
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException ignored) {
                }
                buttons.get(tmpList.get(i) - 1).setBackground(Color.DARK_GRAY);
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException ignored) {
                }
            }
            buttons.forEach(e -> e.setEnabled(true));
        }).start();
    }

    private void buildButton(final JButton button) {
        button.setSize(dimensionGetter.getButtonMinigameDimension());
        switch (button.getText()) {
            case "1" -> button.setLocation(
                    (dimensionGetter.getMinigameScenarioWidht() - button.getWidth()) / 2, // Center horizontally
                    0); // Top (up)
            case "2" -> button.setLocation(
                    dimensionGetter.getMinigameScenarioWidht() - button.getWidth(), // Right
                    (dimensionGetter.getScenarioDimension().height - button.getHeight()) / 2); // Center vertically
            case "3" -> button.setLocation(
                    (dimensionGetter.getMinigameScenarioWidht() - button.getWidth()) / 2, // Center horizontally
                    dimensionGetter.getScenarioDimension().height - button.getHeight()); // Bottom
            case "4" -> button.setLocation(
                    0, // Left
                    (dimensionGetter.getScenarioDimension().height - button.getHeight()) / 2); // Center vertically
            default -> {
            }
        }
    }

    /**
     * Resizes the buttons of the Lat Machine mini-game.
     */
    @Override
    public void resizeComponents() {
        super.resizeComponents();
        buttons.forEach(this::buildButton);
    }

}
