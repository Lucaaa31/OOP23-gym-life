package gymlife.view.minigame;

import gymlife.controller.api.Controller;
import gymlife.utility.FontLoader;
import gymlife.utility.minigame.MinigameState;
import gymlife.utility.DimensionGetter;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import java.awt.Color;
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
     * @param controller      the controller to use
     * @param dimensionGetter the dimension getter to use
     */
    public MinigameEndView(final Controller controller, final DimensionGetter dimensionGetter) {
        this.setLayout(new GridLayout(2, 1));
        FontLoader.loadFont();
        final JButton endButton = new JButton("return to the gym");
        final int borderSize = 5;
        final int titleScale = 5;
        final int buttonScale = 20;


        endLabel.setFont(FontLoader
                .getCustomFont((float) dimensionGetter.getScenarioDimension().height / titleScale));
        endButton.setFont(FontLoader
                .getCustomFont((float) dimensionGetter.getScenarioDimension().height / buttonScale));

        endLabel.setHorizontalAlignment(JLabel.CENTER);
        endButton.setHorizontalAlignment(JButton.CENTER);

        this.setBackground(Color.DARK_GRAY);
        endLabel.setBackground(Color.DARK_GRAY);
        endButton.setBackground(Color.DARK_GRAY);
        endButton.setForeground(Color.WHITE);

        endButton.setBorder(new MatteBorder(borderSize, borderSize, borderSize, borderSize, Color.WHITE));
        endButton.setOpaque(true);


        endButton.addActionListener(e -> {
            controller.setMinigameResult();
            this.setVisible(false);
        });

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(final ComponentEvent e) {
                endLabel.setForeground(controller
                        .getMinigameState()
                        .equals(MinigameState.ENDED_WON) ? Color.GREEN : Color.RED);
                endLabel.setText(controller.getMinigameState().getText());
            }
        });

        this.setVisible(true);

        this.add(endLabel);
        this.add(endButton);
    }

}
