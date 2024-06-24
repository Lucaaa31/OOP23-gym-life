package gymlife.model.minigame;

import gymlife.utility.minigame.MinigameState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a lat machine minigame that implements the Minigame interface.
 */
public final class LatStrategy extends Minigame {
    private MinigameStatistics statistics = new MinigameStatistics();
    private List<Integer> sequence;
    private final Random random = new Random();
    private int buttonCode;


    /**
     * Constructs a new LatStrategy object.
     */
    public LatStrategy() {
        sequence = new ArrayList<>();
    }

    /**
     * Returns the statistics of the minigame.
     *
     * @return the statistics of the minigame
     */
    @Override
    public MinigameStatistics getStatistics() {
        return statistics;
    }

    /**
     * Sets the statistics of the minigame.
     *
     * @param statistics the statistics of the minigame
     */
    @Override
    public void setStatistics(final MinigameStatistics statistics) {
        this.statistics = statistics;
    }

    /**
     * Notifies the bench minigame that a button has been pressed.
     * Starts the timer if it is the first time the button is pressed.
     *
     * @param buttonCode the button that has been pressed
     */
    @Override
    public void notifyUserAction(final String buttonCode) {
        if (getMinigameState().equals(MinigameState.REP_REACHED)
                || getMinigameState().equals(MinigameState.INVALID_PRESS)
                || getMinigameState().equals(MinigameState.VALID_PRESS)) {
            setMinigameState(MinigameState.RUNNING);
        }
        this.buttonCode = Integer.parseInt(buttonCode);
        super.notifyUserAction(buttonCode);
    }

    /**
     * Handle the NOT_STARTED state.
     */
    @Override
    public void notStarted() {
        createRandomSequence();
        statistics = statistics.startMinigameTime();
        setMinigameState(getMinigameState().next());
    }

    /**
     * Handle the PRESSED_START state.
     */
    @Override
    public void pressedStartButton() {
        super.pressedStartButton();
        running();
    }

    /**
     * Handle the RUNNING state.
     */
    @Override
    public void running() {
        statistics = statistics.incrementInteractions();
        if (conditionOfMinigame(0)) {
            validPress();
        } else {
            invalidPress();
            createRandomSequence();
        }
    }

    /**
     * The condition of the minigame.
     *
     * @param reactionTime the reaction time of the user
     * @return true if the condition is met, false otherwise
     */
    @Override
    public boolean conditionOfMinigame(final long reactionTime) {
        return sequence.get(statistics.interactions() - 1) == buttonCode;
    }


    /**
     * Handle the REP_REACHED state.
     */
    @Override
    public void repReached() {
        super.repReached();
        createRandomSequence();
    }


    /**
     * Creates a random sequence of numbers.
     */
    @Override
    public void createRandomSequence() {
        sequence = new ArrayList<>();
        for (int i = 0; i < getDifficulty().getTouchForLift(); i++) {
            sequence.add(random.nextInt(4) + 1);
        }
    }

    /**
     * Returns the sequence of numbers.
     *
     * @return the sequence of numbers
     */
    @Override
    public List<String> getSequence() {
        return List.copyOf(sequence.stream()
                .map(Object::toString)
                .toList());
    }


}
