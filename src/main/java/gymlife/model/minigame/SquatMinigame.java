package gymlife.model.minigame;

import gymlife.utility.minigame.Colors;
import gymlife.utility.minigame.MinigameState;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * The SquatMinigame class represents a squat minigame that can be played within the gym.
 */
public final class SquatMinigame extends AbstractMinigame {
    private final List<Colors> colors = new ArrayList<>(Arrays.asList(Colors.values()));
    private Colors currentColor;
    private long startMinigame;
    private String colorPressed;
    private final Random random = new Random();
    private final List<String> sequence = new ArrayList<>();
    private boolean isReactionTimeSet = false;
    private long startReactionTime;

    @Override
    public void notifyUserAction(final String buttonCode) {
        if (getMinigameState() == MinigameState.NOT_STARTED) {
            startMinigame = System.currentTimeMillis();
            setMinigameState(MinigameState.PRESSED_START);
        }else{
            System.out.println("Button pressed: " + buttonCode);
            System.out.println("Right color: " + currentColor.getColorName());
            colorPressed = buttonCode;
            setMinigameState(MinigameState.RUNNING);
            if (!isReactionTimeSet) {
                startReactionTime = System.nanoTime();
                isReactionTimeSet = true;
            }
            validatePress();

        }
        createRandomColor();
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
        long reactionTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startReactionTime);
        startReactionTime = System.nanoTime();
        if (Objects.equals(currentColor.getColorName(), colorPressed) && reactionTime < getDifficulty().getReactionTime()){
            incrementNTimePressed();
            System.out.println("Valid press");
            if (handleValidPress()) {
                System.out.println("REPS");
                checkIfMinigameHasEnded(startMinigame);
                isReactionTimeSet = false;
            }
        } else {
            isReactionTimeSet = false;
            System.out.println("Invalid press");
            handleInvalidPress();
        }
    }


    /**
     * Returns the sequence of the minigame.
     *
     * @return the sequence of the minigame
     */
    @Override
    public List<String> getSequence() {
        return sequence;
    }

    private void createRandomColor() {
        // Clear the previous sequence
        sequence.clear();

        // Select the current color
        currentColor = colors.get(random.nextInt(colors.size()));
        sequence.add(currentColor.getColorName());

        // Select two different colors
        Colors color1;
        Colors color2;

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

}



