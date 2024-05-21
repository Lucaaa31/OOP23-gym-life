package gymlife.view.bankgame;

import java.io.Serial;

/**
 * A specialized TextLabelView for displaying money-related information in the game view.
 */
public class MoneyGameView extends TextLabelView {

    @Serial
    private static final long serialVersionUID = 5339375961088600795L;

    /**
     * Updates the text of the label with the specified money amount.
     *
     * @param moneyStart The amount of money to be displayed.
     */
    public void updateText(final float moneyStart) {
        super.setText(String.format("%.2f", moneyStart));
    }
}
