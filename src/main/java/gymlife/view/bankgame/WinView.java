package gymlife.view.bankgame;

import gymlife.utility.DimensionGetter;
import gymlife.utility.FontLoader;
import gymlife.view.api.GamePanel;

import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import java.io.Serial;

/**
 * This class represents the view that is displayed when you win the game.
 */
public final class WinView extends GamePanel {
@Serial
private static final long serialVersionUID = -4738018605210425822L;

    /**
     * Constructs a WinView with the specified dimension getter.
     *
     * @param dimensionGetter the DimensionGetter used to get the size for the font
     */
    public WinView(final DimensionGetter dimensionGetter) {

        final JLayeredPane winPanel = new JLayeredPane() {
            @Override
            protected void paintComponent(final Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.black);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        winPanel.setLayout(new GridBagLayout());

        final JLabel gameWonLabel = new JLabel("You won the game!");
        gameWonLabel.setForeground(Color.green);
        gameWonLabel.setFont(FontLoader.getCustomFont(dimensionGetter.getBigFontSize()));


        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        winPanel.add(gameWonLabel, gbc);

        this.setLayout(new BorderLayout());
        this.add(winPanel, BorderLayout.CENTER);
    }

    @Override
    public void resizeComponents() {

    }

    @Override
    public String getPanelName() {
        return "winPanel";
    }
}

