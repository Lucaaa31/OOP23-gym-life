package gymlife.controller;

import gymlife.view.bankgame.BankGameView;

import gymlife.model.PlaneGameModel;

/**
 * This class manages the interaction between the model (PlaneGameModel) and the
 * view (BankGameView).
 */
public class BankGameController {
    private final PlaneGameModel model;
    private final BankGameView view;
    private static final int THREAD_WAITING = 105;

    /**
     * Constructs a new BankGameController with the specified model and view.
     * 
     * @param model model The model component of the MVC architecture.
     * @param view  The view component of the MVC architecture.
     */
    protected BankGameController(final PlaneGameModel model, final BankGameView view) {
        this.model = model;
        this.view = view;
    }

    /**
     * This method starts the multiplier thread, continuously updates the view with
     * the current multiplier value,
     * and waits for the thread to finish.
     */
    public void startMultiplier() {
        final Thread threadMutliplier = new Thread(model);
        threadMutliplier.start();

        while (threadMutliplier.isAlive()) {
            final float multi = model.getMultiplierShort();

            view.updateMulti(multi);
            try {
                Thread.sleep(THREAD_WAITING);
            } catch (InterruptedException e) {
            }
        }
    }
}
