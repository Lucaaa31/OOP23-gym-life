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

    }

    @Override
    void declineButtonAction() {

    }

    @Override
    String getDescription() {
        return "";
    }

    @Override
    String getAcceptButtonText() {
        return "";
    }

    @Override
    String getDeclineButtonText() {
        return "";
    }

    @Override
    String getImagePath() {
        return "";
    }

    @Override
    public String getPanelName() {
        return "";
    }
}
