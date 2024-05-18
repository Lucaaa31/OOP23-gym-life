package gymlife.view.minigame;

import gymlife.controller.api.Controller;
import gymlife.utility.FontLoader;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
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
        this.setLayout(new BorderLayout());
        FontLoader.loadFont();
        JLabel endLabel = new JLabel(controller.getMinigameState().getText());
        JButton endButton = new JButton("return to the gym");

        endLabel.setFont(new Font("Arial", Font.BOLD, 30));
        endButton.setFont(new Font("Arial", Font.BOLD, 30));

        endLabel.setBounds(0, 0, 100, 100);
        endButton.setBounds(0, 100, 100, 100);

        endButton.addActionListener(e -> {
                    controller.setMinigameResult();
                    endButton.setEnabled(false);
                });


        this.add(endLabel);
        this.add(endButton);
        this.setVisible(true);
    }
}
