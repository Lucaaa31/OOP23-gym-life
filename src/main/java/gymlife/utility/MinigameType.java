package gymlife.utility;

import gymlife.model.Minigame.BenchMinigame;
import gymlife.model.LatMachineMinigame;
import gymlife.model.Minigame.SquatMinigame;
import gymlife.view.LatMachineView;
import gymlife.view.minigame.SquatView;
import gymlife.view.minigame.BenchView;

public enum MinigameType{
    BENCH_PRESS(
            BenchMinigame.class.getCanonicalName(),
            BenchView.class.getCanonicalName()
            ),
    SQUAT(
            SquatMinigame.class.getCanonicalName(),
            SquatView.class.getCanonicalName()
            ),
    LAT_MACHINE(
            LatMachineMinigame.class.getCanonicalName(),
            LatMachineView.class.getCanonicalName()
            );

    private final String minigameType;
    private final String minigameViewType;

    MinigameType(String minigameType, String minigameViewType){
        this.minigameType = minigameType;
        this.minigameViewType = minigameViewType;
    }

    public String getName(){
        return this.minigameType;
    }

    public String getViewName(){
        return this.minigameViewType;
    }


}
