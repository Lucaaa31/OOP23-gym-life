package gymlife.view.minigame;

import gymlife.controller.api.Controller;
import gymlife.utility.FontLoader;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.Serial;


/**
 * The MinigameEndView class represents the view displayed at the end of a minigame.
 * It extends the JPanel class and provides methods to display the end of a minigame.
 */
public class MinigameEndView extends JPanel {
    @Serial
    private static final long serialVersionUID = 3580983973396473130L;
    private final JLabel endLabel = new JLabel();

    /**
     * Create a new MinigameEndView object.
     *
     * @param controller the controller to use
     */
    public MinigameEndView(final Controller controller) {
        final int fontSize = 30;
        this.setLayout(new GridLayout(2, 1));
        FontLoader.loadFont();


        endLabel.setFont(new Font("Arial", Font.BOLD, fontSize));
        final JButton endButton = new JButton("return to the gym");
        endButton.setFont(new Font("Arial", Font.BOLD, fontSize));

        endLabel.setBounds(0, 0, 100, 100);
        endButton.setBounds(0, 100, 100, 100);

        endButton.addActionListener(e -> {
            controller.setMinigameResult();
            this.setVisible(false);
        });

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(final ComponentEvent e) {
                endLabel.setText(controller.getMinigameState().getText());
            }
        });

        this.setVisible(true);

        this.add(endLabel);
        this.add(endButton);
    }

}
