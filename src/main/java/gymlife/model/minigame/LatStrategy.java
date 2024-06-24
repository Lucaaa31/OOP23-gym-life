package gymlife.model.minigame;

import gymlife.model.minigame.api.MinigameStateHandler;
import gymlife.utility.minigame.MinigameState;

import java.util.*;
import java.util.function.Consumer;

/**
 * Represents a lat machine minigame that implements the Minigame interface.
 */
public final class LatStrategy extends Minigame implements MinigameStateHandler {
    private final Map<MinigameState, Consumer<MinigameStateHandler>> actionMap = new EnumMap<>(MinigameState.class);
    private final MinigameStatistics statistics = new MinigameStatistics();
    private List<Integer> sequence;
    private final Random random = new Random();
    private int buttonCode;


    public LatStrategy() {
        actionMap.put(MinigameState.NOT_STARTED, MinigameStateHandler::notStarted);
        actionMap.put(MinigameState.PRESSED_START, MinigameStateHandler::pressedStartButton);
        actionMap.put(MinigameState.RUNNING, MinigameStateHandler::running);
        actionMap.put(MinigameState.VALID_PRESS, MinigameStateHandler::validPress);
        actionMap.put(MinigameState.INVALID_PRESS, MinigameStateHandler::invalidPress);
        actionMap.put(MinigameState.REP_REACHED, MinigameStateHandler::repReached);
        actionMap.put(MinigameState.ENDED_WON, MinigameStateHandler::miniGameEndedWon);
        actionMap.put(MinigameState.ENDED_LOST, MinigameStateHandler::miniGameEndedLost);
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
        actionMap.get(getMinigameState()).accept(this);
    }


    @Override
    public void notStarted() {
        createRandomSequence();
        statistics.setStartMinigame();
        setMinigameState(getMinigameState().next());
    }

    @Override
    public void pressedStartButton() {
        setMinigameState(getMinigameState().next());
        running();
    }

    @Override
    public void running() {
        statistics.incrementInteractions();
        if (conditionOfMinigame()) {
            validPress();
        } else {
            invalidPress();
        }
    }

    private boolean conditionOfMinigame() {
        return sequence.get(statistics.getInteractions() - 1) == buttonCode;
    }

    @Override
    public void validPress() {
        setMinigameState(MinigameState.VALID_PRESS);
        if (statistics.getInteractions() == getDifficulty().getTouchForLift()) {
            statistics.resetInteractions();
            statistics.incrementNumReps();
            repReached();
        }
    }

    @Override
    public void invalidPress() {
        statistics.incrementMistakes();
        setMinigameState(MinigameState.INVALID_PRESS);
        if (statistics.getMistakes() > getDifficulty().getMaxMistakes()) {
            miniGameEndedLost();
        }
        createRandomSequence();
        statistics.resetInteractions();
    }

    @Override
    public void repReached() {
        setMinigameState(MinigameState.REP_REACHED);
        if (statistics.getNumReps() == getDifficulty().getRequiredReps()) {
            statistics.setMinigameTime();
            setMinigameState(MinigameState.ENDED_WON);
        }
        createRandomSequence();
    }

    @Override
    public void miniGameEndedWon() {
        setMinigameState(MinigameState.ENDED_WON);
    }

    @Override
    public void miniGameEndedLost() {
        setMinigameState(MinigameState.ENDED_LOST);
    }


    @Override
    public int getTimeMinigame() {
        return statistics.getMinigameTime();
    }


    /**
     * Creates a random sequence of numbers.
     */
    public void createRandomSequence() {
        sequence = new ArrayList<>();
        for (int i = 0; i < getDifficulty().getTouchForLift(); i++) {
            sequence.add(random.nextInt(4) + 1);
        }
        System.out.println(sequence);
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
