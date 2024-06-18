package gymlife.model.minigame;

import gymlife.utility.Position;
import gymlife.utility.minigame.DimensionMinigame;
import gymlife.utility.minigame.MinigameState;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The SquatMinigame class represents a squat minigame that can be played within the gym.
 */
public final class SquatMinigame extends AbstractMinigame {
    private Set<Integer> sequence = new HashSet<>(List.of(1, 2, 3, 4));
    private long startMinigame;
    private int index;

    @Override
    public void notifyUserAction(int buttonCode) {
        if (getMinigameState() == MinigameState.NOT_STARTED) {
            setMinigameState(MinigameState.RUNNING);
            startMinigame = System.nanoTime();
        } else {
            if (getMinigameState() == MinigameState.RUNNING) {
                validatePress();
            }else{
                sequence = new HashSet<>(List.of(1, 2, 3, 4));
            }
        }
    }

    @Override
    public void notifyUserAction() {
        notifyUserAction(0);
    }

    /**
     * Validates the press of the button.
     */
    @Override
    public void validatePress() {
        if (conditionOfMinigame(index)) {
            incrementNTimePressed();
            sequence.remove(index);
            if (sequence.isEmpty()) {
                checkIfMinigameHasEnded(startMinigame);
            }
        }
    }

    /**
     * Returns the condition of the minigame.
     *
     * @param param the parameter to be checked
     * @return true if the condition is met, false otherwise
     */
    @Override
    public boolean conditionOfMinigame(final long param) {
        return sequence.contains((int) param);
    }

    /**
     * Returns the sequence of the minigame.
     *
     * @return the sequence of the minigame
     */
    @Override
    public List<Integer> getSequence() {
        return List.of();
    }

    @Override
    public Position getRandomPositionButton(final DimensionMinigame dimensionMinigame) {
        return new Position(super.getRandomPositionButton(dimensionMinigame).X(), 0);
    }
}
