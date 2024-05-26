package gymlife.view;

import gymlife.controller.api.Controller;
import gymlife.utility.FontLoader;

import java.util.Locale;

/**
 * Class with graphical interface for when a random encounter occurs.
 */
public class EncounterView extends ChoicePanel {

    private final transient Controller controller;


    /**
     * Standard constructor for class EncounterView.
     *
     * @param controller      Controller inherited by the class that instantiates this.
     * @param dimensionGetter dimensionGetter inherited by the class that instantiates this.
     */
    public EncounterView(Controller controller, DimensionGetter dimensionGetter) {
        super(dimensionGetter);
        this.controller = controller;
    }


    /**
     * Returns canonical name of this GamePanel.
     * @return string representing said name.
     */
    @Override
    public String getPanelName() {
        return "encounter";
    }

    @Override
    void acceptButtonAction() {
        controller.resolveEncounter(true);
    }

    @Override
    void declineButtonAction() {
        controller.resolveEncounter(false);
    }

    @Override
    String getDescription() {
        if (controller.getCurrentEncounter() != null) {
            return controller.getCurrentEncounter().description();
        }
        return "";
    }

    @Override
    String getAcceptButtonText() {
        if (controller.getCurrentEncounter() != null) {
            return controller.getCurrentEncounter().acceptCase().toString();
        }
        return "";
    }

    @Override
    String getDeclineButtonText() {
        if (controller.getCurrentEncounter() != null) {
            return controller.getCurrentEncounter().denyCase().toString();
        }
        return "";
    }

    @Override
    String getImagePath() {

        if (controller.getCurrentEncounter() != null) {
            return "images/randomEncounters/"
                    + controller.getCurrentEncounter().name().toLowerCase(Locale.ROOT)
                    + ".png";
        }
        return "images/randomEncounters/ice_cream.png";
    }
}
