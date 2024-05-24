package gymlife.view.bankgame;

/**
 * This class extends BLabelView and represents the view for displaying the
 * multiplier in the game.
 */
public final class MultiplierGameView extends TextLabelView {
    private static final long serialVersionUID = -2109720273400189091L;

    /**
 * Updates the text of the label with the specified multiplier value and money amount.
 *
 * @param multiplierValue The multiplier value to be displayed.
 * @param money The amount of money to be displayed.
 */
    public void updateText(final double multiplierValue, final float money) {
        super.setText(String.format("%.2fx   " + "%.2f$", multiplierValue, money));
    }
}
