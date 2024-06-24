package gymlife.model.minigame;

import gymlife.model.minigame.api.MinigameStateHandler;
import gymlife.utility.minigame.ColorModel;
import gymlife.utility.minigame.MinigameState;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * The SquatMinigame class represents a squat minigame that can be played within the gym.
 */
public final class SquatStrategy extends Minigame implements MinigameStateHandler {
    private final List<ColorModel> colors = new ArrayList<>(Arrays.asList(ColorModel.values()));
    private ColorModel currentColor;
    private String colorPressed;
    private final Random random = new Random();
    private final List<String> sequence = new ArrayList<>();
    private final Map<MinigameState, Consumer<MinigameStateHandler>> actionMap = new EnumMap<>(MinigameState.class);
    private final MinigameStatistics statistics = new MinigameStatistics();

    /**
     * Constructs a SquatMinigame object.
     */
    public SquatStrategy() {
        actionMap.put(MinigameState.NOT_STARTED, MinigameStateHandler::notStarted);
        actionMap.put(MinigameState.PRESSED_START, MinigameStateHandler::pressedStartButton);
        actionMap.put(MinigameState.RUNNING, MinigameStateHandler::running);
        actionMap.put(MinigameState.VALID_PRESS, MinigameStateHandler::validPress);
        actionMap.put(MinigameState.INVALID_PRESS, MinigameStateHandler::invalidPress);
        actionMap.put(MinigameState.REP_REACHED, MinigameStateHandler::repReached);
        actionMap.put(MinigameState.ENDED_WON, MinigameStateHandler::miniGameEndedWon);
        actionMap.put(MinigameState.ENDED_LOST, MinigameStateHandler::miniGameEndedLost);
    }

    @Override
    public void notifyUserAction(final String buttonCode) {
        if (getMinigameState().equals(MinigameState.REP_REACHED) || getMinigameState().equals(MinigameState.INVALID_PRESS)){
            setMinigameState(MinigameState.PRESSED_START);
        } else if (getMinigameState().equals(MinigameState.VALID_PRESS)) {
            setMinigameState(MinigameState.RUNNING);
        }
        colorPressed = buttonCode;
        actionMap.get(getMinigameState()).accept(this);
    }


    @Override
    public int getTimeMinigame() {
        return statistics.getMinigameTime();
    }


    /**
     * Creates a random sequence for the minigame.
     */

    @Override
    public void notStarted() {
        statistics.setStartMinigame();
        createRandomSequence();
        setMinigameState(getMinigameState().next());
    }

    @Override
    public void pressedStartButton() {
        statistics.resetReactionTime();
        setMinigameState(getMinigameState().next());
        running();
    }

    @Override
    public void running() {
        final long reactionTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - statistics.getStartReactionTime());
        System.out.println(currentColor.getColorName() + " " + colorPressed + " " + reactionTime);
        statistics.resetReactionTime();
        if (conditionOfMinigame(reactionTime)) {
            statistics.incrementInteractions();
            validPress();
        } else {
            invalidPress();
        }
        createRandomSequence();
    }

    private boolean conditionOfMinigame(final long reactionTime) {
        return Objects.equals(currentColor.getColorName(), colorPressed) && reactionTime < getDifficulty().getReactionTime();
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
        statistics.resetInteractions();
    }

    @Override
    public void repReached() {
        setMinigameState(MinigameState.REP_REACHED);
        if (statistics.getNumReps() == getDifficulty().getRequiredReps()) {
            statistics.setMinigameTime();
            setMinigameState(MinigameState.ENDED_WON);
        }
    }

    @Override
    public void miniGameEndedWon() {
        setMinigameState(MinigameState.ENDED_WON);
    }

    @Override
    public void miniGameEndedLost() {
        setMinigameState(MinigameState.ENDED_LOST);
    }

    public void createRandomSequence() {
        // Clear the previous sequence
        sequence.clear();

        // Select the current color
        currentColor = colors.get(random.nextInt(colors.size()));
        sequence.add(currentColor.getColorName());

        // Select two different colors
        ColorModel color1;
        ColorModel color2;

        do {
            color1 = colors.get(random.nextInt(colors.size()));
        } while (color1.equals(currentColor));

        do {
            color2 = colors.get(random.nextInt(colors.size()));
        } while (color2.equals(currentColor) || color2.equals(color1));

        // Add the different colors to the sequence
        sequence.add(color1.getColorName());
        sequence.add(color2.getColorName());

    }

    /**
     * Returns the sequence of the minigame.
     *
     * @return the sequence of the minigame
     */
    @Override
    public List<String> getSequence() {
        return List.copyOf(sequence);
    }


}



