package gymlife.model;

import gymlife.utility.BenchDifficulty;
import gymlife.utility.MinigameScenario;

public class MinigameManager {
    private BenchMinigame benchMinigame;
    private SquatMinigame squatMinigame;    
    private LatMachineMinigame latMachineMinigame;
    private BenchDifficulty difficulty;
    private MinigameScenario currentScenario;
    

    public MinigameManager() {
        this.benchMinigame = new BenchMinigame();
        this.squatMinigame = new SquatMinigame();
        this.latMachineMinigame = new LatMachineMinigame();
    }

    public void startMinigame(MinigameScenario minigame){
        switch(minigame){
            case BENCH_PRESS:
                benchMinigame.start();
                break;
            case SQUAT:
                squatMinigame.start();
                break;
            case LAT_MACHINE:
                latMachineMinigame.start();
                break;
        }
    }

    public void setDifficulty(final BenchDifficulty selectedDifficulty){
        this.difficulty = selectedDifficulty;
    }

    public void setScenario(final MinigameScenario newScenario){
        this.currentScenario = newScenario;
    }

    

}
