package gymlife.view;

import gymlife.controller.api.Controller;

import java.io.Serial;

public class BuyFoodPanel extends ChoicePanel{

    @Serial
    private static final long serialVersionUID = -8132510797018705147L;
    private final transient Controller controller;

    public BuyFoodPanel(final Controller controller, final DimensionGetter dimensionGetter) {
        super(dimensionGetter);
        this.controller = controller;

    }

    @Override
    void acceptButtonAction() {
        //TODO add the relative action of accept Button.

    }

    @Override
    void declineButtonAction() {
        //TODO add the relative action of decline Button.
    }

    @Override
    String getDescription() {
        return "";
    }

    @Override
    String getAcceptButtonText() {
        return "Buy this food";
    }

    @Override
    String getDeclineButtonText() {
        return "Don't buy this food";
    }

    @Override
    String getImagePath() {
        return "images/icons/";
    }

    @Override
    public String getPanelName() {
        return "buyFood";
    }
}
