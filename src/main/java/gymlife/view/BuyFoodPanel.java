package gymlife.view;

import gymlife.controller.api.Controller;
import gymlife.model.statistics.StatsType;
import gymlife.utility.ScenariosType;


import java.io.Serial;
import java.util.Locale;

/**
 * Class that extends ChoicePanel for giving the character a choice when buying food.
 */
public class BuyFoodPanel extends ChoicePanel {

    @Serial
    private static final long serialVersionUID = -8132510797018705147L;
    private final transient Controller controller;

    /**
     * Constructor the uses superclass constructor and also a controller.
     * @param controller inherited controller.
     * @param dimensionGetter inherited dimensionGetter.
     */
    public BuyFoodPanel(final Controller controller, final DimensionGetter dimensionGetter) {
        super(dimensionGetter);
        this.controller = controller;

        this.setAcceptAction(e -> {

            if (controller.getMoney() > controller.getFoodToBuy().getCost()) {
                controller.buyFood();
                controller.changeScenario(ScenariosType.INDOOR_MAP);
                this.transferFocus();
            }
        });

        this.setDeclineAction(e -> {
            controller.changeScenario(ScenariosType.INDOOR_MAP);
            this.transferFocus();
        });

    }


    /**
     * Method to return information about the food to be bought.
     *
     * @return String with food information.
     */
    @Override
    String getDescription() {
        String result = "PRICE : " + controller.getFoodToBuy().getCost() + "$<br>";
        for (final StatsType st : controller.getFoodToBuy().returnFoodPerks().keySet()) {
            result = result.concat(st.toString() + " : " + controller.getFoodToBuy().returnFoodPerks().get(st) + "<br>");
        }
        return result;
    }

    /**
     * Method to get what text should be displayed on the accept button.
     *
     * @return a String.
     */
    @Override
    String getAcceptButtonText() {
        return "Buy this food";
    }

    /**
     * Method to get what text should be displayed on the accept button.
     *
     * @return a String.
     */
    @Override
    String getDeclineButtonText() {
        return "Don't buy this food";
    }

    /**
     * Method to get the food image to be displayed in the image panel.
     *
     * @return the chosen ImageIcon.
     */
    @Override
    String getImagePath() {
        return "images/icons/" + controller.getFoodToBuy().toString().toLowerCase(Locale.ROOT) + ".png";
    }

    /**
     * Method to return the canonical name of this class.
     * @return String for said name.
     */
    @Override
    public String getPanelName() {
        return "buyFood";
    }
}
