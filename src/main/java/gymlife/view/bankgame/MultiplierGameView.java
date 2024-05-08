package gymlife.view.bankgame;

/**
 * This class extends BLabelView and represents the view for displaying the
 * multiplier in the game.
 */
public final class MultiplierGameView extends TextLabelView {
    private static final long serialVersionUID = -2109720273400189091L;

    public void updateText(float multiplierValue, float money) {
        if (multiplierValue == 0) {
            multiplierValue = 1;
        }
        super.setText(String.format("%.2fx   " + "%.2f", multiplierValue, money));
    }
}
