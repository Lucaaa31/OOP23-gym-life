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
        switch (scenariosType) {
            case MINGAME_BANK:
                actualScenariosType = ScenariosType.MINGAME_BANK;
                break;
            case MINIGAME_GYM:
                actualScenariosType = ScenariosType.MINIGAME_GYM;
                break;
            case MAIN_MAP:
                actualScenariosType = ScenariosType.MAIN_MAP;
                break;
            default:
                break;
        }
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
