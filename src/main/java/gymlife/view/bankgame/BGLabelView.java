package gymlife.view.bankgame;


import java.awt.Font;

import javax.swing.JLabel;

public abstract class BGLabelView extends JLabel implements BankGameComponent {
    private Font originalFont;
    @Override
    public void reload(){
    
        int newFontSize = (int) (getWidth() * 0.03); // Esempio: 3% della larghezza della finestra
        Font resizedFont = originalFont.deriveFont(Font.PLAIN, newFontSize);
        setFont(resizedFont);
    }
}
