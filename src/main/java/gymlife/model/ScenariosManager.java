package gymlife.model;

import gymlife.utility.ScenariosType;

/**
 * The ScenariosManager class is responsible for managing the scenarios in the gym life application.
 */
public class ScenariosManager {
    /**
     * The actualScenariosType represents the current scenario type.
     */
     private ScenariosType actualScenariosType = ScenariosType.MAIN_MAP;
    /**
     * Updates the current scenario type.
     * 
     * @param scenariosType the new scenario type to be set
     */
    public void updateScenarios(final ScenariosType scenariosType) {
        this.actualScenariosType = scenariosType;
    }
    /**
     * Retrieves the current scenario type.
     * 
     * @return the current scenario type
     */
    public ScenariosType getActualScenariosType() {
        return actualScenariosType;
    }
}
