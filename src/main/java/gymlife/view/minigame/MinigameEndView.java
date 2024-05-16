package gymlife.view.minigame;

import gymlife.controller.api.Controller;
import gymlife.utility.FontLoader;

import javax.swing.*;
import java.awt.*;

/**
 * The MinigameEndView class represents the view displayed at the end of a minigame.
 * It extends the JPanel class and provides methods to display the end of a minigame.
 */
public class MinigameEndView extends JPanel {

    /**
     * Create a new MinigameEndView object.
     *
     * @param controller the controller to use
     */
    public MinigameEndView(final Controller controller) {
        this.setLayout(new FlowLayout());
        FontLoader.loadFont();
        JLabel endLabel = new JLabel(controller.getMinigameResult() == -1 ? "You lost!" : "You won!");
        JButton endButton = new JButton("return to the gym");
        endButton.addActionListener(e -> controller.setMinigameResult());
        endLabel.setFont(new Font("Arial", Font.BOLD, 30));
        endLabel.setHorizontalAlignment(SwingConstants.CENTER);
        endButton.setFont(new Font("Arial", Font.BOLD, 30));

        endLabel.setBounds(0, 0, 100, 100);
        this.add(endLabel);
        this.add(endButton);
        this.setVisible(true);
    }
}
