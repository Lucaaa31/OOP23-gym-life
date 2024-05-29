package gymlife.view;

import gymlife.controller.api.Controller;


import java.io.Serial;
import java.util.Locale;

/**
 * Class with graphical interface for when a random encounter occurs.
 */
public class EncounterView extends ChoicePanel {

    @Serial
    private static final long serialVersionUID = 3208674110651780044L;
    private final transient Controller controller;


    /**
     * Standard constructor for class EncounterView.
     *
     * @param controller      Controller inherited by the class that instantiates this.
     * @param dimensionGetter dimensionGetter inherited by the class that instantiates this.
     */
    public EncounterView(final Controller controller, final DimensionGetter dimensionGetter) {
        super(dimensionGetter);
        this.controller = controller;

        this.setAcceptAction(e -> {
            controller.resolveEncounter(true);
            this.transferFocus();
        });

        this.setDeclineAction(e -> {
            controller.resolveEncounter(false);
            this.transferFocus();
        });
    }


    /**
     * Returns canonical name of this GamePanel.
     * @return string representing said name.
     */
    @Override
    public String getPanelName() {
        return "encounter";
    }


    /**
     * Method to get what text the info panel should show.
     * @return String with said description.
     */
    @Override
    String getDescription() {
        try {
            return "RANDOM ENCOUNTER<br>" + controller.getCurrentEncounter().description();
        } catch (NullPointerException e) {
            return "description";
        }
    }

    /**
     * Method to get what text should be displayed on the accept button.
     * @return a String.
     */
    @Override
    String getAcceptButtonText() {
        try {
            return "ACCEPT<br>" + controller.getCurrentEncounter().acceptCase().toString();
        } catch (NullPointerException e) {
            return "accept";
        }

    }

    /**
     * Method to get what text should be displayed on the decline button.
     * @return a String.
     */
    @Override
    String getDeclineButtonText() {

        try {
            return "DECLINE<br>" + controller.getCurrentEncounter().denyCase().toString();
        } catch (Exception e) {
            return "decline";
        }

    }

    /**
     * Method to get the image to be displayed in the image panel.
     * @return the chosen ImageIcon.
     */
    @Override
    String getImagePath() {
        try {
            return "images/randomEncounters/"
                    + controller.getCurrentEncounter().name().toLowerCase(Locale.ROOT)
                    + ".png";
        } catch (NullPointerException e) {
            return "images/randomEncounters/ice_cream.png";
        }
    }
}
