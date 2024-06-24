package gymlife.model.minigame;

import gymlife.utility.minigame.ColorModel;
import gymlife.utility.minigame.MinigameState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * The SquatMinigame class represents a squat minigame that can be played within the gym.
 */
public final class SquatStrategy extends Minigame {
    private final List<ColorModel> colors = new ArrayList<>(Arrays.asList(ColorModel.values()));
    private ColorModel currentColor;
    private String colorPressed;
    private final Random random = new Random();
    private final List<String> sequence = new ArrayList<>();
    private MinigameStatistics statistics = new MinigameStatistics();


    /**
     * Constructs a new SquatMinigame object.
     */
    public SquatStrategy() {
        currentColor = null;
    }

    /**
     * Notifies the bench minigame that a button has been pressed.
     *
     * @param buttonCode the button that has been pressed
     */
    @Override
    public void notifyUserAction(final String buttonCode) {
        if (getMinigameState().equals(MinigameState.REP_REACHED) || getMinigameState().equals(MinigameState.INVALID_PRESS)) {
            setMinigameState(MinigameState.PRESSED_START);
        } else if (getMinigameState().equals(MinigameState.VALID_PRESS)) {
            setMinigameState(MinigameState.RUNNING);
        }
        colorPressed = buttonCode;
        super.notifyUserAction(buttonCode);
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
     * Handles the NOT_STARTED state of the minigame.
     */
    @Override
    public void notStarted() {
        statistics = statistics.startMinigameTime();
        createRandomSequence();
        setMinigameState(getMinigameState().next());
    }

    /**
     * Handles the PRESSED_START state of the minigame.
     */
    @Override
    public void pressedStartButton() {
        super.pressedStartButton();
        running();
    }

    /**
     * Handles the RUNNING state of the minigame.
     */
    @Override
    public void running() {
        final long reactionTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - statistics.startReactionTime());
        statistics = statistics.resetReactionTime();
        if (conditionOfMinigame(reactionTime)) {
            statistics = statistics.incrementInteractions();
            validPress();
        } else {
            invalidPress();
        }
        createRandomSequence();
    }

    /**
     * The condition of the minigame.
     *
     * @param reactionTime the reaction time of the user
     * @return true if the condition is met, false otherwise
     */
    @Override
    public boolean conditionOfMinigame(final long reactionTime) {
        return Objects.equals(currentColor.getColorName(), colorPressed) && reactionTime < getDifficulty().getReactionTime();
    }

    /**
     * Create the three color sequence.
     */
    @Override
    public void createRandomSequence() {
        sequence.clear();

        currentColor = colors.get(random.nextInt(colors.size()));
        sequence.add(currentColor.getColorName());

        ColorModel color1;
        ColorModel color2;

        do {
            color1 = colors.get(random.nextInt(colors.size()));
        } while (color1.equals(currentColor));

        do {
            color2 = colors.get(random.nextInt(colors.size()));
        } while (color2.equals(currentColor) || color2.equals(color1));

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
