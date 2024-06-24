package gymlife.model.map.api;

import gymlife.utility.ScenariosType;

/**
 * An object that manages the game's Scenario.
 */
public interface ScenariosManager {

    /**
     * Method to change the current scenario.
     * @param scenariosType new Scenario to change to.
     */
    void updateScenarios(ScenariosType scenariosType);

    /**
     * Method to get the current scenario.
     * @return the current scenario.
     */
    ScenariosType getActualScenariosType();
}
