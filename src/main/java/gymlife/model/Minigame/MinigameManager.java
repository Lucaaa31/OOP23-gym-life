package gymlife.model.Minigame;

import gymlife.model.api.Minigame;
import gymlife.utility.MinigameDifficulty;
import gymlife.utility.MinigameType;

public class MinigameManager {
    private Minigame currentMinigame;
    private MinigameDifficulty difficulty;


    public MinigameManager() {
    }


    public void startMinigame() {
        new Thread((Runnable) currentMinigame).start();
    }

    public MinigameManager setCurrentMinigame(final MinigameType minigameScenario) {

        try {
            this.currentMinigame = (Minigame) Class
                    .forName(MinigameType.BENCH_PRESS.getName())
                    .getDeclaredConstructor(difficulty.getClass())
                    .newInstance(difficulty);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return this;
    }

    public MinigameManager setDifficulty(final MinigameDifficulty selectedDifficulty) {
        this.difficulty = selectedDifficulty;
        return this;
    }


    public Minigame getCurrentMinigame() {
        return this.currentMinigame;
    }

    public MinigameManager setTimer(final Timer timer) {
        this.currentMinigame.setTimer(timer);
        return this;
    }
}
