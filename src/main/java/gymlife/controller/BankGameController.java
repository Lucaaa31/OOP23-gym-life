package gymlife.controller;

import gymlife.model.PlaneGameModel;

/**
 * This class manages the interaction between the model (PlaneGameModel) and the
 * view (BankGameView).
 */
public class BankGameController {
    private final PlaneGameModel model;
    float multi;
    float money;

    /**
     * Constructs a new BankGameController with the specified model and view.
     * 
     * @param model model The model component of the MVC architecture.
     * @param view  The view component of the MVC architecture.
     */
    public BankGameController() {
        this.model = new PlaneGameModel();
    }

    /**
     * This method starts the multiplier thread, continuously updates the view with
     * the current multiplier value,
     * and waits for the thread to finish.
     */
    public void startMultiplier(float money) {
        model.runMultiplier(money);
    }

    public float getMultiplier() {
        return model.getMultiplierShort();
    }

    public float getTreshold() {
        return model.getTreshold();
    }

    public void controllerStopMultiplier() {
        model.stopMultiplier();
    }
}
