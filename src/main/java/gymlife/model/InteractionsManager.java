package gymlife.model;

import gymlife.utility.ScenariosType;

/**
 * A class that contains the methods used for cell interactions.
 */
public final class InteractionsManager {

    private final ScenariosManager scenariosManager;

    /**
     * Constructor of the interactionsManager. All the managers of the game are given to it in order to act as a filter
     * for the interactions.
     * @param scenariosManager the scenarioManager on which the interactions will occur.
     */
    public InteractionsManager(final ScenariosManager scenariosManager) {
        this.scenariosManager = scenariosManager;
    }

    /**
     * method to call if the interaction of a cell concerns changing the current scenario.
     * @param newScenario the new scenario to change to.
     */
    public void scenarioInteraction(final ScenariosType newScenario) {
        this.scenariosManager.updateScenarios(newScenario);
    }
}
