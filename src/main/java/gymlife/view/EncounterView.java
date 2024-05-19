package gymlife.view;

import gymlife.controller.api.Controller;
import gymlife.view.api.GamePanel;

public class EncounterView extends GamePanel {

    private final transient Controller controller;
    private final transient DimensionGetter dimensionGetter;

    public EncounterView(final Controller controller, final DimensionGetter dimensionGetter) {
        this.controller = controller;
        this.dimensionGetter = dimensionGetter;
    }

    @Override
    public void resizeComponents() {

    }

    @Override
    public String getPanelName() {
        return "encounter";
    }
}
