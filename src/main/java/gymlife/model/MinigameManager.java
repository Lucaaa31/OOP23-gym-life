package gymlife.model;

import gymlife.model.api.Minigame;
import gymlife.utility.MinigameDifficulty;
import gymlife.utility.MinigameType;

public class MinigameManager {
    private Minigame currentMinigame;
    private MinigameDifficulty difficulty;


    public MinigameManager() {
    }


    public void startMinigame() {
        currentMinigame.start();
    }

    public MinigameManager setCurrentMinigame(MinigameType minigameScenario) {

        try {
            this.currentMinigame = (Minigame) Class
                    .forName(minigameScenario.getName())
                    .getDeclaredConstructor()
                    .newInstance();
        } catch (Exception e) {

        }
        return this;
    }

    public MinigameManager setDifficulty(final MinigameDifficulty selectedDifficulty) {
        this.difficulty = selectedDifficulty;
        return this;
    }


}
