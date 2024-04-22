package gymlife.model;

import java.util.List;

import gymlife.utility.MinigameDifficulty;

public class BenchMinigame {
    private MinigameDifficulty currentDifficulty;
    private StatsModelImpl statsModel = new StatsModelImpl();

    public BenchMinigame() {

    }

    public void start() {
        
        

    }

    private List<Integer> setDifficulty(){
        return List.of(
            statsModel.getChestMass() + 40,
            statsModel.getChestMass() + 50,
            statsModel.getChestMass() + 60
        );
    }

}
