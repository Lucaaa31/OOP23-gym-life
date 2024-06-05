package gymlife.model.minigame;


import gymlife.utility.minigame.MinigameState;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;

/**
 * Represents a lat machine minigame that implements the Minigame interface.
 */
public final class LatMachineMinigame extends AbstractMinigame {
    private List<Integer> sequence;
    private boolean isFirstTimePressed = true;
    private int numberPressed;
    private int index;
    private long startMinigame;
    private final Random random = new Random();


    /**
     * Constructs a new LatMachineMinigame object and initializes the instance variables.
     */
    public LatMachineMinigame() {
        super();
        this.index = 0;
        sequence = new ArrayList<>();
    }


    /**
     * Notifies the lat machine minigame that a button has been pressed.
     * Starts the timer if it is the first time the button is pressed.
     */
    @Override
    public void notifyUserAction(final int... params) {
        if (isFirstTimePressed) {
            startMinigame = System.nanoTime();
            createRandomSequence();
            isFirstTimePressed = false;
        } else {
            incrementNTimePressed();
            numberPressed = params[0];
            setMinigameState(MinigameState.RUNNING);
            validatePress();
        }
    }

    /**
     * Validates the user's press timing and updates the game state accordingly.
     */
    @Override
    public void validatePress() {
        if (conditionOfMinigame(numberPressed)) {
            index++;
            if (handleValidPress()) {
                createRandomSequence();
                index = 0;
                super.checkIfMinigameHasEnded(startMinigame);
            }
        } else {
            index = 0;
            handleInvalidPress();
            createRandomSequence();
        }
    }

    /**
     * Returns true if the user's pressed button is correct, false otherwise.
     *
     * @param buttonPressed the user's pressed button
     * @return true if the user's press timing is correct, false otherwise
     */
    @Override
    public boolean conditionOfMinigame(final long buttonPressed) {
        return sequence.get(index) == buttonPressed;
    }

    /**
     * Creates a random sequence of numbers.
     */
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
    public List<Integer> getSequence() {
        return List.copyOf(sequence);
    }

}
