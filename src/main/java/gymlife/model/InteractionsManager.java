package gymlife.model;

import gymlife.utility.ScenariosType;

public class InteractionsManager {

    private ScenariosManager scenariosManager;

    public InteractionsManager(final ScenariosManager scenariosManager) {
        this.scenariosManager = scenariosManager;
    }

    public void scenarioInteraction(final ScenariosType newScenario) {
        this.scenariosManager.updateScenarios(newScenario);
    }
}
