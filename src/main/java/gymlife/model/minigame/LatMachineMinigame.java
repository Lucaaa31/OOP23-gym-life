package gymlife.model.minigame;


import gymlife.utility.minigame.MinigameState;

import java.util.Random;
import java.util.Set;

public final class LatMachineMinigame extends AbstractMinigame {
    private Set<Integer> setOfSequence;
    private boolean isFirstTimePressed = true;
    private int nTimesPressed = 0;
    private long startMinigame = 0;
    private boolean isReactionTimeSet = false;

    public LatMachineMinigame() {
        super();
    }

    @Override
    public void notifyUserAction() {
        if (isFirstTimePressed) {
            isFirstTimePressed = false;
            startMinigame = System.nanoTime();
        } else {
            setMinigameState(MinigameState.RUNNING);
            nTimesPressed++;
            validatePress();
        }

    }

    @Override
    public void validatePress() {

    }

    public void createRandomSequence() {
        final Random random = new Random();
        for (int i = 0; i < getDifficulty().getTouchForLift(); i++) {
            setOfSequence.add(random.nextInt(4) + 1);
        }
    }
}
