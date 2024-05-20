package gymlife.view;

import gymlife.utility.FontLoader;
import gymlife.utility.GameDifficulty;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ButtonBuilder {
    private final transient DimensionGetter dimensionGetter;

    public ButtonBuilder(final DimensionGetter dimensionGetter) {
        this.dimensionGetter = dimensionGetter;
    }

    public JButton buttonBuilder(final Color color, final GameDifficulty difficulty) {
        final int borderSize = 5;
        final int fontSize = 60;
        final JButton button = new JButton(difficulty.toString());
        button.setBackground(color);
        button.setBorder(new LineBorder(Color.WHITE, borderSize));
        button.setForeground(Color.BLACK);
        button.setBorderPainted(true);
        button.setPreferredSize(dimensionGetter.getButtonStartDimension());
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setFont(FontLoader.getCustomFont(fontSize));
        button.setVerticalAlignment(SwingConstants.CENTER);

        return button;

    }

}
