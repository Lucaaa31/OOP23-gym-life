package gymlife.view.minigame;


import gymlife.controller.api.Controller;
import gymlife.view.DimensionGetter;
import gymlife.view.api.MinigamePanel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

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
    private final JButton buttonMinigame1 = new JButton("1");
    private final JButton buttonMinigame2 = new JButton("2");
    private final JButton buttonMinigame3 = new JButton("3");
    private final JButton buttonMinigame4 = new JButton("4");
    private final Random random = new Random();
    final List<JButton> buttonList = new ArrayList<>(
            List.of(buttonMinigame1, buttonMinigame2, buttonMinigame3, buttonMinigame4));

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



        for (JButton button : buttonList) {
            button.setSize(dimensionGetter.getButtonMinigameDimension());
            button.setBackground(Color.GREEN);
            button.setBorder(new LineBorder(Color.WHITE, borderSize));
            addLayeredPanel(button);

            button.addActionListener(e -> {
                controller.notifyUserAction(Integer.parseInt(button.getText()));
                buttonAnimation(button);
                super.progressBarHandler();
            });
        }


        this.setFocusable(true);
        this.setVisible(true);

    }

    private void buttonAnimation(final JButton button) {
        new Timer(5, new ActionListener() {
            int y = 0;
            final int direction = 1;
            final int speed = 5;
            int x = (int) (Math.random() * (dimensionGetter.getMinigameScenarioWeight() - button.getWidth()));


            @Override
            public void actionPerformed(final ActionEvent e) {

                y += direction * speed;
                do {
                    button.setLocation(x, y);
                    System.out.println("Button: " + button.getText() + " x: " + x + " y: " + y);
                }while (buttonList.stream()
                        .anyMatch(b -> b != button && b.getBounds().intersects(button.getBounds())));

                SquatView.super.repaint(); 
            }
        }).start();
    }



    @Override
    public void doAnimation() {

    }

    /**
     * Resizes the components of the view.
     */
    public void resizeComponents() {
    }
}
