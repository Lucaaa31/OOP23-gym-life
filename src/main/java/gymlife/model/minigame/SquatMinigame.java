package gymlife.model.minigame;

import java.util.List;

/**
 * The SquatMinigame class represents a squat minigame that can be played within the gym.
 */
public final class SquatMinigame extends AbstractMinigame {

    /**
     * Constructs a new SquatMinigame object.
     */
    @Override
    public void notifyUserAction(final int... params) {

    }

    /**
     * Validates the press of the button.
     */
    @Override
    public void validatePress() {

    }

    /**
     * Returns the condition of the minigame.
     *
     * @param param the parameter to be checked
     * @return true if the condition is met, false otherwise
     */
    @Override
    public boolean conditionOfMinigame(final long param) {
        return false;
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
}
