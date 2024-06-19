package gymlife.view.minigame;


import gymlife.controller.api.Controller;
import gymlife.utility.FontLoader;
import gymlife.utility.minigame.Colors;
import gymlife.view.DimensionGetter;
import gymlife.view.api.MinigamePanel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.Serial;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * The SquatView class represents the view component for the Squat mini-game.
 * It extends the JPanel class and provides the necessary UI elements and
 * functionality
 * for the Squat mini-game.
 */
public class SquatView extends AbstractMinigameView implements MinigamePanel {
    @Serial
    private static final long serialVersionUID = -5624416626690898281L;
    private final transient DimensionGetter dimensionGetter;
    private final transient Controller controller;
    private final JButton buttonMinigame1 = new JButton("Start!");
    private final JButton buttonMinigame2 = new JButton("Start!");
    private final JButton buttonMinigame3 = new JButton("Start!");
    private final List<JButton> buttonList = new ArrayList<>(
            List.of(buttonMinigame1, buttonMinigame2, buttonMinigame3));
    private String actualColor;

    /**
     * Creates a new SquatView object.
     *
     * @param controller the controller for the mini-game
     */
    public SquatView(final Controller controller, final DimensionGetter dimensionGetter) {
        super(controller, dimensionGetter, "squat");
        this.dimensionGetter = dimensionGetter;
        this.controller = controller;
        final int borderSize = 5;
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 3));


        for (JButton button : buttonList) {
            button.setSize(dimensionGetter.getButtonMinigameDimension());
            button.setBackground(Color.GREEN);
            button.setBorder(new LineBorder(Color.BLACK, borderSize));
            button.setFont(FontLoader.getCustomFont(25));
            button.setOpaque(true);
            button.addActionListener(e -> {
                controller.notifyUserAction(Objects.requireNonNull(Colors.getColorName(
                        button.getBackground().getRed(),
                        button.getBackground().getGreen(),
                        button.getBackground().getBlue()
                )).getColorName());
                super.progressBarHandler();
                handleMinigameState();
            });
            panel.add(button);
        }
        panel.setBounds(dimensionGetter.getMinigameScenarioWidht() / 2
                        - buttonMinigame1.getWidth() * 3 / 2
                        + 25,
                100,
                buttonMinigame1.getWidth() * 3,
                buttonMinigame1.getHeight());

        addLayeredPanel(panel);

    }

    @Override
    public void handleMinigameState() {
        super.handleMinigameState();
        final List<String> sequence = controller.getSequence();
        actualColor = sequence.get(0);
        final Color color = Objects.requireNonNull(Colors.getColor(sequence.get(1))).getColor();
        Collections.shuffle(sequence);
        for (int i = 0; i < 3; i++) {
            buttonList.get(i).setBackground(Objects.requireNonNull(
                            Colors.getColor(controller.getSequence().get(i)))
                    .getColor());
            buttonList.get(i).setText("?");
        }

        super.setText("Press the " + actualColor + " button!", color);
    }

    public void doAnimation() {
        new Thread(() -> {
            buttonList.forEach(button -> {
                button.setEnabled(false);
            });
            for (int state = 3; state >= 0; state--) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }
                setCharacterLabelIcon(super.getCharacterImage("images/Minigame/squat/sprite_" + state + ".png"));
            }
            buttonList.forEach(button -> {
                button.setEnabled(true);
            });
        }).start();
    }

    /**
     * Resizes the components of the view.
     */
    public void resizeComponents() {
        super.resizeComponents();
        buttonList.forEach(button -> button.setSize(dimensionGetter.getButtonMinigameDimension()));
    }
}
