package gymlife.view.bankgame;

import javax.swing.JLabel;

public abstract class BGLabelView extends JLabel implements BankGameComponent {
    @Override
    public void reload(){
        this.setFont(...);
    }
}
