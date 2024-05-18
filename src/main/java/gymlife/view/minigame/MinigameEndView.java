package gymlife.view.minigame;

import gymlife.controller.api.Controller;
import gymlife.utility.FontLoader;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


/**
 * The MinigameEndView class represents the view displayed at the end of a minigame.
 * It extends the JPanel class and provides methods to display the end of a minigame.
 */
public class MinigameEndView extends JPanel {

    JLabel endLabel = new JLabel();
    JButton endButton = new JButton("return to the gym");
    private final Controller controller;

    /**
     * Create a new MinigameEndView object.
     *
     * @param controller the controller to use
     */
    public MinigameEndView(final Controller controller) {
        this.controller = controller;
        this.setLayout(new GridLayout(2, 1));
        FontLoader.loadFont();


        endLabel.setFont(new Font("Arial", Font.BOLD, 30));
        endButton.setFont(new Font("Arial", Font.BOLD, 30));

        endLabel.setBounds(0, 0, 100, 100);
        endButton.setBounds(0, 100, 100, 100);

        endButton.addActionListener(e -> {
            controller.setMinigameResult();
            this.setVisible(false);
        });

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                endLabel.setText(controller.getMinigameState().getText());
            }
        });

        this.setVisible(true);

        this.add(endLabel);
        this.add(endButton);
    }



}
