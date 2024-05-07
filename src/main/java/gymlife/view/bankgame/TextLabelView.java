package gymlife.view.bankgame;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;

/**
 * This abstract class extends JLabel and implements the BankGameComponent
 * interface.
 * It provides a method to reload the appearance of the multiplier.
 */
public abstract class TextLabelView extends JLabel implements BankGameComponent {
    private static final long serialVersionUID = -8840823320252661066L;
    private static final float SIZE_NUM = 15;

    /**
     * Reloads the appearance of the label by updating its font.
     * The font is set to bold and its size is adjusted based on the current height
     * of the label.
     */
    @Override
    public void reload() {
        final Dimension newSize = this.getSize();
        this.setFont(this.getFont().deriveFont(Font.BOLD, newSize.height / SIZE_NUM));
    }
}
