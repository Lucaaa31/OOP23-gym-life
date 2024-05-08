package gymlife.model.Minigame;

import gymlife.model.api.Minigame;
import gymlife.utility.MinigameDifficulty;
import gymlife.utility.MinigameType;

public class MinigameManager {
    private Minigame currentMinigame;
    private MinigameType currentMinigameType;



    public MinigameManager() {
    }


    public void startMinigame() {
        new Thread((Runnable) currentMinigame).start();
    }

    public MinigameManager setCurrentMinigame(final MinigameType minigameType) {
        this.currentMinigameType = minigameType;
        try {
            this.currentMinigame = (Minigame) Class
                    .forName(minigameType.getName())
                    .getDeclaredConstructor()
                    .newInstance();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return this;
    }

    public MinigameManager setDifficulty(final MinigameDifficulty selectedDifficulty) {
        this.currentMinigame.setDifficulty(selectedDifficulty);
        return this;
    }


    public Minigame getCurrentMinigame() {
        return this.currentMinigame;
    }

    public MinigameManager setTimer(final Timer timer) {
        this.currentMinigame.setTimer(timer);
        return this;
    }

    public MinigameType getMinigameType() {
        return currentMinigameType;
    }
}
