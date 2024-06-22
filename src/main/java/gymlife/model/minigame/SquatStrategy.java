package gymlife.model.minigame;

import gymlife.utility.minigame.Colors;
import gymlife.utility.minigame.MinigameState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * The SquatMinigame class represents a squat minigame that can be played within the gym.
 */
public final class SquatStrategy extends Minigame {
    private final List<Colors> colors = new ArrayList<>(Arrays.asList(Colors.values()));
    private Colors currentColor;
    private long startingTime;
    private String colorPressed;
    private final Random random = new Random();
    private final List<String> sequence = new ArrayList<>();
    private boolean isReactionTimeSet;
    private long startCountDown;

    @Override
    public void notifyUserAction(final String buttonCode) {
        if (getMinigameState() == MinigameState.NOT_STARTED) {
            startingTime = System.nanoTime();
            setMinigameState(MinigameState.PRESSED_START);
        } else {
            colorPressed = buttonCode;
            setMinigameState(MinigameState.RUNNING);
            if (!isReactionTimeSet) {
                startCountDown = System.nanoTime();
                isReactionTimeSet = true;
            }
            validatePress();

        }
        createRandomSequence();
    }

    @Override
    public void notifyUserAction() {
        notifyUserAction("0");
    }

    /**
     * Validates the press of the button.
     */
    @Override
    public void validatePress() {
        final long reactionTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startCountDown);
        startCountDown = System.nanoTime();
        if (Objects.equals(currentColor.getColorName(), colorPressed) && reactionTime < getDifficulty().getReactionTime()) {
            incrementInteractions();
            if (handleValidPress()) {
                checkIfMinigameHasEnded(startingTime);
                resetReactionTime();
            }
        } else {
            resetReactionTime();
            handleInvalidPress();
        }
    }

    private void resetReactionTime() {
        isReactionTimeSet = false;
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

    /**
     * Creates a random sequence for the minigame.
     */
    public void createRandomSequence() {
        sequence.clear();

        currentColor = colors.get(random.nextInt(colors.size()));
        sequence.add(currentColor.getColorName());

        Colors color1;
        Colors color2;

        do {
            color1 = colors.get(random.nextInt(colors.size()));
        } while (color1.equals(currentColor));

        do {
            color2 = colors.get(random.nextInt(colors.size()));
        } while (color2.equals(currentColor) || color2.equals(color1));

        sequence.add(color1.getColorName());
        sequence.add(color2.getColorName());

    }

}



