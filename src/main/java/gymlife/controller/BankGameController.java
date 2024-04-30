package gymlife.controller;

import gymlife.view.bankgame.BankGameView;

import gymlife.model.PlaneGameModel;

public class BankGameController {
    private final PlaneGameModel model;
    private final BankGameView view;

    public BankGameController(PlaneGameModel model, BankGameView view) {
        this.view = view;
        this.model = model;
    }

    public void startMultiplier() {
       final Thread threadMutliplier = new Thread(model);
        threadMutliplier.start();

        while (threadMutliplier.isAlive()) {
            float multi = model.getMultiplierShort();

            view.updateMulti(multi);
            try {
                Thread.sleep(105);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
