package gymlife.controller;

import javax.swing.JComponent;

import gymlife.view.bankgame.BankGameView;

import gymlife.model.PlaneGameModel;

public class BankGameController extends JComponent{ 
    private float multi;
    private BankGameView view;

    public BankGameController(PlaneGameModel multi, BankGameView view) {
        this.multi = multi.getMultiplierShort();
        this.view = view;
    }
    
    public void updateview() {
        view.updateMulti(multi);
    }


}
