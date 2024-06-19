package gymlife.view.minigame;

import gymlife.controller.api.Controller;
import gymlife.utility.FontLoader;
import gymlife.utility.minigame.MinigameState;
import gymlife.view.DimensionGetter;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import java.awt.*;
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
    private final JPanel buttonPanel = new JPanel();

    /**
     * Create a new MinigameEndView object.
     *
     * @param controller the controller to use
     */
    public MinigameEndView(final Controller controller, final DimensionGetter dimensionGetter) {
        final int fontSize = 30;
        this.setLayout(new GridLayout(2, 1));
        FontLoader.loadFont();
        final JButton endButton = new JButton("return to the gym");


        endLabel.setFont(FontLoader.getCustomFont(50));

        endButton.setFont(FontLoader.getCustomFont(fontSize));

        endLabel.setHorizontalAlignment(JLabel.CENTER);
        endButton.setHorizontalAlignment(JButton.CENTER);

        this.setBackground(Color.DARK_GRAY);
        endLabel.setBackground(Color.DARK_GRAY);
        endButton.setBackground(Color.DARK_GRAY);
        endButton.setForeground(Color.WHITE);

        endButton.setBorder(new MatteBorder(5, 5, 5, 5, Color.WHITE));
        endButton.setOpaque(true);


        endButton.addActionListener(e -> {
            controller.setMinigameResult();
            this.setVisible(false);
        });

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(final ComponentEvent e) {
                endLabel.setForeground(controller.getMinigameState().equals(MinigameState.ENDED_WON) ? Color.GREEN : Color.RED);
                endLabel.setText(controller.getMinigameState().getText());
            }
        });

        this.setVisible(true);

        this.add(endLabel);
        this.add(endButton);
    }

}