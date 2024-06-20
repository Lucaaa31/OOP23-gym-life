package gymlife.view;

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
 * This class represents the view that is displayed when the game is over.
 * It extends the GamePanel and shows a "Game Over" message.
 */
public final class GameOverView extends GamePanel {
@Serial
private static final long serialVersionUID = 270660408143212875L;

    /**
     * Constructs a GameOverView with the specified dimension getter.
     *
     * @param dimensionGetter the DimensionGetter used to get the size for the font
     */
    public GameOverView(final DimensionGetter dimensionGetter) {

        final JLayeredPane gameOverPanel = new JLayeredPane() {
            @Override
            protected void paintComponent(final Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.black);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        gameOverPanel.setLayout(new GridBagLayout());

        final JLabel gameOverLabel = new JLabel("Game Over");
        gameOverLabel.setForeground(Color.red);
        gameOverLabel.setFont(FontLoader.getCustomFont(dimensionGetter.getBigFontSize()));


        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        gameOverPanel.add(gameOverLabel, gbc);

        this.setLayout(new BorderLayout());
        this.add(gameOverPanel, BorderLayout.CENTER);
    }

    @Override
    public void resizeComponents() {

    }

    @Override
    public String getPanelName() {
        return "GameOverPanel";
    }
}



