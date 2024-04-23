package gymlife.utility;

import gymlife.model.BenchMinigame;
import gymlife.model.LatMachineMinigame;
import gymlife.model.SquatMinigame;

public enum MinigameType{
    BENCH_PRESS(BenchMinigame.class.getCanonicalName()),

    SQUAT(SquatMinigame.class.getCanonicalName()),

    LAT_MACHINE(LatMachineMinigame.class.getCanonicalName());

    private  String minigameType;

    MinigameType(String minigameType){
        this.minigameType = minigameType;
    }

    public String getName(){
        return this.minigameType;
    }

}
